package subsequence;

/**
 * 376. 摆动序列
 *
 * @author novo
 * @date 2022/3/1-21:01
 */
public class WiggleSubsequence {
    // 动态规划
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            // 如果下一数大于前一个数 则新升序结尾数组 = 旧降序结尾数组+1 新降序结尾数组不变 维护摆动
            if (nums[i] > nums[i - 1]) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = 1 + dp[i - 1][0];
            } else if (nums[i] < nums[i - 1]) {
                // 如果下一数小于于前一个数 则新降序结尾数组 = 旧升序结尾数组 + 1 维护摆动
                dp[i][0] = 1 + dp[i - 1][1];
                dp[i][1] = dp[i - 1][1];
            } else {
                // 相等都不变
                dp[i][1] = dp[i - 1][1];
                dp[i][0] = dp[i - 1][0];
            }
        }
        // 返回考虑[0, i - 1]内最长子序列的长度
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    // 贪心算法
    public int wiggleMaxLength1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int cur = 0;
        int pre = 0;
        int res = 1;
        for (int i = 1; i < n; i++) {
            cur = nums[i] - nums[i - 1];
            if ((cur > 0 && pre <= 0) || (cur < 0 && pre >= 0)) {
                res++;
                pre = cur;
            }
        }
        return res;
    }
}
