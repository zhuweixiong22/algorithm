package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwx
 * @date 2022-05-08 17:22
 */
public class LeetCode797 {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if (graph.length == 0) {
            return res;
        }
        path.add(0);
        dfs(graph, 0);
        return res;
    }

    private void dfs(int[][] graph, int index) {
        if (index == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 当前点能到达哪些点
        int[] arr = graph[index];
        for (int i = 0; i < arr.length; i++) {
            path.add(arr[i]);
            // index 为当前点
            dfs(graph, arr[i]);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode797 leetCode797 = new LeetCode797();
        //int[][] graph = new int[][]{{1,2},{3},{3},{}};
        int[][] graph = new int[][]{{4,3,1},{3,2,4},{3},{4},{}};
        System.out.println(leetCode797.allPathsSourceTarget(graph));
    }
}
