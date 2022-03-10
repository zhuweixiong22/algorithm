package knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和
 *
 * @author novo
 * @date 2022/2/24-21:15
 */
public class CombinationSumIV {
    // 回溯
    private List<Integer> path = new ArrayList<>();
    private List<List<Integer>> res = new ArrayList<>();

    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        backTracking(nums, target);
        return res.size();
    }

    private void backTracking(int[] nums, int target) {
        if (target <= 0) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            path.add(nums[i]);
            backTracking(nums, target - nums[i]);
            path.remove(path.size() - 1);
        }
    }

    // 记忆化搜索
    private int[] memory;

    public int combinationSum43(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        memory = new int[target + 1];
        for (int i = 0; i <= target; i++) {
            memory[i] = -1;
        }
        return dfs(nums, target);
    }

    private int dfs(int[] nums, int target) {
        if (target <= 0) {
            // dp[0] = 1
            if (target == 0) {
                return 1;
            }
            return 0;
        }
        if (memory[target] == -1) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                res = res + dfs(nums, target - nums[i]);
            }
            memory[target] = res;
        }
        return memory[target];
    }

    // 动态规划
    public int combinationSum42(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {
                    dp[i] = dp[i] + dp[i - nums[j]];
                }
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        CombinationSumIV combinationSumIV = new CombinationSumIV();
        System.out.println(combinationSumIV.combinationSum43(nums, 4));
    }
}
