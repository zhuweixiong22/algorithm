package day13;

/**
 * 斐波那契
 *
 * @author novo
 * @date 2022/2/12-21:31
 */
public class FibonacciNumber {
    private static int count;
    // memory都初始化为-1
    private int[] memory;

    // 记忆化搜索-自顶向下的解决问题 调用2n - 1次
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        memory = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            memory[i] = -1;
        }
        memory[0] = 0;
        memory[1] = 1;
        return dfs(n);
    }

    private int dfs(int n) {
        if (memory[n] == -1) {
            memory[n] = fib(n - 1) + fib(n - 2);
        }
        return memory[n];
    }

    // 动态规划-自底向上的解决问题 先解决小数据量 再层层递推 只调用n

    // 动态规划 自底向上
    public int fib2(int n) {
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int fib = new FibonacciNumber().fib(20);
        long end = System.currentTimeMillis();
        System.out.println(fib);
        System.out.println("耗时" + (end - start) + "ms");
        System.out.println("执行" + count);
    }
}
