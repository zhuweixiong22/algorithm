package subsequence;


import java.util.*;

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

    public void getSeqOfLIS1(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        int n = nums.length;
        // 表示以nums[i]为结尾的最长递增子序列的长度
        int[] dp = new int[n];
        int[] memory = new int[n];
        int res = 1;
        int index = 0;

        Arrays.fill(memory, -1);
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    // 记录下每个状态从哪里来的
                    memory[i] = j;
                }
            }
            // 求出最大长度 和 该序列结尾的索引
            if (dp[i] > res) {
                res = dp[i];
                index = i;
            }
        }
        Deque<Integer> stack = new ArrayDeque<>();
        // 倒序输出
        for (int i = 0, len = dp[index]; i < len; i++) {
            System.out.println(nums[index]);
            index = memory[index];
        }
        // 最后再逆序一下即可
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,8,6,4};
        OneAnsOfLongestIncreasingSubsequence oneAnsOfLongestIncreasingSubsequence = new OneAnsOfLongestIncreasingSubsequence();
        System.out.println(oneAnsOfLongestIncreasingSubsequence.getSeqOfLIS(nums));
        oneAnsOfLongestIncreasingSubsequence.getSeqOfLIS1(nums);
    }
}
