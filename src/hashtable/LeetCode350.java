package hashtable;

import java.util.*;

/**
 * LeetCode350. 两个数组的交集 II
 * @author novo
 * @date 2022/4/1-23:19
 */
public class LeetCode350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int x : nums1) {
            list.add(x);
        }
        for (int x : nums2) {
            if (list.contains(x)) {
                remove(list, x);
                res.add(x);
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    // list删除指定元素 注意要逆序遍历 避免删除后长度发生变化引起越界
    private void remove(List<Integer> list, int val) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) == val) {
                list.remove(i);
                // 题目要求 一次删除一个
                break;
            }
        }
    }


    public int[] intersect2(int[] nums1, int[] nums2) {
        // nums中元素可以重复 所以次数有意义 可以用map
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int x : nums1) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (int x : nums2) {
            if (map.containsKey(x) && map.get(x) > 0) {
                // 用掉一次，减一
                map.put(x, map.get(x) - 1);
                list.add(x);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] intersect3(int[] nums1, int[] nums2) {
        // 假定已经排好序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        List<Integer> res = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 9, 5};
        int[] nums2 = new int[]{9, 4, 9, 8, 4};
        LeetCode350 leetCode350 = new LeetCode350();
        leetCode350.intersect2(nums1, nums2);
    }
}
