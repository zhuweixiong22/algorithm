package dp;

import java.util.Scanner;

/**
 * 摘花生
 * @author zwx
 * @date 2022-05-22 23:37
 */
public class AcWing1015 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int t = read.nextInt();
        while (t-- > 0) {
            int row = read.nextInt();
            int col = read.nextInt();
            int[][] nums = new int[row + 1][col + 1];
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    nums[i][j] = read.nextInt();
                }
            }

            // 空间优化 只记录一列的数据
            int[] dp = new int[col + 1];
            // 状态转移过程说白了就是最后一步是怎么来的
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    // 因为自己写的输入 数据索引都是从1开始，所以上面的case其实都可以省略，不用特殊判断
                    dp[j] = Math.max(dp[j - 1], dp[j]) + nums[i][j];
                }
            }

            System.out.println(dp[col]);
        }
    }
}
