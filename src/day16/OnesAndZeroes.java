package day16;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 474. 一和零
 *
 * @author novo
 * @date 2022/2/26-21:56
 */
public class OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0 || (m == 0 && n == 0)) {
            return 0;
        }
        return dfs(strs, 0, m, n);
    }

    private int dfs(String[] strs, int index, int m, int n) {
        if (index == strs.length) {
            return 0;
        }
        char[] str = strs[index].toCharArray();
        int one = 0;
        int zero = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '0') {
                zero++;
            }
            if (str[i] == '1') {
                one++;
            }
        }
        // 当前str[index] 0 和 1 的个数都不超过m和n才有选与不选两种选择，否则只能不选
        if (m >= zero && n >= one) {
            return Math.max(dfs(strs, index + 1, m, n), 1 + dfs(strs, index + 1, m - zero, n - one));
        } else {
            return dfs(strs, index + 1, m, n);
        }
    }

    // 自顶向下 记忆化搜索
    private int[][][] memory;

    public int findMaxForm1(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0 || (m == 0 && n == 0)) {
            return 0;
        }
        memory = new int[strs.length][m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    memory[i][j][k] = -1;
                }
            }
        }
        return dfs1(strs, strs.length - 1, m, n);
    }

    private int dfs1(String[] strs, int index, int m, int n) {
        if (index < 0) {
            return 0;
        }
        int zero = 0;
        int one = 0;
        char[] str = strs[index].toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '0') {
                zero++;
            }
            if (str[i] == '1') {
                one++;
            }
        }
        if (memory[index][m][n] == -1) {
            if (m >= zero && n >= one) {
                memory[index][m][n] = Math.max(dfs1(strs, index - 1, m, n), 1 + dfs1(strs, index - 1, m - zero, n - one));
            } else {
                memory[index][m][n] = dfs1(strs, index - 1, m, n);
            }
        }
        return memory[index][m][n];
    }

    // 三维数组动态规划
    public int findMaxForm2(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] strInfo = new int[len][2];
        // 存储所有字符的01信息
        for (int i = 0; i < len; i++) {
            char[] str = strs[i].toCharArray();
            int zero = 0;
            int one = 0;
            for (int j = 0; j < str.length; j++) {
                if (str[j] == '0') {
                    zero++;
                }
                if (str[j] == '1') {
                    one++;
                }
            }
            strInfo[i] = new int[]{zero, one};
        }

        int[][][] dp = new int[len][m + 1][n + 1];
        // 初始化dp数组 考虑第一件物品 这个也可以放到下面的循环一起
        for (int j = 0; j <= m; j++) {
            for (int k = 0; k <= n; k++) {
                if (strInfo[0][0] <= j && strInfo[0][1] <= k) {
                    dp[0][j][k] = 1;
                } else {
                    dp[0][j][k] = 0;
                }
            }
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 当前strInfo 0 和 1 的个数都不超过m和n才有选与不选两种选择，否则只能不选
                    if (strInfo[i][0] <= j && strInfo[i][1] <= k) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], 1 + dp[i - 1][j - strInfo[i][0]][k - strInfo[i][1]]);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[len - 1][m][n];
    }

    // 空间优化 取消物品维度
    public int findMaxForm3(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] strInfo = new int[len][2];
        // 存储所有字符的01信息
        for (int i = 0; i < len; i++) {
            char[] str = strs[i].toCharArray();
            int zero = 0;
            int one = 0;
            for (int j = 0; j < str.length; j++) {
                if (str[j] == '0') {
                    zero++;
                }
                if (str[j] == '1') {
                    one++;
                }
            }
            strInfo[i] = new int[]{zero, one};
        }

        int[][] dp = new int[m + 1][n + 1];
        // 初始化dp数组 考虑第一件物品 这个也可以放到下面的循环一起
        for (int j = 0; j <= m; j++) {
            for (int k = 0; k <= n; k++) {
                if (strInfo[0][0] <= j && strInfo[0][1] <= k) {
                    dp[j][k] = 1;
                } else {
                    dp[j][k] = 0;
                }
            }
        }

        for (int i = 1; i < len; i++) {
            // 和01背包的空间优化一样 逆序遍历 可以提前结束 省去了额外的if判断
            for (int j = m; j >= strInfo[i][0]; j--) {
                for (int k = n; k >= strInfo[i][1]; k--) {
                    dp[j][k] = Math.max(dp[j][k], 1 + dp[j - strInfo[i][0]][k - strInfo[i][1]]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        OnesAndZeroes onesAndZeroes = new OnesAndZeroes();
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        System.out.println(onesAndZeroes.findMaxForm(strs, 5, 3));
    }
}
