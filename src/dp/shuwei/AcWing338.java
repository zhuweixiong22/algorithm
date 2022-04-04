package dp.shuwei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 计数问题
 * @author novo
 * @date 2022/4/3-20:43
 */
public class AcWing338 {
    // 统计1~n 内 x出现的次数
    private static int forceCount(int n, int x) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j != 0; j /= 10) {
                if (j % 10 == x) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        while (read.hasNext()) {
            int a = read.nextInt();
            int b = read.nextInt();
            if (a == 0 && b == 0) {
                break;
            }
            // 确保a 小于 b
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            // 前缀和思想
            for (int i = 0; i < 10; i++) {
                System.out.print(count(b, i) - count(a - 1, i) + " ");
            }
            System.out.println();
        }
    }

    // 统计1~n这个区间 x出现的次数
    private static int count(int n, int x) {
        if (n == 0) {
            return 0;
        }
        // 存储每一位
        List<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(n % 10);
            n /= 10;
        }
        // 对 x == 0 做特判，如果 x == 0 i从次高位开始枚举
        int bit = (x == 0 ? 1 : 0);
        int bitSize = list.size();
        int res = 0;
        // 从高位开始枚举
        for (int i = bitSize - 1 - bit; i >= 0; i--) {
            // base 1 x不为最高位时才有这种情况
            if (i < bitSize - 1) {
                res += (getNum(list, bitSize - 1, i + 1)) * Math.pow(10, i);
                if (x == 0) {
                    // x == 0 时 可取个数为(getNum(list, bitSize - 1, i + 1)) - 1) * Math.pow(10, i)，所以要减回去
                    res -= Math.pow(10, i);
                }
            }

            // base 2.2 x == d
            if (x == list.get(i)) {
                res += getNum(list, i - 1, 0) + 1;
            } else if (x < list.get(i)) {
                // base 2.3 x < d
                res += Math.pow(10, i);
            }
        }
        return res;
    }

    private static int getNum(List<Integer> list, int l, int r) {
        int res = 0;
        for (int i = l; i >= r; i--) {
            res = res * 10 + list.get(i);
        }
        return res;
    }

    private static int pow10(int x) {
        int res = 1;
        while (x-- != 0) {
            res *= 10;
        }
        return res;
    }
}
