package arr;

import java.util.Arrays;

/**
 * @author zwx
 * @date 2022/4/20-23:16
 */
public class LeetCode167 {
    // 暴力枚举
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (i != j && numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[0];
    }

    // 枚举另一个数使用二分 时间复制度O(n * lgN)
    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int value = target - numbers[i];
            int res = binarySearch(numbers, value, i);
            if (res != -1) {
                return new int[]{i + 1, res + 1};
            }
        }
        return new int[0];
    }

    // 双指针
    public int[] twoSum3(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) return new int[]{left + 1, right + 1};
            else if (numbers[left] + numbers[right] > target) right--;
            else left++;
        }
        return new int[0];
    }

    private int binarySearch(int[] nums, int target, int other) {
        int l = 0;
        int r = nums.length - 1;
        int mid;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (nums[mid] == target && mid != other) return mid;
            else if (nums[mid] > target) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        /*int[] nums = new int[30000];
        Arrays.fill(nums, -1);
        nums[29999] = 1;
        nums[29998] = 1;*/

        int[] nums = new int[]{5, 25, 75};
        LeetCode167 leetCode167 = new LeetCode167();
        System.out.println(Arrays.toString(leetCode167.twoSum2(nums, 100)));
    }
}
