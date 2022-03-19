package heap;

/**
 * 堆结构
 * @author novo
 * @date 2022/3/19-20:04
 */
public class MyHeap {
    private int heapSize;
    private int[] data;

    public MyHeap(int n) {
        data = new int[n];
        heapSize = 0;
    }

    public void add(int val) {
        data[heapSize] = val;
        siftUp(heapSize);
        heapSize++;
    }
    public int poll() {
        int res = data[0];
        swap(data, 0 , heapSize - 1);
        heapSize--;
        heapify(0);
        return res;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }
    // 上浮 调整的时间为 logN 树的高度
    private void siftUp(int index) {
        // (i - 1) / 2 为i的父结点
        // while停止的条件 1 arr[index] 不比父结点大 2 arr[index] 已经为堆顶
        while (data[index] > data[(index - 1) / 2]) {
            swap(data, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 下沉 调整的时间为 logN 树的高度
    private void heapify(int index) {
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
    public static void main(String[] args) {
        MyHeap myHeap = new MyHeap(10);
        myHeap.add(1);
        myHeap.add(-2);
        myHeap.add(-14);
        myHeap.add(4);
        myHeap.add(99);
        myHeap.add(34);
        while (!myHeap.isEmpty()) {
            System.out.println(myHeap.poll());
        }
    }
}
