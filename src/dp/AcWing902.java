package dp;

import java.util.Scanner;

/**
 * 最短编辑距离
 *
 * @author novo
 * @date 2022/3/31-17:51
 */
public class AcWing902 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int m = read.nextInt();
        String text1 = read.next();
        int n = read.nextInt();
        String text2 = read.next();
        int[][] dp = new int[m + 1][n + 1];

        // 初始化
        // text2长度为0 text2只能做i步增加
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // text1长度为0 text1只能做j步增加
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 1 + dp[i][j - 1] : A增加  1 + dp[i - 1][j] ： A删除
                dp[i][j] = Math.min(1 + dp[i][j - 1], 1 + dp[i - 1][j]);
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 如果最后一个字符相等则不用操作
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i - 1][j - 1]);
                }
            }
        }

        System.out.println(dp[m][n]);
    }
}
