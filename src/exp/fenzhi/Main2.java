package exp.fenzhi;

import java.util.*;

/**
 * 分治法求众数及其重数
 *
 * @author zwx
 * @date 2022-05-18 10:46
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = read.nextInt();
        }

        //int[] test = gennerateArray(20, 100);
        Arrays.sort(nums);
        //System.out.println(Arrays.toString(test));
        binarySearch(nums, 0, nums.length - 1);
        System.out.println("众数: " + maxNum + " 重数: " + maxCount);
        force(nums);
    }

    private static int maxCount = 0; // 记录众数的重数
    private static int maxNum = Integer.MAX_VALUE; // 记录众数

    // 返回区间[l, r]的中位数的重数及中位数的两个边界
    public static int[] getMidCount(int[] nums, int mid, int l, int r) {
        int leftCount = 0;
        int rightCount = 0;
        while (nums[l] != nums[mid]) {
            l++;
            leftCount++;
        }
        while (nums[r] != nums[mid]) {
            r--;
            rightCount++;
        }
        return new int[]{leftCount, rightCount, r - l + 1};
    }


    public static void binarySearch(int[] nums, int l, int r) {
        int mid = l + (r - l) / 2;
        int[] info = getMidCount(nums, mid, l, r);
        int midCount = info[2];
        if (midCount > maxCount) {
            maxCount = midCount;
            maxNum = nums[mid];
        }
        // 如果左边的数大于此时中间数的重数才有可能是众数
        if (info[0] > midCount) {
            binarySearch(nums, l, mid - 1);
        }
        // 如果右边的数大于此时中间数的重数才有可能是众数
        if (info[1] > midCount) {
            binarySearch(nums, mid + 1, r);
        }
    }

    // 暴力枚举
    public static void force(int[] nums) {
        int maxNum = -1;
        int maxCount = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int x : nums) {
            if (map.containsKey(x)) {
                map.put(x, map.get(x) + 1);
            } else {
                map.put(x, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxNum = entry.getKey();
            }
        }
        System.out.println("众数: " + maxNum + " 重数: " + maxCount);
    }


    public static int[] gennerateArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

}
