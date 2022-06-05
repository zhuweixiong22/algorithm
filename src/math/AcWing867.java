package math;

import java.util.ArrayList;
import java.util.List;

/**
 * 分解质因数
 *
 * @author zwx
 * @date 2022-06-05 14:06
 */
public class AcWing867 {
    public static void main(String[] args) {
        System.out.println(divide2(24));
        divide(24);
        divide3(24);
    }


    // 试除法
    private static void divide(int n) {
        for (int i = 2; i <= n / i; i++) {
            // 这里的i一定是素数 因为当我们枚举到i的时候 n已经不含有 2 ~ i- 1之间的质因子
            // 且n % i == 0 所以i一定是素数
            if (n % i == 0) {
                // 消耗所有值为i的质因子
                while (n % i == 0) {
                    n /= i;
                    System.out.print(i + " ");
                }
            }
        }
        if (n > 1) {
            System.out.println(n);
        }
    }

    // 暴力枚举
    private static void divide3(int n) {
        for (int i = 2; i <= n; i++) {
            // 这里的i一定是素数 因为当我们枚举到i的时候 n已经不含有 2 ~ i- 1之间的质因子
            // 且n % i == 0 所以i一定是素数
            if (n % i == 0) {
                // 消耗所有值为i的质因子
                while (n % i == 0) {
                    n /= i;
                    System.out.print(i + " ");
                }
            }
        }
        System.out.println();
    }

    // 分治
    // 这里使用字符串返回优雅一点
    private static String divide2(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return divide2(i) + divide2(n / i);
            }
        }
        return n + " ";
    }
}
