package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全排列
 * 打印一个字符串的去重全排列
 *
 * @author novo
 * @date 2022/2/5-23:31
 */
public class PrintAllPermutations {

    public static List<String> printAllPermutations(String str) {
        List<String> res = new ArrayList<>();
        process(str.toCharArray(), 0, res);
        return res;
    }

    public static List<String> printAllPermutations1(String str) {
        List<String> res = new ArrayList<>();
        process1(str.toCharArray(), 0, res);
        return res;
    }

    public static List<String> printAllPermutations2(String str) {
        List<String> res = new ArrayList<>();
        String path = "";
        boolean[] visited = new boolean[256];
        process2(str.toCharArray(), visited, res, path);
        return res;
    }

    // 递归回溯 index位置可以和index后面的位置做交换
    public static void process(char[] str, int index, List<String> res) {
        if (index == str.length) {
            res.add(String.valueOf(str));
            return;
        }
        for (int i = index; i < str.length; i++) {
            swap(str, index, i);
            process(str, index + 1, res);
            // 需要还原上一时刻的状态，实现回溯
            swap(str, index, i);
        }
    }

    // 递归回溯 index位置可以和index后面的位置做交换
    public static void process1(char[] str, int index, List<String> res) {
        if (index == str.length) {
            res.add(String.valueOf(str));
        } else {
            // 256个ascii码 注意是在一层递归去重 不能写在外面
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                // 剪枝 如果遇到相同ascii码的字符，说明下一个字符和当前为重复字符 跳过
                if (visited[str[i]]) {
                    continue;
                }
                visited[str[i]] = true;
                swap(str, index, i);
                process1(str, index + 1, res);
                // 需要还原上一时刻的状态，实现回溯
                swap(str, index, i);
            }
        }
    }

    // 递归回溯
    public static void process2(char[] str, boolean[] visited, List<String> res, String path) {
        if (path.length() == str.length) {
            res.add(path);
            return;
        }
        // 注意是在同一层递归去重
        for (int i = 0; i < str.length; i++) {
            // 剪枝 如果遇到相同ascii码的字符，说明下一个字符和当前为重复字符 跳过
            if (!visited[i]) {
                // visited[i - 1] == false 说明被回溯过 回到同一层，若此时str[i] == str[i - 1]说明在树层出现重复，即深搜起点一样需要剪枝
                // 当visited[i - 1] == true 说明在深搜树枝，可以重复
                if (i > 0 && !visited[i - 1] && str[i] == str[i - 1]) {
                    continue;
                }
                visited[i] = true;
                path = path + str[i];
                process2(str, visited, res, path);
                // 需要还原上一时刻的状态，实现回溯
                path = path.substring(0, path.length() - 1);
                visited[i] = false;
            }

        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[256];
        process4(nums, visited, res, path);
        return res;
    }

    public void process4(int[] nums, boolean[] visited, List<List<Integer>> res, List<Integer> path) {
        if (path.size() == nums.length) {
            // 注意要重新new一个path 否则都是添加的是同一个引用
            path = new ArrayList<>(path);
            res.add(path);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                if (i > 0 && !visited[i - 1] && nums[i] == nums[i - 1] || visited[nums[i]]) {
                    continue;
                }

                visited[i] = true;
                path.add(nums[i]);
                process4(nums, visited, res, path);
                // 回溯
                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public void test1() {
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> res2 = new ArrayList<>();
        test2(res);
        test3(res2, res);
        System.out.println(res);
    }

    public void test2(List<Integer> res) {
        res.add(2);
    }

    public void test3(List<List<Integer>> res2, List<Integer> res) {
        //List<Integer> res = new ArrayList<>();
        res2.add(res);
    }

    public static void main(String[] args) {
        /*List<String> list = printAllPermutations1("abb");
        list.forEach(System.out::println);
*/
        List<String> list2 = printAllPermutations2("abb");
        //System.out.println(list2.size());
        // list2.forEach(System.out::println);
        int[] nums = new int[]{3, 3, 0, 3};
        new PrintAllPermutations().permuteUnique(nums);
        new PrintAllPermutations().test1();
    }
}
