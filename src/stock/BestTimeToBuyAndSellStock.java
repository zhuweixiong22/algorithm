package stock;

/**
 * @author novo
 * @date 2022/2/20-23:37
 */
public class BestTimeToBuyAndSellStock {
    // 暴力枚举 超时
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        // 利益为负不选择交易
        int res = 0;
        // 枚举所有交易差
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                res = Math.max(res, prices[j] - prices[i]);
            }
        }
        // 利益为负 都不交易
        return res;
    }

    // 动态规划
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        // 滚动数组 因为只需要两种状态
        int[] dp = new int[2];
        // 初始化第一天的状态
        dp[0] = 0;
        dp[1] = -prices[0];
        // 从第二天开始遍历
        for (int i = 1; i < n; i++) {
            // 今天不持股的情况 之前也不持股或者今天卖出
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            // 今天持股的情况 之前就持股或者今天买入
            dp[1] = Math.max(dp[1], -prices[i]);
        }
        // 返回不持股时手上的现金
        return dp[0];
    }
}
