package exp.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 股票问题
 * <p>
 * 12
 * 68 69 54 64 68 64 70 67 78 62 98 87
 *
 * @author zwx
 * @date 2022-06-01 11:03
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = read.nextInt();
        }
        // 最长下降子序列模型
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        System.out.println(res);
    }
}
