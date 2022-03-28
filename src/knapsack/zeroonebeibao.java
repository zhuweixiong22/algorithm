package knapsack;

import java.util.*;

/**
 * @author novo
 * @date 2022/3/27-19:36
 */
public class zeroonebeibao {
    static int n;
    static int C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        C = sc.nextInt();
        int[] w = new int[n];
        int[] v = new int[n];
        int[][] dp = new int[n][C + 1];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        for (int j = 0; j <= C; j++) {
            dp[0][j] = (w[0] <= j) ? v[0] : 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                dp[i][j] = dp[i - 1][j];
                if (w[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i - 1][j - w[i]]);
                }
            }
        }
        System.out.println(dp[n - 1][C]);
    }
}
