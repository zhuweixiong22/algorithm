package exp.dp;

import java.util.Scanner;

/**
 * 添加最少括号数问题
 * 给你一个字符串，里面只包含"(",")","[","]" "{","}"六种符号，
 * 请问你需要至少添加多少个括号才能使这些括号匹配起来。
 *
 * @author zwx
 * @date 2022-06-01 10:26
 */
public class Main2 {

    static int INF = 0x3f3f3f3f;

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String str = read.next();
        //String str = "(}(}";
        int n = str.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // 区间dp
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int l = i;
                int r = i + len - 1;
                dp[l][r] = INF;
                if ((str.charAt(l) == '(' && str.charAt(r) == ')')
                        || (str.charAt(l) == '[' && str.charAt(r) == ']')
                        || (str.charAt(l) == '{' && str.charAt(r) == '}')) {
                    dp[l][r] = Math.min(dp[l][r], dp[l + 1][r - 1]);
                } else if (str.charAt(l) == '(' || str.charAt(l) == '[' || str.charAt(l) == '{') {
                    dp[l][r] = Math.min(dp[l][r], dp[l + 1][r] + 1);
                } else if (str.charAt(r) == ')' || str.charAt(r) == ']' || str.charAt(r) == '}') {
                    dp[l][r] = Math.min(dp[l][r], dp[l][r - 1] + 1);
                }

                for (int k = l; k < r; k++) {
                    dp[l][r] = Math.min(dp[l][r], dp[l][k] + dp[k + 1][r]);
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }

}
