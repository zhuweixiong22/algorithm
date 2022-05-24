package subsequence;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 登山
 *
 * @author zwx
 * @date 2022-05-24 20:38
 */
public class AcWing1014 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = read.nextInt();
        }

        int res = 1;
        /*int[] dp = new int[n + 1];

        // 枚举最高点
        for (int k = 1; k <= n; k++) {
            int left = 1, right = 1;
            // 正向LIS
            Arrays.fill(dp, 1);
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                left = Math.max(left, dp[i]);
            }

            // 反向LIS
            Arrays.fill(dp, 1);
            for (int i = n; i >= k; i--) {
                for (int j = n; j > i; j--) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                right = Math.max(right, dp[i]);
            }
            // 因为正向反向的k都是取的等号，所以一个点算了两遍 要减去1
            res = Math.max(res, left + right - 1);
        }
*/
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];

        Arrays.fill(f, 1);
        Arrays.fill(g, 1);
        // 正向求解LIS
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        // 反向求解LIS
        for (int i = n; i >= 1; i--) {
            for (int j = n; j > i; j--) {
                if (nums[j] < nums[i]) {
                    g[i] = Math.max(g[i], g[j] + 1);
                }
            }
        }
        // 枚举正向和反向长度和的最大值
        for (int i = 0; i <= n; i++) {
            res = Math.max(res, f[i] + g[i] - 1);
        }
        System.out.println(res);
    }
}
