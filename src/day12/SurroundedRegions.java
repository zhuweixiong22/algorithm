package day12;

/**
 * 给你一个 m x n 的矩阵 board ，
 * 由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，
 * 并将这些区域里所有的 'O' 用 'X' 填充。
 * 任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 *
 * @author novo
 * @date 2022/2/10-16:48
 */
public class SurroundedRegions {
    private int[][] directs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int m;
    private int n;
    private boolean[] visited;

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        m = board.length;
        n = board[0].length;
        visited = new boolean[m * n];
        //1 对最外面一圈进行dfs，标记与外界相连的'0'
        // 对第一列和最后一列dfs
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                dfs(board, i, n - 1);
            }
        }
        // 对第一行和最后一行dfs
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                dfs(board, m - 1, j);
            }
        }
        // 2 遍历整个棋盘 将没有被标记到的'0'都翻转成'X'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !visited[i * n + j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        visited[x * n + y] = true;
        for (int[] direct : directs) {
            int newX = x + direct[0];
            int newY = y + direct[1];
            if (inArea(newX, newY) && board[newX][newY] == 'O' && !visited[newX * n + newY]) {
                dfs(board, newX, newY);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X',},
                {'X', 'O', 'O', 'X',},
                {'X', 'X', 'O', 'X',},
                {'X', 'O', 'X', 'X',}};
        new SurroundedRegions().solve(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(board);
    }
}
