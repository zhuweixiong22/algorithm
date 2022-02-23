package day16;

import jdk.nashorn.internal.ir.CallNode;

import java.util.Arrays;

/**
 * 01背包问题
 *
 * @author novo
 * @date 2022/2/21-23:36
 */
public class Knapsack01 {
    public int bestValue(int[] w, int[] v, int C) {
        int n = w.length;
        return dfs(w, v, n - 1, C);
    }

    private int dfs(int[] w, int[] v, int index, int c) {
        if (index < 0 || c <= 0) {
            return 0;
        }
        // 对于索引为index的物品，不放入背包中
        int res = dfs(w, v, index - 1, c);
        // 对于索引为index的物品，背包容量充足 放入背包中
        if (w[index] <= c) {
            // 两种策略取最值
            res = Math.max(res, v[index] + dfs(w, v, index - 1, c - w[index]));
        }
        return res;
    }

    // 记忆化搜索
    private int[][] memory;

    public int bestValue2(int[] w, int[] v, int C) {
        int n = w.length;
        // 一维表示考虑[0 - n)个物品可以放入 二维表示当前背包容量 加一个偏移量便于理解
        memory = new int[n][C + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                memory[i][j] = -1;
            }
        }
        return dfs2(w, v, n - 1, C);
    }

    private int dfs2(int[] w, int[] v, int index, int c) {
        if (index < 0 || c <= 0) {
            return 0;
        }
        if (memory[index][c] == -1) {
            // 对于索引为index的物品，不放入背包中
            int res = dfs2(w, v, index - 1, c);
            // 对于索引为index的物品，背包容量充足 放入背包中
            if (w[index] <= c) {
                // 两种策略取最值
                res = Math.max(res, v[index] + dfs2(w, v, index - 1, c - w[index]));
            }
            memory[index][c] = res;
        }
        return memory[index][c];
    }

    // 动态规划
    public int bestValue3(int[] w, int[] v, int C) {
        if (w == null || w.length == 0) {
            return 0;
        }
        int n = w.length;
        // dp数组：所持最大价值，一维表示考虑[0 - n)个物品可以放入 二维表示当前背包容量 加一个偏移量便于理解
        int[][] dp = new int[n][C + 1];
        // 最基础的问题 对于0这个物品
        for (int j = 0; j <= C; j++) {
            dp[0][j] = (w[0] <= j ? v[0] : 0);
        }
        // 考虑[1,n)个物品
        for (int i = 1; i < n; i++) {
            // 背包容量为j
            for (int j = 0; j <= C; j++) {
                // 不放入背包
                dp[i][j] = dp[i - 1][j];
                if (w[i] <= j) {
                    // 放入背包
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i - 1][j - w[i]]);
                }
            }
        }
        return dp[n - 1][C];
    }

    // 动态规划空间优化
    public int bestValue4(int[] w, int[] v, int C) {
        if (w == null || w.length == 0) {
            return 0;
        }
        int n = w.length;
        // dp数组：所持最大价值，一维表示考虑[0 - n)个物品可以放入 二维表示当前背包容量 加一个偏移量便于理解
        int[][] dp = new int[2][C + 1];
        // 最基础的问题 对于0这个物品
        for (int j = 0; j <= C; j++) {
            dp[0][j] = (w[0] <= j ? v[0] : 0);
        }
        // 考虑[1,n)个物品
        for (int i = 1; i < n; i++) {
            // 背包容量为j
            for (int j = 0; j <= C; j++) {
                // 不放入背包
                // 使用余数 只使用两行
                dp[i % 2][j] = dp[(i - 1) % 2][j];
                if (w[i] <= j) {
                    // 放入背包
                    dp[i % 2][j] = Math.max(dp[i % 2][j], v[i] + dp[(i - 1) % 2][j - w[i]]);
                }
            }
        }
        return dp[(n - 1) % 2][C];
    }

    // 动态规划
    public int bestValue5(int[] w, int[] v, int C) {
        if (w == null || w.length == 0) {
            return 0;
        }
        int n = w.length;
        // dp数组：所持最大价值，一维表示考虑[0 - n)个物品可以放入 二维表示当前背包容量 加一个偏移量便于理解
        int[] dp = new int[C + 1];
        // 最基础的问题 对于0这个物品 背包容量为j
        for (int j = 0; j <= C; j++) {
            dp[j] = (w[0] <= j ? v[0] : 0);
        }
        // 考虑[1,n)个物品
        for (int i = 1; i < n; i++) {
            // 背包容量为j
            for (int j = C; j >= w[i]; j--) {
                // 不放入背包:dp[j] 放入背包：v[i] + dp[j - w[i]]
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        return dp[C];
    }

    public static int[] gennerateArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        Knapsack01 knapsack01 = new Knapsack01();

        for (int i = 0; i < 10000; i++) {
            int[] w = gennerateArray(5, 10);
            int[] v = gennerateArray(5, 10);
           /* System.out.print("w:");
            printArr(w);
            System.out.println();
            System.out.print("v:");
            printArr(v);
            System.out.println();*/
            if (knapsack01.bestValue3(w, v, 10)
                    != knapsack01.bestValue5(w, v, 10)) {
                System.out.println("fail");
                printArr(w);
                System.out.println();
                printArr(v);
                System.out.println();
                System.out.println("动态：" + knapsack01.bestValue3(w, v, 10));
                System.out.println("记忆化：" + knapsack01.bestValue5(w, v, 10));
                break;
            }
            //System.out.println(knapsack01.bestValue(w, v, 10));
        }

    }
}
