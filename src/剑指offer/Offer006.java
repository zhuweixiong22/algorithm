package 剑指offer;

/**
 * 剑指 Offer II 006. 排序数组中两个数字之和
 * LeetCode167. 两数之和 II - 输入有序数组
 *
 * @author zwx
 * @date 2022-06-05 23:14
 */
public class Offer006 {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers.length == 0) {
            return new int[]{0, 0};
        }
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l, r};
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return null;
    }
}
