package 剑指offer;

/**
 * 和大于等于 target 的最短子数组
 *
 * @author zwx
 * @date 2022-06-11 23:12
 */
public class Offer008 {
    // 滑动窗口
    public int minSubArrayLen(int target, int[] nums) {
        int res = nums.length + 1;
        int sum = 0;
        int l = 0, r = -1;
        while (l < nums.length) {
            if (r + 1 < nums.length && sum < target) {
                sum += nums[++r];
            } else {
                sum -= nums[l++];
            }

            if (sum >= target) {
                res = Math.min(res, r - l + 1);
            }
        }
        return res == nums.length + 1 ? 0 : res;
    }
}
