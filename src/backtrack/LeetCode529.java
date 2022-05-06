package backtrack;

import tool.LeetCodeTool;

/**
 * 529. 扫雷游戏
 * @author zwx
 * @date 2022-05-05 16:10
 */
public class LeetCode529 {
    // 八个方向
    private int[][] directs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private int m;
    private int n;

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board.length == 0) return null;
        m = board.length;
        n = board[0].length;
        int x = click[0];
        int y = click[1];
        // 点击为雷，退出
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        dfs(board, x, y);
        return board;
    }

    private void dfs(char[][] board, int x, int y) {
        // 先扫描一圈周围，记录地雷个数
        int count = 0;
        for (int[] direct : directs) {
            int newX = x + direct[0];
            int newY = y + direct[1];
            if (inArea(newX, newY) && board[newX][newY] == 'M') {
                count++;
            }
        }
        if (count > 0) {
            board[x][y] = (char) (count + '0');
        } else {
            // board[x][y] 不为数字才继续深搜 即(x, y)的八个方向没有地雷则继续深搜
            //visited[x * n + y] = true;
            board[x][y] = 'B';
            for (int[] direct : directs) {
                int newX = x + direct[0];
                int newY = y + direct[1];
                // 这里不需要visited数组，因为判断该字符是否为'E'就知道有没有被访问过
                if (inArea(newX, newY)  && board[newX][newY] == 'E') {
                    dfs(board, newX, newY);
                }
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        LeetCode529 leetCode529 = new LeetCode529();
        char[][] temp = new char[][]{{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};

        char[][] chars = leetCode529.updateBoard(temp, new int[]{3, 0});
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                System.out.print(chars[i][j] + " ");
            }
            System.out.println();
        }
    }
}
