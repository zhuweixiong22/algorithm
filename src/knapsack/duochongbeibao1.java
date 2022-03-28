package knapsack;

import java.util.*;

public class duochongbeibao1 {
    static int n;
    static int C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        C = sc.nextInt();

        int[] w = new int[n + 1];
        int[] v = new int[n + 1];
        int[] s = new int[n + 1];
        int[][] dp = new int[n + 1][C + 1];

        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
            s[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= C; j++) {
                for (int k = 0; k <= s[i] && k * w[i] <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], k * v[i] + dp[i - 1][j - k * w[i]]);
                }
            }
        }

        System.out.println(dp[n][C]);
    }
}
