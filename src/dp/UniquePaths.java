package dp;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 *
 * @author novo
 * @date 2022/2/18-22:31
 */
public class UniquePaths {
    // 回溯超时
    private int[][] directs = new int[][]{{1, 0}, {0, 1}};
    private int m;
    private int n;
    private int path = 0;

    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        this.m = m;
        this.n = n;
        backTracking(0, 0);
        return path;
    }

    private void backTracking(int x, int y) {
        if (x == m - 1 && y == n - 1) {
            path++;
            return;
        }
        for (int[] direct : directs) {
            int newX = x + direct[0];
            int newY = y + direct[1];
            if (inArea(newX, newY)) {
                backTracking(newX, newY);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    // 动态规划
    public int uniquePaths2(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = 1;
                } else if (i == 0) {
                    dp[j] = 1;
                } else if (j == 0) {
                    dp[j] = 1;
                } else {
                    dp[j] = dp[j] + dp[j - 1];
                }

            }
        }
        return dp[n - 1];
    }

    // 动态规划 添加哨兵 简化边界处理
    public int uniquePaths3(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3, 2));
        System.out.println(uniquePaths.uniquePaths2(7, 3));
    }
}
