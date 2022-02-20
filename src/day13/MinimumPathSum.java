package day13;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author novo
 * @date 2022/2/13-17:31
 */
public class MinimumPathSum {
    private int[][] directs = new int[][]{{0, 1}, {1, 0}};
    private int m;
    private int n;
    private int path = 0;
    private List<Integer> paths = new ArrayList<>();
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        // 先把左顶点加进去
        path += grid[0][0];
        backTracking(grid, 0, 0);
        // 将所求的所有路径和排序
        Collections.sort(paths);
        return paths.get(0);
    }

    private void backTracking(int[][] grid, int x, int y) {
        // 终止条件 到达右下顶点 因为是path已经先加的值的再dfs 所以位于[m - 1][n - 1]位置时已经添加了该位置
        if (x == m - 1 && y == n - 1) {
            paths.add(path);
            return;
        }
        for (int[] direct : directs) {
            int newX = x + direct[0];
            int newY = y + direct[1];
            if (inArea(newX, newY)) {
                path += grid[newX][newY];
                backTracking(grid, newX, newY);
                path -= grid[newX][newY];
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }


    // 动态规划
    public int minPathSum1(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        // 空间优化，只用一维
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[0] = grid[0][0];
                } else if (i == 0) {
                    dp[j] = dp[j - 1] + grid[0][j];
                } else if (j == 0) {
                    dp[j] = dp[j] + grid[i][0];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        int[][] grid = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        int[][] grid2 = new int[][]{{1,2},{5,6},{1,1}};
        System.out.println(minimumPathSum.minPathSum(grid));
        minimumPathSum.minPathSum1(grid2);
    }
}
