package day04;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author novo
 * @date 2022/1/28-23:19
 */
public class HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 逐个读入O(N*lonN)
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;

        // 排序的过程 靠首尾交换取得最大值从而手动打乱堆 再调用heapify来不断维护 不断获得最值
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            // 交换后堆大小减一 是逻辑删除该值
            swap(arr, 0, --heapSize);
        }
    }

    // 上浮
    public static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    // 下沉
    public static void heapify(int[] arr, int i, int heapSize) {
        int leftChild = 2 * i + 1;
        // while只需要判断是否有左孩子，因为如果没有左孩子，右孩子肯定也没有
        while (leftChild < heapSize) {
            // 寻找较大孩子 右孩子存在且右孩子大于左孩子的话返回右孩子
            int largest = leftChild + 1 < heapSize && arr[leftChild + 1] > arr[leftChild] ? leftChild + 1 : leftChild;

            // 注意要break掉避免死循环 只有arr[largest] > arr[i] 才继续交换
            if (arr[largest] <= arr[i]) {
                break;
            }
            swap(arr, largest, i);
            i = largest;
            leftChild = 2 * i + 1;
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
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
        System.out.println(-1 / 2);
        System.out.println(-1 >> 1);

        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(6);
        heap.add(8);
        heap.add(0);
        heap.add(2);
        heap.add(9);
        heap.add(1);

        /*while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }*/
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

            heapSort(arr1);

            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }
}
