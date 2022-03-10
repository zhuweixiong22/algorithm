package twoDbacktrack;

/**
 * 37. 解数独
 *
 * @author novo
 * @date 2022/2/12-11:41
 */
public class SudokuSolver {
    // 第二维的大小都设置成 10 是为了让 '1' 落在下标 1 的位置，'9' 落在下标 9 的位置
    // row第一维表示哪一行，第二维表示含有哪个数字
    private boolean[][] row = new boolean[9][10];
    // col第一维表示哪一列，第二维表示含有哪个数字
    private boolean[][] col = new boolean[9][10];
    // cell 的第一维表示在哪个3*3的宫内
    private boolean[][] cell = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        // 1 遍历一遍棋盘 将已有的数字设置状态
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    // 减去 '0' 是有 1 个位置的偏移
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    // [i][j] ==> [i / 3][j / 3]将9 * 9的小格缩小成3 * 3的大格
                    // [i / 3][j / 3] ==> [i / 3 * 3 + j / 3] 二维转一维
                    cell[i / 3 * 3 + j / 3][num] = true;
                }
            }
        }
        // 2 找到第一个为'.'的位置进行dfs搜索 这里至多只会执行n次，因为只是找出一个位置
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    backTracking(board, i * 9 + j);
                }
            }
        }
    }

    private boolean backTracking(char[][] board, int index) {
        // dfs到最后一个位置说明求解成功
        if (index == 9 * 9) {
            return true;
        }
        // 剪枝 找到下一个为'.'的一维值 否则会超时
        int next = index + 1;
        for (; next < 9 * 9; next++) {
            // 一维==>二维  i * n + j 所以二维坐标的i就等于一维值整除列数，j等于一维值模列数
            if (board[next / 9][next % 9] == '.') {
                break;
            }
        }

        int x = index / 9;
        int y = index % 9;
        for (int i = 1; i <= 9; i++) {
            if (!row[x][i] && !col[y][i] && !cell[x / 3 * 3 + y / 3][i]) {
                row[x][i] = true;
                col[y][i] = true;
                cell[x / 3 * 3 + y / 3][i] = true;
                // 将整型强制类型转换为字符型，JVM 会把数字当成字符的 ASCII 编码来处理
                // 所以要转传成正确的字符，要先加上48再强转，也是加上字符'0'
                board[x][y] = (char) ('0' + i);

                if (backTracking(board, next)) {
                    return true;
                }
                // 回溯，状态重置
                row[x][i] = false;
                col[y][i] = false;
                cell[x / 3 * 3 + y / 3][i] = false;
                board[x][y] = '.';
            }
        }

        // 当前解尝试错误
        return false;
    }

    public static void main(String[] args) {
        char[][] arr = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        new SudokuSolver().solveSudoku(arr);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        char i = 65;
        System.out.println(i);
    }
}
