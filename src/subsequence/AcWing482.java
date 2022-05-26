package subsequence;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 合唱队形
 *
 * @author zwx
 * @date 2022-05-26 21:17
 */
public class AcWing482 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = read.nextInt();
        }

        int res = 1;
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];
        Arrays.fill(f, 1);
        Arrays.fill(g, 1);

        // 正向LIS
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }

        // 反向LIS
        for (int i = n; i >= 1; i--) {
            for (int j = n; j > i; j--) {
                if (nums[j] < nums[i]) {
                    g[i] = Math.max(g[i], g[j] + 1);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            res = Math.max(res, f[i] + g[i] - 1);
        }

        System.out.println(n - res);
    }
}
