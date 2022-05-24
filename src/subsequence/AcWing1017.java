package subsequence;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 怪盗基德的滑翔翼
 *
 * @author zwx
 * @date 2022-05-24 19:45
 */
public class AcWing1017 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int k = read.nextInt();
        while (k-- > 0) {
            int n = read.nextInt();
            int[] nums = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                nums[i] = read.nextInt();
            }

            int res = 1;
            int[] dp = new int[n + 1];

            // 正向下降LIS问题
            Arrays.fill(dp, 1);
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    if (nums[j] > nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                res = Math.max(res, dp[i]);
            }

            // 反向下降LIS问题
            Arrays.fill(dp, 1);
            for (int i = n; i >= 1; i--) {
                for (int j = n; j > i; j--) {
                    if (nums[j] > nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                res = Math.max(res, dp[i]);
            }

            // 两个方向取个最值
            System.out.println(res);
        }
    }
}
