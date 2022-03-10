package knapsack;

import java.util.Arrays;

/**
 * @author novo
 * @date 2022/2/23-16:56
 */
public class CoinChange {
    // DFS
    private int res;

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        res = Integer.MAX_VALUE;
        dfs(coins, amount, 0);
        if (res == Integer.MAX_VALUE) {
            return -1;
        }
        return res;
    }

    private void dfs(int[] coins, int amount, int index) {
        if (amount == 0) {
            // index从0开始 这里的index已经+1所以是正确的
            res = Math.min(res, index);
            return;
        }
        if (amount < 0) {
            // 不能凑出amount res的初始值不会被改变
            return;
        }
        for (int i = 0; i < coins.length; i++) {
            dfs(coins, amount - coins[i], index + 1);
        }
    }


    // 记忆化搜索
    private int[] memory;

    public int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        // 初始化为0
        memory = new int[amount + 1];
        return dfs2(coins, amount);
    }

    private int dfs2(int[] coins, int amount) {
        if (amount == 0) {
            // 填满容量为0的背包 不需要放入物品 物品个数为0
            return 0;
        }
        if (amount < 0) {
            // 不能填满容量为amount的背包
            return -1;
        }
        if (memory[amount] == 0) {
            int minCount = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                // res 为填满容量为amount - coin[i]的最少个数
                int res = dfs2(coins, amount - coins[i]);
                if (res != -1 && res + 1 < minCount) {
                    // 如果存在解 且res解所需的硬币+1 < minCount 则更新minCount
                    // 可以填满amount - coin[i]这个背包 再加上当前的coins[i]这一个物品就能填满amount
                    minCount = res + 1;
                }
            }
            memory[amount] = (minCount == Integer.MAX_VALUE ? -1 : minCount);
        }
        return memory[amount];
    }


    // 动态规划
    public int coinChange3(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int n = coins.length;
        // 凑齐总价值为j 需要的最少硬币个数
        int[] dp = new int[amount + 1];
        // dp数组初始化为一个不可能达到的最大值
        Arrays.fill(dp, amount + 1);
        // 容量为0的背包 最少放0个硬币
        dp[0] = 0;
        // 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
        // 如果求排列数就是外层for遍历背包，内层for循环遍历物品。
        // 对物品从0开始遍历 因为每个物品都是可以重复选择，对于物品0我们也放进去遍历更方便
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);
            }
        }
        return dp[amount] == (amount + 1) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 5};
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange2(arr, 11));
    }
}
