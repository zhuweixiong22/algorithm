package slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * 219. 存在重复元素 II
 * @author novo
 * @date 2022/4/12-23:30
 */
public class LeetCode219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        // 滑动窗口
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(i - k - 1);
            }
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}
