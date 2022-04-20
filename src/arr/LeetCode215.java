package arr;

import java.util.Random;

/**
 * 数组中的第 K 个最大元素
 * @author novo
 * @date 2022/4/18-20:19
 */
public class LeetCode215 {
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    private void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int i = l - 1;
        int j = r + 1;
        int pivot = nums[l + ((r - l) >> 1)];
        while (i < j) {
            do i++; while (nums[i] < pivot);
            do j--; while (nums[j] > pivot);
            if (i < j) {
                swap(nums, i, j);
            }
        }
        quickSort(nums, l, j);
        quickSort(nums, j + 1, r);
    }



    // partition
    public int findKthLargest2(int[] nums, int k) {
        return selectK(nums, 0, nums.length - 1, nums.length - k);
    }

    public int selectK(int[] nums, int l, int r, int k) {
        int p = partition(nums, l, r);
        if (k == p) return nums[p];
        return (p > k) ? selectK(nums, l, p - 1, k) : selectK(nums, p + 1, r, k);
    }

    private Random random = new Random();

    private int partition(int[] nums, int l, int r) {
        int p = l + random.nextInt(r - l + 1);
        swap(nums, l, p);
        int pivot = nums[l];
        int i = l, j = r + 1;
        while (i < j) {
            do i++; while (i < r && nums[i] < pivot);
            do j--; while (j > l && nums[j] > pivot);
            if (i < j) swap(nums, i, j);
        }

        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        LeetCode215 leetCode215 = new LeetCode215();
        int[] nums = new int[]{3,2,1,5,6,4};
        leetCode215.findKthLargest2(nums, 2);
    }
}
