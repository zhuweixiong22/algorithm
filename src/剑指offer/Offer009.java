package 剑指offer;

/**
 * 乘积小于 K 的子数组
 *
 * @author zwx
 * @date 2022-06-12 22:26
 */
public class Offer009 {

    // 滑动窗口
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        int mul = 1;
        int l = 0, r = -1;
        // 做到不重不漏 枚举区间的终点为r的情况和
        while (r < nums.length - 1) {
            mul *= nums[++r];
            // 找到终点为r 区间乘积小于k的最左端l
            // 因为值都为正整数，区间[l, r]乘积小于k，必然有区间[l + i, r](i = 1, 2, ..., l + i <= r)乘积小于k
            while (l <= r && mul >= k) {
                mul /= nums[l++];
            }
            res += (r - l + 1);
        }
        return res;
    }

    /*// 暴力枚举
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        int mul = 1;
        // 枚举起始元素
        for (int i = 0; i < nums.length; i++) {
            mul *= nums[i];
            if (mul < k) {
                res++;
            } else {
                mul = 1;
                continue;
            }
            // 枚举后面的元素
            for (int j = i + 1; j < nums.length; j++) {
                mul *= nums[j];
                if (mul < k) {
                    res++;
                } else {
                    // 要求连续子数组
                    break;
                }
            }
            mul = 1;
        }
        return res;
    }*/

    public static void main(String[] args) {
        Offer009 offer009 = new Offer009();
        int[] nums = new int[]{10,5,2,6};
        //int[] nums = new int[]{1, 2, 3};
        int k = 100;
        System.out.println(offer009.numSubarrayProductLessThanK(nums, k));
    }
}
