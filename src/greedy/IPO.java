package greedy;

import java.util.PriorityQueue;

/**
 * @author novo
 * @date 2022/2/3-18:44
 */
public class IPO {
    public static class Project {
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Project> minCostHeap = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        PriorityQueue<Project> maxProfitHeap = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);
        // 将项目都加入最小成本堆
        for (int i = 0; i < profits.length; i++) {
            minCostHeap.add(new Project(capital[i], profits[i]));
        }
        for (int i = 0; i < k; i++) {
            // 项目不为空且最小成本堆顶的成本小于本金时，加入最大收益堆
            while (!minCostHeap.isEmpty() && minCostHeap.peek().cost <= w) {
                maxProfitHeap.add(minCostHeap.poll());
            }
            // 最大收益堆为空 说明本金w可能小于成本或者项目已经都做完，直接返回w
            if (maxProfitHeap.isEmpty()) {
                return w;
            }
            w += maxProfitHeap.poll().profit;
        }
        return w;
    }
}
