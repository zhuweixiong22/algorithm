package subsequence;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author novo
 * @date 2022/3/29-16:52
 */
public class LongestIncreasingSubsequenceAcw {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        while (read.hasNext()) {
            int n = read.nextInt();
            int[] nums = new int[n];
            int[] dp = new int[n];
            int res = 1;

            for (int i = 0; i <= n; i++) {
                nums[i] = read.nextInt();
            }
            Arrays.fill(dp, 1);

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                res = Math.max(res, dp[i]);
            }
            System.out.println(res);
        }
    }
}
