package day13;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author novo
 * @date 2022/2/12-23:09
 */
public class Triangle {
    private List<Integer> res = new ArrayList<>();
    private int path;

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        backTracking(triangle, 0, 0);
        // 选取最小路径和
        Collections.sort(res);
        return res.get(0);
    }

    // index 记录上次横向遍历的位置 下次只能从[index,index + 1]做选择 row记录当前层
    private void backTracking(List<List<Integer>> triangle, int index, int row) {
        if (row == triangle.size()) {
            res.add(path);
            return;
        }
        for (int i = index; i < index + 2; i++) {
            // 检查每一层的i是否越界
            if (i != triangle.get(row).size()) {
                path += triangle.get(row).get(i);
                backTracking(triangle, i, row + 1);
                path -= triangle.get(row).get(i);
            }
        }
    }

    // 4 动态规划
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 使用一维数组是只要求顶点一个位置的最小路径，只要底部拿到数据累加覆盖就行
        // 设置成n + 1是为了防止当j为一层的最后一个时，j + 1越界
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            // j是小于等于i 因为i是对应的索引
            for (int j = 0; j <= i; j++) {
                // 有个隐含的点是 数组new出来后初始化都为0 所以最底层的局部最小就是0+0+本身
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
            System.out.println("第" + i + "层 " + Arrays.toString(dp));
        }
        return dp[0];
    }


    // 2 自顶向下递归
    public int minimumTotal3(List<List<Integer>> triangle) {
        return dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int index, int j) {
        if (index == triangle.size() - 1) {
            return triangle.get(index).get(j);
        }
        int path1 = dfs(triangle, index + 1, j);
        int path2 = dfs(triangle, index + 1, j + 1);
        return Math.min(path1, path2) + triangle.get(index).get(j);
    }

    // 3 记忆化搜索
    private int[][] memory;

    public int minimumTotal4(List<List<Integer>> triangle) {
        int n = triangle.size();
        memory = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memory[i][j] = Integer.MIN_VALUE;
            }
        }
        return dfs1(triangle, 0, 0);
    }

    private int dfs1(List<List<Integer>> triangle, int index, int j) {
        if (index == triangle.size() - 1) {
            return triangle.get(index).get(j);
        }
        if (memory[index][j] == Integer.MIN_VALUE) {
            int path1 = dfs1(triangle, index + 1, j);
            int path2 = dfs1(triangle, index + 1, j + 1);
            memory[index][j] = Math.min(path1, path2) + triangle.get(index).get(j);
        }
        return memory[index][j];
    }


    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        // [[2],[3,4],[6,5,7],[4,1,8,3]]
        List<List<Integer>> test = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        list1.add(2);
        list2.add(3);
        list2.add(4);
        list3.add(6);
        list3.add(5);
        list3.add(7);
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        test.add(list1);
        test.add(list2);
        test.add(list3);
        test.add(list4);
        int i = triangle.minimumTotal2(test);
        System.out.println(i);
    }
}
