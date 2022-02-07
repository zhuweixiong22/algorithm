package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author novo
 * @date 2022/2/7-17:35
 */
public class Permutations2 {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backTracking(nums);
        return res;
    }

    public void backTracking(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // used[i - 1] == false 说明被回溯过 回到同一层，若此时nums[i] == nums[i - 1]说明在树层出现重复，即深搜起点一样需要剪枝
                // 当used[i - 1] == true 说明在深搜树枝，可以重复
                if (i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) {
                    continue;
                }
                used[i] = true;
                path.add(nums[i]);
                backTracking(nums);
                // 回溯
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }


    public List<List<Integer>> permuteUnique2(int[] nums) {
        Arrays.sort(nums);
        backTracking2(nums, 0);
        System.out.println("退出backTracking函数的path" + path);
        System.out.println("退出backTracking函数的res " + res);
        return res;
    }

    public void backTracking2(int[] nums, int index) {
        if (path.size() == nums.length) {

            res.add(new ArrayList<>(path));
            System.out.println("backTracking函数中的path" + path);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // visited[i - 1] == false 说明被回溯过 回到同一层，若此时str[i] == str[i - 1]说明在树层出现重复，即深搜起点一样需要剪枝
                // 当visited[i - 1] == true 说明在深搜树枝，可以重复
                if (i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) {
                    continue;
                }
                used[i] = true;
                path.add(nums[i]);
                //System.out.println(path);
                backTracking2(nums, index + 1);
                // 回溯
                used[i] = false;
                //System.out.println("path" + path);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        List<List<Integer>> lists = new Permutations2().permuteUnique2(nums);
        System.out.println(lists);
    }
}
