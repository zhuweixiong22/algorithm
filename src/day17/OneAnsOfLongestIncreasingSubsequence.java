package day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LIS其中一个解
 *
 * @author novo
 * @date 2022/3/3-20:57
 */
public class OneAnsOfLongestIncreasingSubsequence {
    public List<Integer> getSeqOfLIS(int[] nums) {
        if (nums.length < 2) {
            if (nums.length == 0) {
                return new ArrayList<>();
            }
            else {
                return new ArrayList<>(nums[0]);
            }
        }

        int n = nums.length;
        // 表示以nums[i]为结尾的最长递增子序列的长度
        int[] dp = new int[n];
        int maxLen = 1;
        int maxId = 1;
        // res.get(i) 表示以nums[i]结尾的最长递增子序列
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        Arrays.fill(dp, 1);
        // 初始化索引为0的res
        path.add(nums[0]);
        res.add(path);
        for (int i = 1; i < n; i++) {
            path = new ArrayList<>();
            int index = -1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    index = j;
                }
            }
            // 下面两步是将res.get(index)复制并挂到nums[i]作为以nums[i]结尾的最长递增子序列
            if (index > -1) {
                path.addAll(res.get(index));
            }
            path.add(nums[i]);

            res.add(path);
            // 同步更新最大长度和最大长度递增子序列尾元素的索引
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxId = i;
            }
        }
        return res.get(maxId);
    }
    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        OneAnsOfLongestIncreasingSubsequence oneAnsOfLongestIncreasingSubsequence = new OneAnsOfLongestIncreasingSubsequence();
        System.out.println(oneAnsOfLongestIncreasingSubsequence.getSeqOfLIS(nums));
    }
}
