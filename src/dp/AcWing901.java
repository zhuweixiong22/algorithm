package dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 滑雪
 * @author novo
 * @date 2022/4/8-21:38
 */
public class AcWing901 {
    static int[][] memory;
    static int[][] nums;
    static int[][] directs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int m;
    static int n;

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        m = read.nextInt();
        n = read.nextInt();
        nums = new int[m][n];
        memory = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = read.nextInt();
                memory[i][j] = -1;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(i, j));
            }
        }

        System.out.println(res);
    }

    private static int dfs(int x, int y) {
        if (memory[x][y] == -1) {
            // 注意赋初始值
            memory[x][y] = 1;
            for (int[] direct : directs) {
                int newX = x + direct[0];
                int newY = y + direct[1];
                if (inArea(newX, newY) && nums[x][y] > nums[newX][newY]) {
                    memory[x][y] = Math.max(memory[x][y], dfs(newX, newY) + 1);
                }
            }
        }
        return memory[x][y];
    }

    private static boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
