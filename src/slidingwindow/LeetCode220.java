package slidingwindow;

import java.util.Set;
import java.util.TreeSet;

/**
 * 220. 存在重复元素 III
 * @author novo
 * @date 2022/4/13-20:32
 */
public class LeetCode220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 维护长度为K+1的窗口
            if (i > k) {
                treeSet.remove((long) nums[i - k - 1]);
            }
            // 取最小上界
            Long x = treeSet.ceiling((long) nums[i] - (long) t);
            if (x != null && x <= (long) nums[i] + (long) t) {
                return true;
            }
            treeSet.add((long) nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 9, 1, 5, 9};
        LeetCode220 leetCode220 = new LeetCode220();
        System.out.println(leetCode220.containsNearbyAlmostDuplicate(nums, 2, 3));
    }
}
