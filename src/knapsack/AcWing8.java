package knapsack;

import java.util.Scanner;

/**
 * 二维费用背包
 *
 * @author zwx
 * @date 2022-05-31 21:49
 */
public class AcWing8 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int V = read.nextInt();
        int M = read.nextInt();

        int[] v = new int[n + 1];
        int[] m = new int[n + 1];
        int[] w = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            v[i] = read.nextInt();
            m[i] = read.nextInt();
            w[i] = read.nextInt();
        }

        int[][][] dp = new int[n + 1][V + 1][M + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= V; j++) {
                for (int k = 0; k <= M; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    // 当前物品重量和体积均小于当前背包剩余体积和重量时才考虑放入背包
                    if (v[i] <= j && m[i] <= k) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - v[i]][k - m[i]] + w[i]);
                    }
                }
            }
        }

        System.out.println(dp[n][V][M]);
    }
}
