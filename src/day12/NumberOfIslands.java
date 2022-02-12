package day12;

/**
 * @author novo
 * @date 2022/2/10-15:43
 */
public class NumberOfIslands {
    private int[][] directs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[] visited;
    private int m;
    private int n;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int islands = 0;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i * n + j]) {
                    islands++;
                    dfs(grid, i, j);
                }
            }
        }
        return islands;
    }

    // floodfill算法实质就是深度优先遍历

    /**
     * 注意点：
     * 1、这里表面并没有设置递归终止的条件
     * 但是搜索的位置的有限的，并且没有对visited进行状态重置
     * 所以递归的终止条件隐藏在if的判断语句中
     * 2、这个dfs没有回溯的过程，没有对visited进行状态重置，
     * 因为我们的目的只是将最初[i,j]这个位置相连的地方都标记为同一个岛屿而已，
     * 而不是在其中找到某一个具体的序列或值
     **/
    private void dfs(char[][] grid, int x, int y) {
        visited[x * n + y] = true;
        for (int[] direct : directs) {
            int newX = x + direct[0];
            int newY = y + direct[1];
            // 位置合法的陆地且没有被访问过
            if (inArea(newX, newY) && grid[newX][newY] == '1' && !visited[newX * n + newY]) {
                dfs(grid, newX, newY);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }

    public static void main(String[] args) {
        char[][] nums = new char[][]{
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'1', '1', '1', '1', '0'},
                {'0', '0', '0', '0', '0'}};
        int i = new NumberOfIslands().numIslands(nums);
        System.out.println(i);
    }
}

