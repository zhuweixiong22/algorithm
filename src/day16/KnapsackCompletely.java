package day16;

/**
 * 完全背包
 *
 * @author novo
 * @date 2022/2/23-22:44
 */
public class KnapsackCompletely {
    // 动态规划
    public int bestValue(int[] w, int[] v, int C) {
        int n = w.length;
        int dp[][] = new int[n][C + 1];

        for (int j = 0; j <= C; j++) {
            dp[0][j] = (w[0] <= j ? v[0] : 0);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                // 不放入背包
                dp[i][j] = dp[i - 1][j];
                if (w[i] <= j) {
                    // 放入背包 两种策略取最优值
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i][j - w[i]]);
                }
            }
        }
        return dp[n - 1][C];
    }

    // 空间优化
    public int bestValue2(int[] w, int[] v, int C) {
        int n = w.length;
        int dp[] = new int[C + 1];

        for (int j = 0; j <= C; j++) {
            dp[j] = (w[0] <= j ? v[0] : 0);
        }

        /*for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                // 不放入背包
                dp[j] = dp[j];
                if (w[i] <= j) {
                    // 放入背包 两种策略取最优值
                    dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
                }
            }
        }*/

        // 可以省略if判断 背包容量从装得下w[i]的物品开始，前面装不下w[i]的直接使用旧值，不需要更新
        for (int i = 1; i < n; i++) {
            for (int j = w[i]; j <= C; j++) {
                // 两种策略取最优值
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        return dp[C];
    }
}
