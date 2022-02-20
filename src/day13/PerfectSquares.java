package day13;

/**
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 示例 2：
 * <p>
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * @author novo
 * @date 2022/2/16-23:08
 */
public class PerfectSquares {
    /**
     * 拉格朗日四平方和定理
     * 拉格朗日四平方和定理说明任何一个数，都可以由小于等于4个的完全平方数相加得到。
     * 所以dp值可以初始化为4
     *
     * @param n
     * @return
     */
    // 完全背包
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            int res = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                // 这里的1 + dp[i - j * j]用自顶向下更容易理解一些 因为可以拆出一个j*j的平方数所以得加1
                // 拆出一个平方数后 剩余的数是i - j * j，必然小于等于当前的i 这个之前已经求过了
                res = Math.min(res, 1 + dp[i - j * j]);
            }
            dp[i] = res;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        PerfectSquares perfectSquares = new PerfectSquares();
        System.out.println(perfectSquares.numSquares(3));
        String path = "/root/kkFileView-4.1.0-SNAPSHOT/file/temp/";
        System.out.println(path.substring(0, path.length() - 5));
    }
}
