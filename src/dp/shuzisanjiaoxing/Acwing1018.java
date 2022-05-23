package dp.shuzisanjiaoxing;

import java.util.Scanner;

/**
 * 最低通行证
 * @author zwx
 * @date 2022-05-22 23:12
 */
public class Acwing1018 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[][] nums = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                nums[i][j] = read.nextInt();
            }
        }

        // 因为这题取的是最小值，每次进行状态转移的时候需要先赋最大值，所以并不能使用一维数组来优化
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // 因为取的是最小值, 所以要对边界进行一下特殊处理
                if (i == 1 && j == 1) {
                    dp[1][1] = nums[1][1];
                } else if (i == 1) {
                    dp[1][j] = Integer.MAX_VALUE;
                    dp[1][j] = Math.min(dp[1][j], dp[1][j - 1]) + nums[1][j];
                } else if (j == 1) {
                    dp[i][1] = Integer.MAX_VALUE;
                    dp[i][1] = Math.min(dp[i][1], dp[i - 1][1]) + nums[i][1];
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + nums[i][j];
                }
            }
        }

        System.out.println(dp[n][n]);
    }
}
