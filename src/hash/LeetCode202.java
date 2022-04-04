package hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 *
 * @author novo
 * @date 2022/4/2-23:30
 */
public class LeetCode202 {
    public boolean isHappy(int n) {
        boolean isHappy = true;
        Set<Integer> set = new HashSet<>();

        while (n != 1) {
            // 如果set里有重复数字出现 说明出现循环
            if (set.contains(n)) {
                isHappy = false;
                break;
            }
            set.add(n);
            int nextN = 0;
            // 取每一个位置上的数字
            while (n != 0) {
                // 取个位数
                int one = n % 10;
                nextN += (one * one);
                // 右移
                n /= 10;
            }
            n = nextN;
        }
        return isHappy;
    }

    public static void main(String[] args) {
        LeetCode202 leetCode202 = new LeetCode202();
        System.out.println(leetCode202.isHappy(2));
    }
}
