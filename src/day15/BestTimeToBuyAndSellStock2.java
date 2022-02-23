package day15;

/**
 * @author novo
 * @date 2022/2/21-20:49
 */
public class BestTimeToBuyAndSellStock2 {


    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        return dfs(prices, 0, 0);
    }

    private int dfs(int[] prices, int index, int status) {
        if (index == prices.length) {
            return 0;
        }
        // 局部变量 因为每次dfs并没有回溯
        int stay = 0, buy = 0, sell = 0;
        // 保持状态不变
        stay = dfs(prices, index + 1, status);
        if (status == 1) {
            // 持股状态卖出
            sell = prices[index] + dfs(prices, index + 1, 0);
        } else {
            // 不持股状态买入
            buy = -prices[index] + dfs(prices, index + 1, 1);
        }
        // 取三种策略的收益最大值
        return Math.max(Math.max(stay, buy), sell);
    }

    // 贪心
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int res = 0;
        // 每一天都做买卖 只要今天股价比昨天股价高，昨天就选择交易
        for (int i = 1; i < n; i++) {
            res += Math.max(0, prices[i] - prices[i - 1]);
        }
        return res;
    }

    // 动态规划
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0];
        int pre0 = dp[0];
        for (int i = 1; i < n; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], pre0 - prices[i]);
            pre0 = dp[0];
        }
        return dp[0];
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock2 bestTimeToBuyAndSellStock2 = new BestTimeToBuyAndSellStock2();
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};
        int[] arr2 = new int[]{1, 2, 3, 4, 5};
        System.out.println(bestTimeToBuyAndSellStock2.maxProfit(arr2));
        System.out.println(bestTimeToBuyAndSellStock2.maxProfit3(arr2));
    }
}
