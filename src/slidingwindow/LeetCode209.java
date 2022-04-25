package slidingwindow;

import java.util.Arrays;

/**
 * 长度最小的子数组
 * @author zwx
 * @date 2022/4/24-22:11
 */
public class LeetCode209 {
    // 滑动窗口
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int res = nums.length + 1;
        for (int l = 0, r = -1; l < nums.length; ) {
            if (r + 1 < nums.length && sum < target) { // 因为使用的是++r 所以要判断是否越界
                sum += nums[++r];
            } else if (sum >= target) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l++];
            } else { // 结束条件
                break;
            }
        }
        return (res == nums.length + 1 ? 0 : res);
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int sum = 0;
        int res = nums.length + 1;
        int l = 0, r = -1;
        while (l < nums.length) {
            if (r + 1 < nums.length && sum < target) {
                sum += nums[++r];
            } else { // 不满足上诉条件就移动左边界，不管窗口内总值是否大于等于target
                sum -= nums[l++];
            }
            // 另外判断，就不用break
            if (sum >= target) res = Math.min(res, r - l + 1);
        }
        return (res == nums.length + 1 ? 0 : res);
    }

    // 前缀和+二分
    public int minSubArrayLen3(int target, int[] nums) {
        int res = nums.length + 1;
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            int preSumBoundAddOne = target + preSum[i];
            int boundAddOne = binarySearch(preSum, preSumBoundAddOne);
            if (boundAddOne < nums.length + 1) {
                res = Math.min(res, boundAddOne - i);
            }
        }
        return (res == nums.length + 1 ? 0 : res);
    }

    // 返回大于等于target的最小索引
    private int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int mid;
        int minGt = Integer.MAX_VALUE;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) {
                minGt = Math.min(minGt, mid);
                r = mid - 1;
            } else l = mid + 1;
        }
        return minGt;
    }

    // 暴力
    public int minSubArrayLen4(int target, int[] nums) {
        int sum = 0;
        int res = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (sum < target) sum += nums[j];
                if (sum >= target) {
                    res = Math.min(res, j - i + 1);
                    break; // 剪枝
                }
            }
            sum = 0;
        }
        return (res == nums.length + 1 ? 0 : res);
    }

    public static void main(String[] args) {
        LeetCode209 leetCode209 = new LeetCode209();
        int[] nums = new int[]{1, 4, 4};
        System.out.println(leetCode209.minSubArrayLen3(4, nums));
    }
}
