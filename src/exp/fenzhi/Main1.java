package exp.fenzhi;

import java.util.Scanner;

/**
 * 求解查找假币问题
 * @author zwx
 * @date 2022-05-18 10:16
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = read.nextInt();
        }

        System.out.println("假币在第" + binarySearch(nums, 0, nums.length - 1) + "位");
    }

    private static int binarySearch(int[] nums, int l, int r) {
        if (r -l + 1 <= 2) {
            return nums[l] < nums[r] ? l : r;
        }
        int mid;
        // 如果长度为奇数 则分成三组
        if (((r - l + 1) & 1) == 1) {
            // nums[mid]单独作为一组
            mid = l + (r - l) / 2;
            int leftSum = getSum(nums, l, mid - 1);
            int rightSum = getSum(nums, mid + 1, r);
            // 如果其他两组重量相等 则nums[mid]就为假币
            if (leftSum == rightSum) {
                return mid;
            } else if (leftSum < rightSum){
                return binarySearch(nums, l, mid - 1);
            } else {
                return binarySearch(nums, mid + 1, r);
            }
        } else {
            // 长度为偶数分成两组
            mid = l + (r - l) / 2;
            int leftSum = getSum(nums, l, mid);
            int rightSum = getSum(nums, mid+ 1,r);
            if (leftSum < rightSum) {
                return binarySearch(nums, l, mid);
            } else if (leftSum > rightSum) {
                return binarySearch(nums, mid + 1, r);
            }
        }
        return -1;
    }

    private static int getSum(int[] nums, int l, int r) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
