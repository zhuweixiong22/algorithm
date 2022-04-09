package hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *
 * @author novo
 * @date 2022/4/9-22:45
 */
public class LeetCode1 {
    // 指针对撞
    public int[] twoSum(int[] nums, int target) {
        // 第二维记录原始位置
        int[][] arr = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        // 排序后使用双指针
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);
        int left = 0;
        int right = arr.length - 1;
        int[] res = new int[2];
        while (left < right) {
            if (arr[left][0] + arr[right][0] == target) {
                res[0] = left;
                res[1] = right;
                break;
            } else if (arr[left][0] + arr[right][0] > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

    // 哈希表
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        // 注意为什么不能一次先将数都放入哈希表中 因为所给数组可能会有重复的数字会被覆盖
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                res[0] = i;
                res[1] = map.get(complement);
                break;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
