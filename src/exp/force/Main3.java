package exp.meiju;

import java.util.Scanner;

/**
 * 样例输入：
 * 4 4
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * X X X X
 *
 * 样例输出：
 * X X X X
 * X X X X
 * X O X X
 * @author zwx
 * @date 2022-05-25 10:52
 */
public class Main3 {
    private static int[][] directs = new int[][]{{0, 1}, {0, -1},{1, 0},{-1, 0}};

    private static int m;
    private static int n;

    private static boolean[] flag;

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        m = read.nextInt();
        n = read.nextInt();
        flag = new boolean[m * n];
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = read.next().charAt(0);
            }
        }
        // dfs上下两行
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 'O') {
                dfs(grid, 0, j);
            }
            if (grid[m - 1][j] == 'O') {
                dfs(grid, m - 1, j);
            }
        }
        // dfs左右两列
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 'O') {
                dfs(grid, i, 0);
            }
            if (grid[i][n - 1] == 'O') {
                dfs(grid, i, n - 1);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!flag[i * n + j] && grid[i][j] == 'O') {
                    grid[i][j] = 'X';
                }
            }
        }

        printGrid(grid);
    }
    private static void dfs(char[][] grid, int x, int y) {
        flag[x * n + y] = true;

        for (int[] direct : directs) {
            int newX = x + direct[0];
            int newY = y + direct[1];
            if (inArea(newX, newY) && grid[newX][newY] == 'O' && !flag[newX * n + newY]) {
                dfs(grid, newX, newY);
            }
        }
    }
    private static boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    private static void printGrid(char[][] grid) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
