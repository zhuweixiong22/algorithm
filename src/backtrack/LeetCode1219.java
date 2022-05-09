package backtrack;

import java.util.Arrays;

/**
 * 1219. 黄金矿工
 *
 * @author zwx
 * @date 2022-05-09 15:46
 */
public class LeetCode1219 {
    /*private int[][] directs = new int[][]{{0, 1},{0, -1},{1, 0},{-1, 0}};
    private int m;
    private int n;
    private int res;
    private int value;

    private boolean[] visited;
    public int getMaximumGold(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    value += grid[i][j];
                    dfs(grid, i, j);
                    Arrays.fill(visited, false);
                    value = 0;
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int x, int y) {
        visited[x * n + y] = true;
        for (int[] direct : directs) {
            int newX = x + direct[0];
            int newY = y + direct[1];
            if (inArea(newX, newY) && !visited[newX * n + newY] && grid[newX][newY] != 0) {
                value += grid[newX][newY];
                dfs(grid, newX, newY);
                value -= grid[newX][newY];
            }
        }
        // 状态重置
        visited[x * n + y] = false;
        res = Math.max(res, value);
    }*/


    private int[][] directs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int m;
    private int n;
    private boolean[] visited;

    public int getMaximumGold(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int res = 0;
        visited = new boolean[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    res = Math.max(res, dfs(grid, i, j));
                    Arrays.fill(visited, false);
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int x, int y) {
        visited[x * n + y] = true;
        int res = grid[x][y];
        for (int[] direct : directs) {
            int newX = x + direct[0];
            int newY = y + direct[1];
            if (inArea(newX, newY) && !visited[newX * n + newY] && grid[newX][newY] != 0) {
                res = Math.max(res, grid[x][y] + dfs(grid, newX, newY));
            }
        }
        // 状态重置
        visited[x * n + y] = false;
        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        LeetCode1219 leetCode1219 = new LeetCode1219();
        //int[][] grid = new int[][]{{0,6,0},{5,8,7},{0,9,0}};
        int[][] grid = new int[][]{{0, 0, 0, 0, 0, 0, 32, 0, 0, 20}, {0, 0, 2, 0, 0, 0, 0, 40, 0, 32}, {13, 20, 36, 0, 0, 0, 20, 0, 0, 0}, {0, 31, 27, 0, 19, 0, 0, 25, 18, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 18, 0, 6}, {0, 0, 0, 25, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 21, 0, 30, 0, 0, 0, 0}, {19, 10, 0, 0, 34, 0, 2, 0, 0, 27}, {0, 0, 0, 0, 0, 34, 0, 0, 0, 0}};
        //int[][] grid = new int[][]{{1,0,7},{2,0,6},{3,4,5},{0,3,0},{9,0,20}};
        System.out.println(leetCode1219.getMaximumGold(grid));
    }
}
