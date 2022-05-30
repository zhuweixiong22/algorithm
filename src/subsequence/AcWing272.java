package subsequence;

import java.util.Scanner;

/**
 * 最长公共上升子序列
 *
 * @author zwx
 * @date 2022-05-30 0:02
 */
public class AcWing272 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = read.nextInt();
        for (int i = 1; i <= n; i++) b[i] = read.nextInt();

        int[][] dp = new int[n + 1][n + 1];
        int res = 0;

        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= n; j++) {
                if (a[i] != b[j]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i][j], max + 1);
                }
                if (b[j] < a[i]) {
                    max = Math.max(max, dp[i - 1][j]);
                }
            }
        }
        for (int i = 1; i <= n; i++) res = Math.max(res, dp[n][i]);
        System.out.println(res);
    }
}
