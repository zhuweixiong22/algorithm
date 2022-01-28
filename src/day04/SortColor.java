package day04;

import static day03.BiggerThanRightTwice.printArray;

/**
 * 荷兰国旗
 *
 * @author novo
 * @date 2022/1/28-15:49
 */
public class SortColor {

    public static void sortColors(int[] nums, int value) {
        int less = -1, index = 0, more = nums.length - 1;
        // [2,0,1] value == 1边界条件 index==more的时候还差一次判断 ，因为和more交换后的数是未知的，我们还需要进行一次判断
        // [1,0,2] 交换后 more已经指到0 index 为1 ++ 这时more和index同时指向1，
        while (index <= more) {
            if (nums[index] < value) swap(nums, ++less, index++);
            else if (nums[index] == value) index++;
            else if (nums[index] > value) swap(nums, index, more--);
        }
    }

    public static void swap(int[] nums, int i, int i1) {
        int temp;
        temp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = temp;

    }

    public static void main(String[] args) {
        int[] arr = {2,0, 1};
        sortColors(arr, 1);
        printArray(arr);
    }
}
