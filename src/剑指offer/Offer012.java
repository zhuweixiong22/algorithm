package 剑指offer;

import java.util.Arrays;

/**
 * 剑指 Offer II 012. 左右两边子数组的和相等
 * @author zwx
 * @date 2022-06-21 22:50
 */
public class Offer012 {
    // 前缀和
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        // 解题思路：
        // 我们肯定是需要O(n)的时间去枚举中心，那么我们优化的方案只能是从如何快速求出左侧和右侧的和
        // 而快速求一段区间和，自然可以想到用前缀和进行预处理
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // 枚举中心
        for (int i = 0; i < n; i++) {
            // [0, i - 1]区间和
            int leftSum = preSum[i] - preSum[0];
            // [i + 1, n - 1]区间和
            int rightSum = preSum[n] - preSum[i + 1];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    // 模拟
    public int pivotIndex2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }

        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            if (leftSum * 2 + nums[i] == sum) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1;
    }
}
