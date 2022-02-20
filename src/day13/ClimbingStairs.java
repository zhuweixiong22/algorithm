package day13;

/**
 * 爬楼梯
 * @author novo
 * @date 2022/2/12-22:25
 */
public class ClimbingStairs {
    private int[] memory;
    // 记忆化搜索
    public int climbStairs(int n) {
        memory = new int[n + 1];
        for (int i = 0; i < n + 1;i++) {
            memory[i] = -1;
        }
        memory[0] = 1;
        memory[1] = 1;
        return dfs(n);
    }
    private int dfs(int n) {
        if (memory[n] == -1) {
            memory[n] = dfs(n - 1) + dfs(n - 2);
        }
        return memory[n];
    }

    // 动态规划
    public int climbStairs2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        for (int i = 0; i < 10000;i++) {
            if(climbingStairs.climbStairs(10)!=climbingStairs.climbStairs2(10)){
                System.out.println("false");
                break;
            }
        }


    }
}
