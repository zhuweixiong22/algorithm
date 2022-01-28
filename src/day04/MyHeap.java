package day04;

import jdk.nashorn.internal.ir.CallNode;

import java.util.PriorityQueue;

import static day04.SortColor.swap;

/**
 * @author novo
 * @date 2022/1/28-22:48
 */
public class MyHeap {
    public static class MaxHeap {
        private int[] heap;
        private int heapSize;

        public MaxHeap(int limit) {
            heap = new int[limit];
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == heap.length;
        }

        public void push(int value) {
            if (heapSize == heap.length) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            int res = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return res;
        }

        // 上浮
        private void heapInsert(int[] arr, int index) {
            // 父 （i - 1）/2 注意边界不能写 <= 因为index等于0 循环也要停
            // 因为 arr[0] 不会大于 arr[(0 - 1) /2] ==>不会大于本身
            while (arr[index] > arr[(index - 1) >> 1]) {
                swap(arr, index, (index - 1) >> 1);
                index = (index - 1) >> 1;
            }
        }

        // 下沉
        private void heapify(int[] arr, int index, int heapSize) {
            int leftChild = index << 1 + 1;
            // while只需要判断是否有左孩子，因为如果没有左孩子，右孩子肯定也没有
            while (leftChild < heapSize) {
                // 寻找较大孩子 右孩子存在且右孩子大于左孩子的话返回右孩子
                int largest = leftChild + 1 < heapSize && arr[leftChild + 1] > arr[leftChild] ? leftChild + 1 : leftChild;
                if (arr[largest] > arr[index]) {
                    swap(arr,largest,index);
                }
                index = largest;
                leftChild = index << 1 + 1;
            }
        }
    }

    public static void main(String[] args) {
        // 默认升序 所以是小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> b - a);
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        heap.add(7);
        //heap.add(0);
        heap.add(7);
        heap.add(7);
        //heap.add(0);
        System.out.println(heap.peek());
    }
}
