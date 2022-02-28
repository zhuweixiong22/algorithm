package day17;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 *
 * @author novo
 * @date 2022/2/28-22:54
 */
public class LongestIncreasingSubsequence {
    // dfs暴力
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int res = 1;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(nums, i));
        }
        return res;
    }

    private int dfs(int[] nums, int index) {
        int res = 1;
        for (int j = 0; j < index; j++) {
            if (nums[j] < nums[index]) {
                res = Math.max(res, 1 + dfs(nums, j));
            }
        }
        return res;
    }

    // 记忆化搜索
    private int[] memory;

    public int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        memory = new int[n];
        // res表示以第i个数字为结尾的最长递增子序列的长度
        int res = 1;
        for (int i = 0; i < n; i++) {
            memory[i] = -1;
        }
        // 枚举所有以第i个数字为结尾的最长递增子序列的长度 取最大值
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs1(nums, i));
        }
        return res;
    }

    private int dfs1(int[] nums, int index) {
        if (memory[index] == -1) {
            int res = 1;
            for (int j = 0; j < index; j++) {
                if (nums[j] < nums[index]) {
                    res = Math.max(res, 1 + dfs1(nums, j));
                }
            }
            memory[index] = res;
        }
        return memory[index];
    }

    // 动态规划
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // 表示以第i个数字为结尾的最长递增子序列的长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 1;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
