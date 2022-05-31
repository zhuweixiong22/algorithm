package knapsack;

import java.util.Scanner;

/**
 * 装箱问题
 *
 * @author zwx
 * @date 2022-05-31 20:40
 */
public class AcWing1024 {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int v = read.nextInt();
        int n = read.nextInt();
        int[] nums = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            nums[i] = read.nextInt();
        }

        int[][] dp = new int[n + 1][v + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= v; j++) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] <= j) {
                    // 将体积也看成价值
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - nums[i]] + nums[i]);
                }
            }
        }

        System.out.println(v - dp[n][v]);
    }
   /* public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int v = read.nextInt();
        int n = read.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = read.nextInt();
        }
        dfs(nums, 0, v);
        System.out.println(res);
    }

    static int res = Integer.MAX_VALUE;

    private static void dfs(int[] nums, int index, int v) {
        if (index == nums.length) {
            res = Math.min(res, v);
            return;
        }
        dfs(nums, index + 1, v);
        if (nums[index] <= v) {
            dfs(nums, index + 1, v - nums[index]);
        }
    }*/
}
