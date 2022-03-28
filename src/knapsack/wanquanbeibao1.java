package knapsack;

import java.util.Scanner;

/**
 * @author novo
 * @date 2022/3/27-20:38
 */
public class wanquanbeibao1 {
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
        // 注意考虑物品0的时候要将所有值都填正确
        for (int j = 1; j <= C; j++) {
            dp[0][j] = dp[0][j - 1];
            if (j % w[0] == 0) {
                dp[0][j] = (j / w[0]) * v[0];
            }
        }
        // 朴素的完全背包
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                for (int k = 0; k * w[i] <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], k * v[i] + dp[i - 1][j - k * w[i]]);
                }
            }
        }

        System.out.println(dp[n - 1][C]);
    }
}
