package arr;

import java.util.Arrays;

/**
 * 75. 颜色分类
 * @author novo
 * @date 2022/4/17-21:48
 */
public class LeetCode75 {
    // 计数排序
    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        int index = 0;
        for (int i = 0; i < count[0]; i++) {
            nums[index++] = 0;
        }
        for (int i = 0; i < count[1]; i++) {
            nums[index++] = 1;
        }
        for (int i = 0; i < count[2]; i++) {
            nums[index++] = 2;
        }
    }

    // partition
    public void sortColors2(int[] nums) {
        // 维护区间[0, zero]都为0
        int zero = -1;
        // 维护区间[two, n - 1]都为2
        int two = nums.length;

        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                swap(nums, --two, i);
            } else if (nums[i] == 0) {
                swap(nums, ++zero, i++);
            }
        }
    }

    private void swap (int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        LeetCode75 leetCode75 = new LeetCode75();
        int[] nums = new int[]{2};
        leetCode75.sortColors2(nums);
    }
}
