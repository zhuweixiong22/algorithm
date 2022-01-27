package day03;

import static day03.SmallSum.printArray;

/**
 * @author novo
 * @date 2022/1/27-13:10
 */
public class review {
    // 归并递归
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] mergeArr = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            mergeArr[i++] = (arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++]);
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
    }

    // 非递归
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int mergeSize = 1;
        while (mergeSize < arr.length) {
            int L = 0;
            while (L < arr.length) {
                if (L + mergeSize >= arr.length) {
                    break;
                }
                int M = mergeSize + L - 1;
                int R = M + Math.min(mergeSize, arr.length - 1 - M);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > arr.length / 2) {
                return;
            }
            mergeSize = (mergeSize << 1);
        }

    }

    public static int getSmallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return processGetSum(arr, 0, arr.length - 1);
    }

    public static int processGetSum(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return processGetSum(arr, L, mid) + processGetSum(arr, mid + 1, R) + mergeGetSum(arr, L, mid, R);
    }

    public static int mergeGetSum(int[] arr, int L, int M, int R) {
        int[] mergeArr = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        while (p1 <= M && p2 <= R) {
            res += arr[p2] > arr[p1] ? (R - p2 + 1) * arr[p1] : 0;
            mergeArr[i++] = arr[p2] > arr[p1] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            mergeArr[i++] = arr[p1++];
        }
        while (p2 <= R) {
            mergeArr[i++] =arr[p2++];
        }
        for (i = 0; i < mergeArr.length; i++) {
            arr[L + i] = mergeArr[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    res += arr[i];
                }
            }
        }
        return res;
    }
    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
        int testTime1 = 500000;
        int maxSize1 = 100;
        int maxValue1 = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime1; i++) {
            int[] arr1 = generateRandomArray(maxSize1, maxValue1);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
        int[] arr = {81, -4, 3, 12, -5, 0, 42, -57, 64, -52, -2, -24, -22, 71, 23, 17, -73, -11};
        mergeSort2(arr);
        printArray(arr);

        int[] arr4 = {1, 3, 5, 2, 4, 6};
        System.out.println(comparator(arr4));
        System.out.println(getSmallSum(arr4));
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (getSmallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
