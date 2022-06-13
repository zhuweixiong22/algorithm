package exp.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 求解仓库设置位置问题
 *  中位数原理
 * 3
 * 2 4
 * 5 3
 * 6 6
 * @author zwx
 * @date 2022-06-13 16:15
 */
public class Main5 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("输入商店个数及坐标：");
        int n = read.nextInt();
        int[] y = new int[n];
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = read.nextInt();
            y[i] = read.nextInt();
        }

        Arrays.sort(x);
        Arrays.sort(y);
        int mid_x = x[n / 2];
        int mid_y = y[n / 2];
        int res = 0;

        for (int i = 0; i < n; i++) {
            res += Math.abs(mid_x - x[i]);
            res += Math.abs(mid_y - y[i]);
        }

        System.out.println("仓库最佳坐标：" + "(" + mid_x + "," + mid_y + ")");
        System.out.println("最短路径：" + res);
    }
}
