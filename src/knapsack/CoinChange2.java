package knapsack;

/**
 * 518. 零钱兑换 II
 *
 * @author novo
 * @date 2022/3/3-23:28
 */
public class CoinChange2 {
    public int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
            if (amount == 0) {
                return 1;
            }
            return 0;
        }
        int n = coins.length;
        int[] dp = new int[amount + 1];
        // 初始化 只考虑物品0
        dp[0] = 1;
        // 对物品从0开始遍历 因为每个物品都是可以重复选择，对于物品0我们也放进去遍历更方便
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    public int change1(int amount, int[] coins) {
        if (coins.length == 0) {
            if (amount == 0) {
                return 1;
            }
            return 0;
        }
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        dp[0][0] = 1;
        // 初始化 只考虑物品0 只有背包容量为物品0的倍数时 才能恰好装满 j += coins[0]
        for (int j = coins[0]; j <= amount; j += coins[0]) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= amount; j++) {
                // 不选择将coins[i] 放入背包
                dp[i][j] = dp[i - 1][j];
                if (j - coins[i] >= 0) {
                    // 如果背包容量装得下coins[i] 选择放入背包 分类是加法
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }
        return dp[n - 1][amount];
    }
}
