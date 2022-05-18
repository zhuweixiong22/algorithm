package dp.qujian;

import java.util.Arrays;

/**
 * @author zwx
 * @date 2022-05-17 22:24
 */
public class LeetCode5 {
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];

        // 长度为1都为回文串
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        /*for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], true);
        }*/

        int maxLen = 1;
        int begin = 0;
        // 枚举长度
        for (int len = 2; len <= s.length(); len++) {
            // 枚举左右边界
            for (int i = 0; i + len - 1 < s.length(); i++) {
                int l = i;
                int r = i + len - 1;
                // 如果两端不相等
                if (s.charAt(l) != s.charAt(r)) {
                    dp[l][r] = false;
                } else {
                    // 如果两端相等且长度小于等于3必为回文串， 也可以将dp数组初始值都设置为true，就不用这个特殊判断
                    if (r - l + 1 <= 3) {
                        dp[l][r] = true;
                    } else {
                        // 如果两端相等 等于上一个状态 因为可能出现某段是回文串但两端不是，整体就不是
                        dp[l][r] = dp[l + 1][r - 1];
                    }
                }

                if (dp[l][r] && r - l + 1 > maxLen) {
                    maxLen = r - l + 1;
                    begin = l;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        LeetCode5 leetCode5 = new LeetCode5();
        System.out.println(leetCode5.longestPalindrome("babad"));
    }
}
