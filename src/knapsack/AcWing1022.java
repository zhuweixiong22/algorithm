package knapsack;

import java.util.Scanner;

/**
 * 宠物小精灵之收服
 *
 * @author zwx
 * @date 2022-05-31 21:55
 */
public class AcWing1022 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int N = read.nextInt();
        int M = read.nextInt();
        int K = read.nextInt();
        int[] v1 = new int[K + 1];
        int[] v2 = new int[K + 1];
        for (int i = 1; i <= K; i++) {
            v1[i] = read.nextInt();
            v2[i] = read.nextInt();
        }

        int[][][] dp = new int[K + 1][N + 1][M];

        for (int i = 1; i <= K; i++) {
            // 精灵球数量
            for (int j = 0; j <= N; j++) {
                // 皮卡丘体力 (题意皮卡丘的体力不能等于0，所以严格小于)
                for (int k = 0; k < M; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (v1[i] <= j && v2[i] <= k) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - v1[i]][k - v2[i]] + 1);
                    }
                }
            }
        }
        int C = dp[K][N][M - 1];
        int R = 0;

        // 从小到大枚举体力 寻找能捕捉C只宠物所需的最小体力值k
        for (int k = 0; k < M; k++) {
            if (dp[K][N][k] == C) {
                R = M - k; // 剩余体力
                break;
            }
        }
        System.out.println(C + " " + R);
    }
}
