package math;

import java.util.Scanner;

/**
 * 快速幂
 *
 * @author zwx
 * @date 2022-06-04 20:44
 */
public class AcWing875 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        while (n-- > 0) {
            int a = read.nextInt();
            int b = read.nextInt();
            int m = read.nextInt();
            System.out.println(quickPow(a, b, m));
        }
    }
/*
    // 快速幂 二分法递归版
    private static long quickPow(int a, int b, int m) {
        if (b == 0) {
            return 1;
        }
        if ((b & 1) == 1) {
            return a * quickPow(a, b - 1, m) % m;
        } else {
            long temp = quickPow(a, b / 2, m);
            return temp * temp % m;
        }
    }*/

    private static long quickPow(long a, long b, long m) {
        long res = 1;
        // 每一步乘法取模
        while (b != 0) {
            if ((b & 1) == 1) {
                res = res * a % m;
            }
            a = a * a % m; // 这里的乘法可能会溢出，所以函数用long来接收
            b >>= 1;
        }
        return res;
    }
}
