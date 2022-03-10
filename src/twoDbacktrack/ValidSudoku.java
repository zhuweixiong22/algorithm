package twoDbacktrack;

/**
 * 36. 有效的数独
 *
 * @author novo
 * @date 2022/2/12-17:49
 */
public class ValidSudoku {
    private boolean[][] row = new boolean[9][10];
    private boolean[][] col = new boolean[9][10];
    private boolean[][] cell = new boolean[9][10];

    public boolean isValidSudoku(char[][] board) {
        // 遍历棋盘
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 对已经填入的数字做校验
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    // 在三个区域内第一次遇到num 设置为true
                    if (!row[i][num] && !col[j][num] && !cell[i / 3 * 3 + j / 3][num]) {
                        row[i][num] = true;
                        col[j][num] = true;
                        cell[i / 3 * 3 + j / 3][num] = true;
                    } else {
                        // 否则说明在同一区域内有重复数字出现
                        return false;
                    }
                }
            }
        }
        // 遍历完没有非法数字
        return true;
    }
}
