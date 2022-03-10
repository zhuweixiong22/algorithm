package subsequence;

import java.util.Arrays;

/**
 * 673. 最长递增子序列的个数
 *
 * @author novo
 * @date 2022/3/2-22:36
 */
public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        // 说明找到了一个更长的递增子序列 还是只有一条最长的 以nums[i]结尾的长度就等于以nums[j]结尾的长度
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        /* 说明在一次内循环寻找dp[i最大值中找到了两个相同长度的递增子序列 注意这里是累加而不是+1
                            因为这是有多种方案可以达到最长递增子序列 而count[i]的定义是以nums[i]结尾的最长递增子序列的数量
                         */
                        count[i] += count[j];
                    }
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        int maxLen = 0;
        // 找出最大长度
        for (int x : dp) {
            maxLen = Math.max(maxLen, x);
        }
        // 将所有最大长度对应的count[i]累加就是结果
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                res += count[i];
            }
        }
        return res;
    }

    public int findNumberOfLIS1(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        int maxLen = 1;
        int res = 1;
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        // 把dp的更新写在dp[j] + 1 > dp[i]就不用额外判断内循环的最值了
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
            // 同时找出最大长度
            maxLen = Math.max(maxLen,dp[i]);
        }

        // 将所有最大长度对应的count[i]累加就是结果
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                res += count[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,4,3,5,4,7,2};
        NumberOfLongestIncreasingSubsequence numberOfLongestIncreasingSubsequence = new NumberOfLongestIncreasingSubsequence();
        System.out.println(numberOfLongestIncreasingSubsequence.findNumberOfLIS(nums));
    }
}
