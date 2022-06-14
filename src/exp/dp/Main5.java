package exp.dp;

import java.util.Scanner;

/**
 * 1~n的连续整数组成的集合
 * 求拆分成两个子集，使得两个子集元素和相等的方案数
 *
 * @author zwx
 * @date 2022-06-13 20:38
 */
public class Main5 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = i;
        }

        int sum = n * (n + 1) / 2; // 当且仅当两子集和相等时，和为最大值
        if ((sum & 1) == 1) {
            // 数列和为奇数时，无解，因为无法分成两个和相等的子集
            System.out.println(-1);
            return;
        }
        int[][] dp = new int[n + 1][sum / 2 + 1];
        //  考虑前i个数，它们的和恰好是j的方案数
        // dp[i][j]=dp[i−1][j](不选) + dp[i−1][j−i](选i)
        // 每个数只有2种情况，放在第一个集合和不放在第一个集合。只要确定了一个集合，另一个集合必然确定，所以结果要除以2 01背包模型
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                dp[i][j] = dp[i - 1][j];
                if (i <= j) {
                    dp[i][j] += dp[i - 1][j - i];
                }
            }
        }

        System.out.println(dp[n][sum / 2] / 2);
    }
}
