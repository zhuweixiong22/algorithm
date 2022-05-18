package dp.qujian;

import java.util.Scanner;

/**
 * 矩阵连乘
 * @author zwx
 * @date 2022-05-17 21:37
 */
public class ZOJ1094 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt(); // 矩阵数量
        int[][] matrix = new int[n + 1][2];
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            matrix[i][0] = read.nextInt(); // 行数
            matrix[i][1] = read.nextInt(); // 列数
        }

        // 枚举区间长度
        for (int len = 2; len <= n; len++) {
            // 枚举左右边界
            for (int i = 1; i + len - 1 <= n; i++) {
                int l = i;
                int r = i + len - 1;
                dp[l][r] = Integer.MAX_VALUE;
                // 枚举分界点
                for (int k = l; k < r; k++) {
                    dp[l][r] = Math.min(dp[l][r], dp[l][k] + dp[k + 1][r] + matrix[l][0] * matrix[k][1] * matrix[r][1]);
                }
            }
        }

        System.out.println(dp[1][n]);
    }
}
