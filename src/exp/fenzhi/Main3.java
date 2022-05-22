package exp.fenzhi;

import java.util.Scanner;

/**
 * 求解逆序数问题
 * @author zwx
 * @date 2022-05-18 10:48
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = read.nextInt();
        }
        //int[] nums = gennerateArray(100, 100);
        force(nums);
        System.out.println(mergeSort(nums, 0, nums.length - 1));
    }

    // 逆序对问题 x右边的数有多少个数比x小
    private static int mergeSort(int[] nums, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        return mergeSort(nums, l, mid) + mergeSort(nums, mid + 1, r) + merge(nums, l, mid, r);
    }

    private static int merge(int[] nums, int l, int m, int r) {
        int[] mergeArr = new int[r - l + 1];
        int i = mergeArr.length - 1;
        int p1 = m;
        int p2 = r;
        int res = 0;
        while (p1 >= l && p2 > m) {
            // x右边的数有多少个数比x小 因为此时两个分支都是有序的 所以当 nums[p2]比nums[p1]小 则nums[p2]前面的数必然比nums[p1]小
            res += nums[p1] > nums[p2] ? (p2 - m) : 0;
            mergeArr[i--] = (nums[p1] > nums[p2] ? nums[p1--] : nums[p2--]);
        }
        while (p1 >= l) {
            mergeArr[i--] = nums[p1--];
        }
        while (p2 > m) {
            mergeArr[i--] = nums[p2--];
        }

        for (int j = 0; j < mergeArr.length; j++) {
            nums[l + j] = mergeArr[j];
        }
        return res;
    }

    public static void force(int[] nums) {
        int res = 0;
        int n = nums.length;
        // 暴力
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) res++;
            }
        }
        System.out.println(res);
    }


    public static int[]  gennerateArray(int len,int max){
        int[] arr=new int[len];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*max);
        }
        return arr;
    }
}
