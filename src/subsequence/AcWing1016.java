package subsequence;

import java.util.Scanner;

/**
 * 最长上升子序列和
 *
 * @author zwx
 * @date 2022-05-26 22:36
 */
public class AcWing1016 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = read.nextInt();
        }

        int[] dp = new int[n + 1];
        // dp初始值就是nums[i]本身
        for (int i = 1; i <= n; i++) {
            dp[i] = nums[i];
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }

        System.out.println(res);
    }
}
