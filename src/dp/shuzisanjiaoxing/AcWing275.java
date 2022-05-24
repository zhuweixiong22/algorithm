package dp.shuzisanjiaoxing;

import java.util.Scanner;

/**
 * 传纸条
 *
 * @author zwx
 * @date 2022-05-23 23:05
 */
public class AcWing275 {
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

        int[][][] dp = new int[m + n + 1][m + 1][m + 1];
        for (int k = 2; k <= (m + n); k++) {
            for (int i1 = 1; i1 <= m; i1++) {
                for (int i2 = 1; i2 <= m; i2++) {
                    int j1 = k - i1;
                    int j2 = k - i2;
                    if (j1 >= 1 && j1 <= n && j2 >= 1 && j2 <= n) {
                        // 确保两条路径不会走同一个格子 除了起点和终点
                        if (i1 == i2 && k != 2 && k != m + n) {
                            // 这个状态设置为-INF 后续必然不会被用到
                            dp[k][i1][i2] = Integer.MIN_VALUE;
                            continue;
                        }
                        int val = nums[i1][j1] + nums[i2][j2];
                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1 - 1][i2 - 1] + val);
                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1 - 1][i2] + val);
                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1][i2 - 1] + val);
                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1][i2] + val);
                    }
                }
            }
        }

        System.out.println(dp[m + n][m][m]);
    }
}
