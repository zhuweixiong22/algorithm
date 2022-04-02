package dp;

import java.util.Scanner;

/**
 * 编辑距离
 *
 * @author novo
 * @date 2022/4/2-15:34
 */
public class AcWing899 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int m = read.nextInt();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = read.next();
        }

        // 处理m次询问
        while (m-- > 0) {
            String str = read.next();
            int limit = read.nextInt();
            int res = 0;
            for (int i = 0; i < n; i++) {
                if (minEdit(strings[i], str) <= limit) {
                    res++;
                }
            }
            System.out.println(res);
        }
    }

    // 最短编辑距离
    private static int minEdit(String text1, String text2) {
        int[][] dp = new int[11][11];
        int m = text1.length();
        int n = text2.length();
        // 处理边界初始化
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
                dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1);
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
