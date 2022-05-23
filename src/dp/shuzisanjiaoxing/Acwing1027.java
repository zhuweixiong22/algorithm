package dp.shuzisanjiaoxing;

import java.util.Scanner;

/**
 * 方格取数
 *
 * @author zwx
 * @date 2022-05-23 19:36
 */
public class Acwing1027 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[][] nums = new int[n + 1][n + 1];
        while (read.hasNext()) {
            int i = read.nextInt();
            int j = read.nextInt();
            int val = read.nextInt();
            if (i == 0 && j == 0 && val == 0) {
                break;
            }
            nums[i][j] = val;
        }

        /*int[][][][] dp = new int[n + 1][n + 1][n + 1][n + 1];

        for (int i1 = 1; i1 <= n; i1++) {
            for (int j1 = 1; j1 <= n; j1++) {
                for (int i2 = 1; i2 <= n; i2++) {
                    for (int j2 = 1; j2 <= n; j2++) {
                        // 同时走相同的步数
                        if (i1 + j1 == i2 + j2) {
                            int val = nums[i1][j1];
                            // 因为步数相同 如果横坐标相等则二维坐标相等 如果当前坐标不位于同一个格子则两个格子都要相加
                            if (i1 != i2) val += nums[i2][j2];
                            // 最后一步 每一条路有向下或向右两种选择 一共2 * 2种可能
                            dp[i1][j1][i2][j2] = Math.max(dp[i1][j1][i2][j2], dp[i1 - 1][j1][i2 - 1][j2] + val); // 下下
                            dp[i1][j1][i2][j2] = Math.max(dp[i1][j1][i2][j2], dp[i1 - 1][j1][i2][j2 - 1] + val); // 下右
                            dp[i1][j1][i2][j2] = Math.max(dp[i1][j1][i2][j2], dp[i1][j1 - 1][i2 - 1][j2] + val); // 右下
                            dp[i1][j1][i2][j2] = Math.max(dp[i1][j1][i2][j2], dp[i1][j1 - 1][i2][j2 - 1] + val); // 右右
                        }
                    }
                }
            }
        }
        System.out.println(dp[n][n][n][n]);*/

        int[][][] dp = new int[2 * n + 1][n + 1][n + 1];
        for (int k = 2; k <= 2 * n; k++) {
            for (int i1 = 1; i1 <= n; i1++) {
                for (int i2 = 1; i2 <= n; i2++) {
                    // 因为通过枚举路径距离和横坐标，纵坐标有可能非法
                    int j1 = k - i1;
                    int j2 = k - i2;
                    if (j1 >= 1 && j1 <= n && j2 >= 1 && j2 <= n) {
                        int val = nums[i1][j1];
                        if (i1 != i2) val += nums[i2][j2];

                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1 - 1][i2 - 1] + val); // 下下
                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1 - 1][i2] + val); // 下右
                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1][i2 - 1] + val); // 右下
                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1][i2] + val); // 右右
                    }
                }
            }
        }

        System.out.println(dp[n + n][n][n]);
    }
}
