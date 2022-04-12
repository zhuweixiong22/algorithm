package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 四数相加 II
 * @author novo
 * @date 2022/4/11-20:20
 */
public class LeetCode454 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 将c和d的组合存入哈希表中，因为与个数有关，选用map
        // 将时间复杂度O(n^4) 降低为O(n^2)
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                map.put(c + d, map.getOrDefault(c + d, 0) + 1);
            }
        }
        for (int a : nums1) {
            for (int b : nums2) {
                int target = 0 - a - b;
                if (map.containsKey(target)) {
                    // 这里虽然map里已经记录的不同的组合，但是a和b的和也可能相同，所以是叠加
                    res += map.get(target);
                }
            }
        }
        return res;
    }
}
