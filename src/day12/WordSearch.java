package day12;

import java.sql.Struct;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
 * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * @author novo
 * @date 2022/2/9-18:04
 */
public class WordSearch {
    // 也可以用二维数组
    private boolean[] visited;
    private int[][] directs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int m;
    private int n;

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        m = board.length;
        n = board[0].length;
        visited = new boolean[m * n];
        char[] wordChar = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 只要找到一个就提前return true
                if (dfs(board, wordChar, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, char[] wordChar, int index, int x, int y) {
        // 搜索到单词最后一个 需要返回结果 不需要再深搜 提前return
        if (index == wordChar.length - 1) {
            return board[x][y] == wordChar[index];
        }

        if (board[x][y] == wordChar[index]) {
            visited[x * n + y] = true;
            for (int[] direct : directs) {
                int newX = x + direct[0];
                int newY = y + direct[1];
                if (inArea(newX, newY) && !visited[newX * n + newY]) {
                    if (dfs(board, wordChar, index + 1, newX, newY)) {
                        return true;
                    }
                }
            }
            // 回溯 状态重置 保证在深搜的过程不会去搜重复的，但是退出深搜进行横向遍历的时候可以搜重复的
            visited[x * n + y] = false;
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }

    public static void main(String[] args) {
        // [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
        //"ABCCED"
        char[][] board = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(new WordSearch().exist(board, "ABCCED"));
    }
}
