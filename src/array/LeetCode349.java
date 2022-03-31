package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author novo
 * @date 2022/3/31-23:13
 */
public class LeetCode349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        for (int x : nums1) {
            set.add(x);
        }

        for (int x : nums2) {
            if (set.contains(x)) {
                set.remove(x);
                res.add(x);
            }
        }
        // 转数组
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
