package day13;

import java.util.Arrays;

/**
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 *
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * @author novo
 * @date 2022/2/17-23:43
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        char[] str = (" " + s).toCharArray();
        int[] dp = new int[n + 1];
        Arrays.fill(dp,0);
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int one = str[i] - '0';
            int two = (str[i - 1] - '0')* 10 + one;
            if (one >= 1 && one <= 9) {
                dp[i] = dp[i] + dp[i - 1];
            }
            if (two >= 10 && two <= 26) {
                dp[i] = dp[i] + dp[i - 2];
            }
        }
        return dp[n];
    }
}
