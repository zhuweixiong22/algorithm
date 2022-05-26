package subsequence;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 友好城市
 *
 * @author zwx
 * @date 2022-05-26 21:51
 */
public class AcWing1012 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[][] nums = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            nums[i][0] = read.nextInt();
            nums[i][1] = read.nextInt();
        }
        Arrays.sort(nums, (o1, o2) -> o1[0] - o2[0]);

        int[] dp = new int[n + 1];
        int res = 1;
        Arrays.fill(dp, 1);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (nums[j][1] < nums[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        System.out.println(res);
    }
}
