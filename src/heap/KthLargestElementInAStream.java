package heap;

import java.util.PriorityQueue;

/**
 * @author novo
 * @date 2022/3/19-19:11
 */
public class KthLargestElementInAStream {
    public static class KthLargest {
        private PriorityQueue<Integer> heap;
        private int k;
        public KthLargest(int k, int[] nums) {
            this.k = k;
            heap = new PriorityQueue<>();
            for (int x : nums) {
                // 调用自己写的add
                this.add(x);
            }
        }

        public int add(int val) {
            heap.offer(val);
            // 维护容量为k的小根堆 则堆顶的元素即为第k大的元素
            if (heap.size() > k) {
                heap.poll();
            }
            assert heap.size() > 0;
            return heap.peek();
        }
    }
    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 8, 2};
        KthLargest kthLargest = new KthLargest(3,nums);
        System.out.println(kthLargest.add(8));
    }
}
