package 剑指offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 前 n 个数字二进制中 1 的个数
 *
 * @author zwx
 * @date 2022-06-03 21:49
 */
public class Offer003 {

    // 动态规划
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // 如果是奇数 则该数1的个数等于前一个数+1
            if ((i & 1) == 1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                // 如果是偶数 那么最低位为0 去掉低位0（右移一位）不影响1的位数，所以等于 x/2 的1的位数
                dp[i] = dp[i >> 1];
            }
        }
        return dp;
    }

    /*public int[] countBits(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i <= n; i++) {
            String str = toBin(i);
            System.out.println(i + " " + str);
            int count = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '1') count++;
            }
            list.add(count);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private String toBin(int x) {
        if (x == 0) return "0";
        StringBuilder str = new StringBuilder();
        while (x > 0) {
            str.insert(0, x % 2);
            x /= 2;
        }
        return str.toString();
    }*/

    public static void main(String[] args) {
        Offer003 offer003 = new Offer003();
        System.out.println(Arrays.toString(offer003.countBits(5)));
    }
}
