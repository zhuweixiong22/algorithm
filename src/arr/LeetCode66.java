package arr;

import dp.qujian.LeetCode516;
import simulate.LeetCode6;

import java.util.Arrays;

/**
 * @author zwx
 * @date 2022-05-19 23:40
 */
public class LeetCode66 {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = (digits[i] + carry);
            digits[i] = sum % 10;
            carry = sum / 10;
            // 如果当前位相加没有进位 则已经可以返回
            if (carry == 0) {
                return digits;
            }
        }
        // 如果最后一位还有进位则扩容数组
        if (carry == 1) {
            int[] copy = new int[digits.length + 1];
            copy[0] = 1;
            for (int j = 1; j < copy.length; j++) {
                copy[j] = digits[j - 1];
            }
            return copy;
        }
        return digits;
    }

    public static void main(String[] args) {
        LeetCode66 leetCode66 = new LeetCode66();
        int[] nums = new int[]{9};
        System.out.println(Arrays.toString(leetCode66.plusOne(nums)));
    }
}
