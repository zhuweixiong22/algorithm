package day15;

/**
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * @author novo
 * @date 2022/2/21-22:33
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        for (int i = 1; i < n; i++) {
            // 注意dp[][0]非冷冻期不持股的状态只能 由 非冷冻期不持股或冷冻期转化而来 而不能由持股卖出转化
            dp[i][0] = Math.max(dp[i - 1][0],dp[i - 1][2]);
            // 持股状态
            dp[i][1] = Math.max(dp[i - 1][1],dp[i - 1][0] - prices[i]);
            // 冷冻期状态
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        // 返回不持股状况的最大值
        return Math.max(dp[n - 1][0],dp[n - 1][2]);
    }


    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        // 滚动数组 空间优化
        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = -prices[0];
        dp[2] = 0;
        int pre0 = dp[0];
        int pre1 = dp[1];
        for (int i = 1; i < n; i++) {
            // 注意dp[][0]非冷冻期不持股的状态只能 由 非冷冻期不持股或冷冻期转化而来 而不能由持股卖出转化
            dp[0] = Math.max(dp[0],dp[2]);
            // 持股状态
            dp[1] = Math.max(dp[1],pre0 - prices[i]);
            // 冷冻期状态
            dp[2] = pre1 + prices[i];
            pre0 = dp[0];
            pre1 = dp[1];
        }
        // 返回不持股状况的最大值
        return Math.max(dp[0],dp[2]);
    }
}
