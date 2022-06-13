package exp.dp;

import java.util.Scanner;

/**
 * 求解双核处理问题
 *
 * 01背包模型
 * 两个单核处理的时间越接近，（最好情况下，两个单核处理的时间相同）双核处理的总时间就越短最短
 * 5
 * 3072 3072 7168 3072 1024
 * @author zwx
 * @date 2022-06-13 17:54
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] length = new int[n + 1];
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            length[i] = read.nextInt();
            sum += (length[i]);
        }

        int[][] dp = new int[n + 1][sum / 2 + 1];

        // 背包容量和价值都是任务长度
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                dp[i][j] = dp[i - 1][j];
                if (length[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - length[i]] + length[i]);
                }
            }
        }

        // dp[n][sum / 2] 的背包容量是两个背包中体积较小的那一个，因为是整除
        System.out.println((sum - dp[n][sum / 2]) / 1);
    }
}
