package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素
 *
 * @author novo
 * @date 2022/3/19-21:45
 */
public class TopKFrequentElements {
    // k 远小于 n 的 情况
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        // key：元素的值 value：元素出现的频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            // getOrDefault如果找不到key返回defaultValue
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        // 维护大小为k的小根堆（频率）
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.offer(entry);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // 返回堆中的元素的值
        for (int i = k - 1; i >= 0; i--) {
            res[i] = heap.poll().getKey();
        }
        return res;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        int[] res = new int[k];
        // key：元素的值 value：元素出现的频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            // getOrDefault如果找不到key返回defaultValue
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        int m = map.size();
        // 维护大小为m - k的大根堆（频率）
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.offer(entry);
            if (heap.size() > (m - k)) {
                res[index] = heap.poll().getKey();
                index++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFrequentElements topKFrequentElements = new TopKFrequentElements();
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        topKFrequentElements.topKFrequent2(nums, 2);
    }
}
