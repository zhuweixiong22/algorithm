package dp.qujian;

/**
 * @author zwx
 * @date 2022-05-18 23:01
 */
public class LeetCode516 {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        // 长度为1的子序列最长回文长度为1
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }

        // 枚举区间长度
        for (int len = 2; len <= s.length(); len ++) {
            // 枚举左右边界
            for (int i = 0; i + len - 1 < s.length(); i++) {
                int l = i;
                int r = i + len - 1;
                if (s.charAt(l) == s.charAt(r)) {
                    // 如果两端相等
                    dp[l][r] = dp[l + 1][r - 1] + 2;
                } else {
                    // 两端不相等
                    dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {
        LeetCode516 leetCode516 = new LeetCode516();
        System.out.println(leetCode516.longestPalindromeSubseq("bbbab"));
        System.out.println(leetCode516.longestPalindromeSubseq("cbbd"));
    }
}
