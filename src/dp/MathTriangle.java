package dp;

import java.util.Scanner;

/**
 * @author novo
 * @date 2022/3/29-16:17
 */
public class MathTriangle {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] triangle = new int[2 * n][2 * n];
        int[][] dp = new int[2 * n][2 * n];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                triangle[i][j] = sc.nextInt();
            }
        }

        // 自底向上
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j];
            }
        }

        System.out.println(dp[1][1]);
    }
}
