package exp.dp;

import java.util.Scanner;

/**
 * 矩阵最小路径和
 * <p>
 * 4 4
 * 1 3 5 9
 * 8 1 3 4
 * 5 0 6 1
 * 8 8 4 0
 * 12
 *
 * @author zwx
 * @date 2022-06-01 10:15
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int m = read.nextInt();
        int n = read.nextInt();
        int[][] nums = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                nums[i][j] = read.nextInt();
            }
        }

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = nums[1][1];
                } else if (i == 1) {
                    dp[1][j] = dp[1][j - 1] + nums[1][j];
                } else if (j == 1) {
                    dp[i][1] = dp[i - 1][j] + nums[i][1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + nums[i][j];
                }
            }
        }

        System.out.println(dp[m][n]);
    }
}
