package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 堆排序
 *
 * @author novo
 * @date 2022/3/19-21:14
 */
public class HeapSort {
    // 大根堆 堆排序完成升序
    public void heapSort(int[] data) {
        if (data.length < 2) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            siftUp(i, data);
        }

        // 形成大根堆后，开始原地排序
        int heapSize = data.length;
        for (int i = heapSize - 1; i >= 0; i--) {
            // 假设依次弹出堆顶到数组末尾 堆大小减一，一直维护堆
            swap(data, 0, i);
            heapify(data, 0, --heapSize);
        }
    }

    // 上浮 调整的时间为 logN 树的高度
    private void siftUp(int index, int[] data) {
        // (i - 1) / 2 为i的父结点
        // while停止的条件 1 arr[index] 不比父结点大 2 arr[index] 已经为堆顶
        while (data[index] > data[(index - 1) / 2]) {
            swap(data, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 下沉 调整的时间为 logN 树的高度
    private void heapify(int[] data, int index, int heapSize) {
        int leftChild = 2 * index + 1;
        // while只需要判断是否有左孩子，因为如果没有左孩子，右孩子肯定也没有
        // 因为堆是从0索引开始的所以只能小于
        while (leftChild < heapSize) {
            // 寻找较大孩子 右孩子存在且右孩子大于左孩子的话返回右孩子
            int largest = leftChild + 1 < heapSize && data[leftChild + 1] > data[leftChild] ? leftChild + 1 : leftChild;

            // 注意要break掉避免死循环 只有arr[largest] > arr[index] 才继续交换
            if (data[largest] <= data[index]) {
                break;
            }
            swap(data, largest, index);
            index = largest;
            leftChild = 2 * index + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
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
            HeapSort heap2 = new HeapSort();
            heap2.heapSort(arr1);

            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
