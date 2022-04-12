package hash;

import java.util.Arrays;

/**
 * LeetCode16. 最接近的三数之和
 * @author novo
 * @date 2022/4/11-19:48
 */
public class LeetCode16 {
    public int threeSumClosest(int[] nums, int target) {
        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                    if (sum == target) break;
                }
                // 注意去重为什么要写在下面而不是上面，因为上面的if执行完不一定是相等，还可能会执行下面的if，不像之前是else if
                // 所以要先判断大小关系，再移动指针，之前的是已经确定是相等，所以直接移动指针
                if (sum > target) {
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else {
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                }
            }
        }
        return res;
    }
}
