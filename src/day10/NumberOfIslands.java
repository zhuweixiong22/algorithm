package day10;

/**
 * 200岛屿数量
 *
 * @author novo
 * @date 2022/2/4-21:55
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    merge(grid, i, j);
                }
            }
        }
        return islands;
    }

    public void merge(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = 'X';
        merge(grid, i - 1, j);
        merge(grid, i + 1, j);
        merge(grid, i, j - 1);
        merge(grid, i, j + 1);
    }

    public static void main(String[] args) {
        int[][] direct = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        System.out.println(direct.length);
        System.out.println(direct[1].length);
    }
}
