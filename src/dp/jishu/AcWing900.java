package dp.jishu;

import java.util.Scanner;

/**
 * 整数划分
 *
 * @author novo
 * @date 2022/4/2-18:45
 */
public class AcWing900 {
    static final int M = (int) (1e9 + 7);

    /*// 完全背包朴素解
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[][] dp = new int[n + 1][n + 1];
        // 凑出0只有一种方案
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k * i <= j; k++) {
                    dp[i][j] = dp[i][j] + dp[i - 1][j - k * i];
                    //dp[i][j] = dp[i][j] % M;
                }
            }
        }

        System.out.println(dp[n][n]);
    }*/

    // 二维
    /*public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                if (i <= j) {
                    dp[i][j] = dp[i][j] + dp[i][j - i];
                }
            }
        }
        System.out.println(dp[n][n]);
    }*/
    /*public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                dp[j] = dp[j] + dp[j - i];
                dp[j] = dp[j] % M;
            }
        }
        System.out.println(dp[n]);
    }*/

    // 另一种转移方程
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[][] dp = new int[n + 1][n + 1];
        int res = 0;
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 凑出n 个数最多的方案最多为 n个1 所以 j <= i
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - j][j];
            }
        }

        for (int j = 0; j <= n; j++) {
            res += dp[n][j];
        }

        System.out.println(res);
    }
}
