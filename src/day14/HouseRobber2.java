package day14;

import java.util.Arrays;

/**
 * 打家劫舍2
 * @author novo
 * @date 2022/2/20-22:07
 */
public class HouseRobber2 {
    public int rob(int[] nums) {
        // 这类的判断不能放在fun方法里，否则样例只有一个元素时，数组复制都为空，答案错误
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // 因为数组复制的原因 所以要保证原数组要大于等于3个元素，或者对dp数组进行偏移也可以
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 分两类转化成打家劫舍1 取最大值
        int[] nums1 = Arrays.copyOfRange(nums,0,nums.length - 1);
        int[] nums2 = Arrays.copyOfRange(nums, 1,nums.length);
        return Math.max(fun(nums1),fun(nums2));
    }
    // 动态规划 打家劫舍1的方法
    private int fun(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0],nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1],dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }
}
