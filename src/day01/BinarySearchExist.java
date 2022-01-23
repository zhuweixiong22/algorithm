package day01;

import java.util.Arrays;

/**
 * 在一个有序数组中，找到某个数是否存在
 *
 * @author novo
 * @date 2022/1/23-20:25
 */
public class BinarySearchExist {
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L < R) { //循环条件是至少有两个数
            // 如果直接写(L + R) / 2可能会溢出
            // 比如说L R本身没有溢出但是相加之后就可能溢出
            // 所以要写成 L + (R - L) / 2
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            } else if (sortedArr[mid] < num) {
                L = mid + 1;
            }
        }
        //所以退出循环后 最后一个数我们没有校验
        return sortedArr[L] == num;
    }

    // for test
    public static boolean test(int[] sortedArr, int num) {
        for(int cur : sortedArr) {
            if(cur == num) {
                return true;
            }
        }
        return false;
    }
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 2) * Math.random()) - (int) (maxValue * Math.random());

        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != exist(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Failed!");
    }
}
