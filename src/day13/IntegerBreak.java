package day13;

/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。
 * 返回你可以获得的最大乘积。
 * @author novo
 * @date 2022/2/14-23:29
 */
public class IntegerBreak {

    // 回溯
    public int integerBreak(int n) {
        return backTracking(n);
    }

    // 将n分割 至少分割两部分
    private int backTracking(int n) {
        if (n == 1) {
            return 1;
        }
        // 用res来记录分割n产生的乘积的最大值 不断更新
        // 因为最小分割成两个且n为大于等于2的正整数 所以res初始值只要小于等于1都可以
        int res = 0;
        for (int i = 1; i < n; i++) {
            // 注意这里是取3个值比较，一个是之前的res，另一个是子问题产生的乘积的最大值，最容易漏的一个就是当前的分割方案的值i * (n - i)，
            // 因为有可能当前方案就是最优解
            res = max3(res, i * (n - i), i * backTracking(n - i));
        }
        return res;
    }

    private int max3(int num1, int num2, int num3) {
        return Math.max(Math.max(num1, num2), num3);
    }


    // 记忆化搜索
    private int[] memory;

    public int integerBreak2(int n) {
        // 加一个偏移量 方便理解  memory[1]表示的就是分割1的最优解
        memory = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            memory[i] = -1;
        }
        memory[0] = 0;
        memory[1] = 1;

        return backTracking2(n);
    }

    private int backTracking2(int n) {
        if (n == 1) {
            return 1;
        }
        int res = 0;
        if (memory[n] == -1) {
            for (int i = 1; i < n; i++) {
                res = max3(res, i * (n - i), i * backTracking2(n - i));
            }
            memory[n] = res;
        }
        return memory[n];
    }

    // 动态规划
    public int integerBreak3(int n) {
        // 加一个偏移量 方便理解  dp[1]表示的就是分割1的最优解
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 求解dp[i]
            int res = 0;
            for (int j = 1; j < i; j++) {
                res = max3(res, j * (i - j), j * dp[i - j]);
            }
            dp[i] = res;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
        System.out.println(integerBreak.integerBreak3(10));
    }

}
