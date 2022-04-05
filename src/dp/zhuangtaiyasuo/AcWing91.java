package dp.zhuangtaiyasuo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 91. 最短Hamilton路径
 *
 * @author novo
 * @date 2022/4/5-14:14
 */
public class AcWing91 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[][] edges = new int[n][n];
        // i 是压缩的状态
        int[][] dp = new int[1 << n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                edges[i][j] = read.nextInt();
            }
        }

        // 初始值INF
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                // 只访问过自己结点且终点为自身的最短距离为0 dp[1][0] = dp[2][1] = dp[4][2] ... = 0
                if ((1 << j) == i) {
                    dp[i][j] = 0;
                } else {
                    // 不能取MAX 因为状态转移时有加法会溢出
                    dp[i][j] = (Integer.MAX_VALUE / 2);
                }
            }
        }
        // 因为这题只能从0点出发，所以只设置一个初始值其他设为INF也是可以的
        dp[1][0] = 0;

        // 枚举路径的所有状态
        for (int i = 0; i < (1 << n); i++) {
            // 枚举终点
            for (int j = 0; j < n; j++) {
                // 一种状态中的已访问结点必须包含终点 这条状态为i 终点为j的路径才合法
                if ((i >> j & 1) == 1) {
                    // 枚举走到j之前 以k为终点的最短距离 也就是前一个状态
                    for (int k = 0; k < n; k++) {
                        if ((i >> k & 1) == 1) {
                            // f(i, j) 由 f(i - {j}, k)转化而来 因为前一个状态要转移到f(i, j)，所以前一个状态j是不能被访问过的
                            dp[i][j] = Math.min(dp[i][j], dp[i - (1 << j)][k] + edges[k][j]);
                        }
                    }
                }
            }
        }
        // 结果 所有结点均被访问过一次 且终点为 n - 1
        System.out.println(dp[(1 << n) - 1][n - 1]);
    }
}
