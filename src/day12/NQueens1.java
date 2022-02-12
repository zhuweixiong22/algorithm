package day12;

import com.sun.deploy.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author novo
 * @date 2022/2/11-16:33
 */
public class NQueens1 {

    /**
     * 以 4 皇后为例的棋盘
     * [00, 01, 02, 03]
     * [10, 11, 12, 13]
     * [20, 21, 22, 23]
     * [30, 31, 32, 33]
     * <p>
     * 因为数组索引从0开始 我们用索引i - j + n - 1代表处于哪一条左对角线上
     * diagMain[-3, -2, -1, 0, 1, 2, 3] ==> [0, 1, 2, ..., i - j + n - 1]
     * <p>
     * diagSub[0, 1, 2, 3, 4, 5, 6] 用i + j代表代表处于哪一条右对角线上
     */
    // 列状态 记录哪一列有皇后
    private boolean[] col;
    // 记录左上->右下的对角线状态 特点：对角线上每个元素i - j 相等
    private boolean[] diagMain;
    // 记录右上->左下的对角线状态 特点：对角线上每个元素i + j 相等
    private boolean[] diagSub;
    private List<List<String>> res = new ArrayList<>();
    // 每个完整结果皇后的列坐标
    private List<Integer> path = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        col = new boolean[n];
        diagMain = new boolean[2 * n - 1];
        diagSub = new boolean[2 * n - 1];
        backTracking(n, 0);
        return res;
    }

    // index为行坐标
    private void backTracking(int n, int index) {
        if (index == n) {
            // 将列坐标结果集转化为棋盘
            List<String> board = convert2board(path,n);
            res.add(board);
            return;
        }

        // 对列遍历 每一列选出一个皇后
        for (int j = 0; j < n; j++) {
            // 不在同一列、不在同一对角线
            if (!col[j] && !diagMain[index - j + n - 1] && !diagSub[index + j]) {
                path.add(j);
                col[j] = true;
                diagMain[index - j + n - 1] = true;
                diagSub[index + j] = true;

                backTracking(n, index + 1);
                // 回溯 状态重置
                path.remove(path.size() - 1);
                col[j] = false;
                diagMain[index - j + n - 1] = false;
                diagSub[index + j] = false;
            }
        }
    }

    // 将结果的列坐标转换成棋盘 path存储的是所有皇后的列坐标
    private List<String> convert2board (List<Integer> path, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] arr = new char[n];
            // 棋盘每一行先填充'.' 在将有皇后的位置修改为'Q'
            Arrays.fill(arr,'.');
            arr[path.get(i)] = 'Q';
            board.add(new String(arr));
        }
        return board;
    }

    private static void printBoard(List<String> board){
        for(String s: board)
            System.out.println(s);
        System.out.println();
    }
    public static void main(String[] args) {
        List<List<String>> lists = new NQueens1().solveNQueens(4);
        for (List<String> list :lists) {
            printBoard(list);
        }

    }
}
