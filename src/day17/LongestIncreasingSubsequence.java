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
        // 表示以nums[i]为结尾的最长递增子序列的长度
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

    //贪心 + 二分
    public int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // 表示长度为 i 的 所有递增子序列的结尾的最小值 用的不是动态规划数组名随便起的
        int[] dp = new int[n + 1];
        // 做一个位置的偏移
        dp[1] = nums[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            if (dp[len] < nums[i]) {
                dp[++len] = nums[i];
            } else {
                int index = binarySearch(dp, 1, len, nums[i]);
                if (index != -1) {
                    dp[index] = nums[i];
                }
            }
        }
        return len;
    }

    private int binarySearch(int[] sortedArr, int L, int R, int target) {
        if (sortedArr == null || sortedArr.length == 0) {
            return -1;
        }
        int left = L;
        int right = R;
        int mid = 1;
        // 循环条件至少有两个数
        while (left < right) {
            mid = left + ((right - left) >> 1);
            // 因为是严格递增 所以如果target等于dp里的数的话不更新
            if (sortedArr[mid] == target) {
                return -1;
            } else if (sortedArr[mid] < target) {
                left = mid + 1;
            } else if (sortedArr[mid] > target){
                // 为了保证找出的数一定是第一个大于target的，right指针赋值为mid而不是mid - 1
                // 如果世上mid - 1找出的数只是最邻近target的值，而不能确定这个数比target大还是小
                right = mid;
            }
        }
        return left;
    }
}
