package 剑指offer;

import day04.MyHeap;

import java.util.Map;

/**
 * 整数除法
 *
 * @author zwx
 * @date 2022-06-01 22:17
 */
public class Offer001 {
    /*public int divide(int a, int b) {
        if (b == 1) {
            return a;
        }
        if (b == -1) {
            // 溢出返回int正整数最大值 -2147483648 / -1 = 2147483647
            return a == Integer.MIN_VALUE ? Integer.MAX_VALUE : -a;
        }
        if (b == 0) {
            return 0;
        }

        // 结果符号 如果除数与被除数同符号 则为正 否则为负
        boolean isPlus = (a ^ b) >= 0; // 如果补码符号位不同异或为1 所以（a ^ b）为负数

        // 不能转化为正数运算，因为负最大数转成整数会溢出 -2147483648 / 2
        // 都转成负数运算
        a = a < 0 ? a : -a;
        b = b < 0 ? b : -b;
        int count = 0;
        // 负数运算所以a要小于等于b继续
        while (a <= b) {
            a -= b;
            count++;
        }
        return isPlus ? count : -count;
    }*/


    public int divide(int a, int b) {
        if (b == 1) {
            return a;
        }
        if (b == -1) {
            return a == Integer.MIN_VALUE ? Integer.MAX_VALUE : -a;
        }
        if (b == 0) {
            return 0;
        }

        boolean isPlus = (a ^ b) >= 0; // 如果补码符号位不同异或为1 所以（a ^ b）为负数

        // 都转成负数运算
        a = a < 0 ? a : -a;
        b = b < 0 ? b : -b;
        int count = 0;
        while (a <= b) {
            int base = 1;
            int step = b;
            // 将步长改为 2的次方，一次性计算出能被减的最大值step
            // a <= 2 * step 转成减法 避免两个负数相加溢出
            while (a - step <= step) {
                step <<= 1; // 倍数增长 题目限制不能用乘法
                base <<= 1;
            }
            a -= step;
            count += base;
        }
        return isPlus ? count : -count;
    }

    public static void main(String[] args) {
        Offer001 offer001 = new Offer001();
        System.out.println(offer001.divide(15, 2));
        System.out.println(offer001.divide(7, -3));
        System.out.println(offer001.divide(0, 1));
        System.out.println(offer001.divide(1, 1));
        int a = 1;
        int b = -2;
        System.out.println(a ^ b);
    }
}
