package dp;

/**
 * @author novo
 * @date 2022/2/18-23:13
 */
public class UniquePaths2 {
    // 动态规划
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n + 1];
        // 哨兵 简化边界
        // 注意这里为什么不能用dp[0] = 1 然后j从1开始遍历
        dp[1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 到达障碍物的路径为0条
                if (obstacleGrid[i][j] == 1) {
                    dp[j + 1] = 0;
                } else {
                    dp[j + 1] = dp[j + 1] + dp[j];
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        UniquePaths2 uniquePaths2 = new UniquePaths2();
        int[][] arr = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePaths2.uniquePathsWithObstacles(arr));
    }
}
