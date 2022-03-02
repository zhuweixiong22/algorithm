package day17;


/**
 * 最长公共子序列
 *
 * @author novo
 * @date 2022/3/2-20:27
 */
public class LongestCommonSubsequence {
    // dfs
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        return dfs(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);
    }

    private int dfs(char[] text1, char[] text2, int i, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (text1[i] == text2[j]) {
            return 1 + dfs(text1, text2, i - 1, j - 1);
        } else {
            return Math.max(dfs(text1, text2, i - 1, j), dfs(text1, text2, i, j - 1));
        }
    }

    // 记忆化搜索
    private int[][] memory;

    public int longestCommonSubsequence1(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        int m = text1.length();
        int n = text2.length();
        memory = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memory[i][j] = -1;
            }
        }
        return dfs1(text1.toCharArray(), text2.toCharArray(), m - 1, n - 1);
    }

    private int dfs1(char[] text1, char[] text2, int i, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (memory[i][j] == -1) {
            if (text1[i] == text2[j]) {
                memory[i][j] = 1 + dfs1(text1, text2, i - 1, j - 1);
            } else {
                memory[i][j] = Math.max(dfs1(text1, text2, i - 1, j), dfs1(text1, text2, i, j - 1));
            }
        }
        return memory[i][j];
    }

    // 动态规划
    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // 初始化默认为0
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        System.out.println(longestCommonSubsequence.longestCommonSubsequence("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"));
    }
}
