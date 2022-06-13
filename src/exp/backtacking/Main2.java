package exp.backtacking;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author zwx
 * @date 2022-06-08 10:14
 */
public class Main2 {
    private static List<Integer> res = new ArrayList<>();
    private static int[][] directs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static boolean[] visited = new boolean[3 * 3];
    private static int[][] nums = new int[3][3];

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                nums[i][j] = -1;
            }
        }

        dfs(0, 0, 0);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0 ; j < nums.length; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
     }

    private static void dfs(int index, int x, int y) {
        if (index == 8) {
            return;
        }
        visited[x * 3 + y] = true;

        for (int i = 1; i <= 9; i++) {
            nums[x][y] = i;
            for (int[] direct : directs) {
                int newX = x + direct[0];
                int newY = y + direct[1];
                if (inArea(newX, newY) && nums[newX][newY] != -1 &&  isPrime(nums[newX][newY] + nums[x][y])) {
                    dfs(index + 1, newX, newY);
                    nums[x][y] = -1;
                }
            }
        }
    }

    private static boolean inArea(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 0; i <= n / i; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
