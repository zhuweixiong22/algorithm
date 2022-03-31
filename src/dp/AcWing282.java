package dp;

import java.util.Scanner;

/**
 * 石子合并
 *
 * @author novo
 * @date 2022/3/31-17:10
 */
public class AcWing282 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] nums = new int[n + 1];
        int[] preSum = new int[n + 1];
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            nums[i] = read.nextInt();
            preSum[i] = preSum[i - 1] + nums[i];
        }
        // 要按区间长度枚举  可以从2开始 因为合并一堆石子代价为0 因为dp初始值就为0
        for (int len = 2; len <= n; len++) {
            // 枚举起点
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                // 注意这里 除了一堆石子的区间初始值为0，其他区间（len>=2）的初始值都要设一个最大值，不然都是0
                dp[i][j] = Integer.MAX_VALUE;
                // 枚举分界点
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + (preSum[j] - preSum[i - 1]));
                }
            }
        }

        System.out.println(dp[1][n]);
    }
}
