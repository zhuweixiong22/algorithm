package day03;

/**
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 * 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 *
 * @author novo
 * @date 2022/1/27-22:33
 */
public class CountOfRangeSum327 {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] preSum = new long[nums.length + 1];
        preSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        return process(preSum, 0, preSum.length - 1, lower, upper);
    }

    public static int process(long[] arr, int L, int R, int lower, int upper) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return process(arr, L, M, lower, upper)
                + process(arr, M + 1, R, lower, upper)
                + merge(arr, L, M, R, lower, upper);
    }

    public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        int res = 0;
        int windowL = L;
        int windowR = L;
        // 求在j之前有多少个前缀和落在这个区间上 也就是左组有效
        // 并且因为单调递增所以两个边界min和max也是递增的 所以windowL和windowR两个指针不会回退 所以时间复杂度是O(N)
        for (int i = M + 1; i <= R; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (windowL <= M && arr[windowL] < min) {
                windowL++;
            }
            while (windowR <= M && arr[windowR] <= max) {
                windowR++;
            }
            // windowR - windowL 是因为设定[L  R)
            // preSum[windowR] <= max R多滑动了一格
            // 如果preSum[windowR] < max res就是 windowR - windowL + 1
            res += windowR - windowL;
        }

        long[] mergeArr = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            mergeArr[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            mergeArr[i++] = arr[p1++];
        }
        while (p2 <= R) {
            mergeArr[i++] = arr[p2++];
        }
        for (i = 0; i < mergeArr.length; i++) {
            arr[L + i] = mergeArr[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] nums, int lower, int upper) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            long sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= lower && sum <= upper) {
                    res++;
                }
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (countRangeSum(arr1, -2, 15) != comparator(arr2, -2, 15)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
