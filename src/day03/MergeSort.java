package day03;


/**
 * 归并排序
 *
 * @author novo
 * @date 2022/1/26-16:09
 */
public class MergeSort {
    public static void merge(int[] arr, int L, int M, int R) {
        int[] mergeArr = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        // 合并两个有序数组
        while (p1 <= M && p2 <= R) {
            mergeArr[i++] = (arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++]);
        }
        // 要么p1越界要么p2越界 下面只会执行一个while，因为上面的条件是小于等于
        while (p1 <= M) { // p1没越界
            mergeArr[i++] = arr[p1++];
        }
        while (p2 <= R) { // p2没越界
            mergeArr[i++] = arr[p2++];
        }
        // 将合并好的mergeArr复制到arr对应的位置
        for (i = 0; i < mergeArr.length; i++) {
            arr[L + i] = mergeArr[i];
        }
        /*// 数组复制可以调用系统的函数
        System.arraycopy(mergeArr, 0, arr, L, mergeArr.length); */
    }
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }
    public static void process(int[] arr, int L, int R) {
        if(L == R) { // base case
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    // 非递归实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int mergeSize = 1;
        while (mergeSize < arr.length) { // 步长小于arr长度
            // 第一大轮左小组第一位的索引
            int L = 0;
            // 归并以当前步长的两倍划分每一大组 每一大组里包含左组和右组
            while (L < arr.length) { // L是要跳动的所以只要L不越界就可以
                if (mergeSize >= arr.length - L) { // 下一大组左组的个数都凑不够/或者刚刚好，直接跳出 但是它已经是有序的，只是没有合并成大组，因为凑不够就没有大组，把它留给步长*2之后的大组的右组直接合并
                    break;
                }
                int M = L + mergeSize - 1; // 左组的最后一位索引
                int R = M + Math.min(mergeSize, arr.length - M - 1); // 会有上面留下来的 变成有序的右组，直接合并
                // 将有序的左组和右组传进去合并排序（因为是从步长为1开始的，所以必然有序）
                // L.....M   M + 1.....R
                merge(arr, L, M, R);
                // 切换下一大组
                L = R + 1;
            }

            // 如果当前循环的步长已经大于数组长度的一半，说明右组不够 在上面已经合并完，
            // 整个数组已经排好序了，在大数据情况下 数组长度临近数据范围边界 为了防止步长*2后溢出变成负数，直接跳出
            if (mergeSize > (arr.length >> 1)) {
                break;
            }
            // 步长乘2
            mergeSize <<= 1;
        }
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
        if (arr1 == null) {
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
        /*int[] arr = {81, -4, 3, 12, -5, 0, 42, -57, 64, -52, -2, -24, -22, 71, 23, 17, -73, -11};
        mergeSort2(arr);
        printArray(arr);*/
    }
}
