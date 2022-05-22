package exp.fenzhi;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zwx
 * @date 2022-05-22 18:03
 */
public class Main5 {
    public static void main(String[] args) {
        int[] nums = gennerateArray(10, 10);
        //int[] nums = new int[]{1, 4, 5, 6, 7, 3, 5,8};
        System.out.println(Arrays.toString(nums));
        binarySearch(nums,0 ,nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    // 非递归
    public static void binarySearch(int[] nums) {
        int l = 0;
        int n = nums.length;
        int r = n - 1;
        int p;
        // 利用partition划分为两半，划分完毕后如果基准值恰好位于数组中间，则划分完成
        while (l < r) {
            p = partition(nums, l, r);
            if (p == n / 2) {
                break;
            } else if (p < n / 2) {
                // 基准值的位置小于中间位置，则向右继续划分
                l = p + 1;
            } else {
                r = p - 1;
            }
        }
    }

    // 递归版本
    public static void binarySearch(int[] nums, int l, int r) {
        int p = partition(nums, l, r);
        if (p == nums.length / 2) return;
        if (p < nums.length / 2) {
            binarySearch(nums, p + 1, r);
        } else {
            binarySearch(nums, l, p - 1);
        }
    }

    private static Random random = new Random();

    private static int partition(int[] nums, int l, int r) {
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

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static int[] gennerateArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }
}
