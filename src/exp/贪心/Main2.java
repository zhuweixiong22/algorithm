package exp.greedy;

import java.util.Scanner;

/**
 * 删数问题
 * 给定n 位正整数a，去掉其中任意k≤n 个数字后，剩下的数字按原次序排列组成一个新的正整数。
 * 对于给定的n位正整数a 和正整数k，设计一个算法找出剩下数字组成的新数最小的删数方案。
 * 对于给定的正整数a，编程计算删去k个数字后得到的最小数。
 * 178543
 * 4
 * 13
 *
 * @author zwx
 * @date 2022-06-12 11:14
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String num = read.next();
        int k = read.nextInt();
        System.out.println(solution(num, k));
    }

    private static String solution(String num, int k) {
        if (k >= num.length()) {
            return "0";
        }
        StringBuilder str = new StringBuilder(num);
        while (k-- > 0) {
            // 贪心策略：寻找最近下降点
            int i = 0;
            while (i < num.length() - 1 && str.charAt(i) <= str.charAt(i + 1)) {
                i++;
            }
            // 若找不到最近下降点，则i停在末尾，写在循环外，确保必然会删除一个数
            str.delete(i, i + 1);
        }

        // 去除前导0
        while (str.length() > 1 && str.charAt(0) == '0') {
            str.delete(0, 1);
        }

        return str.toString();
    }
}
