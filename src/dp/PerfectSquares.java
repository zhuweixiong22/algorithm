package dp;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 示例 2：
 * <p>
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * @author novo
 * @date 2022/2/16-23:08
 */
public class PerfectSquares {
    /**
     * 拉格朗日四平方和定理
     * 拉格朗日四平方和定理说明任何一个数，都可以由小于等于4个的完全平方数相加得到。
     * 所以dp值可以初始化为4
     *
     * @param n
     * @return
     */
    // 完全背包
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            int res = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                // 这里的1 + dp[i - j * j]用自顶向下更容易理解一些 因为可以拆出一个j*j的平方数所以得加1
                // 拆出一个平方数后 剩余的数是i - j * j，必然小于等于当前的i 这个之前已经求过了
                res = Math.min(res, 1 + dp[i - j * j]);
            }
            dp[i] = res;
        }
        return dp[n];
    }

    // BFS
    public int numSquares1(int n) {
        // visited数组用来剪枝 避免重复访问相同值的结点
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        visited[n] = true;
        int depth = 0;
        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            depth++;
            // 多叉数的BFS
            for (int i = 0; i < levelCount; i++) {
                int cur = queue.poll();
                // 访问当前节点的子节点，类比于二叉树的左右子节点 这里是多叉树
                for (int j = 1; j * j <= cur; j++) {
                    int next = cur - j * j;
                    if (next == 0) {
                        return depth;
                    }
                    // next的值未被访问才考虑这条路径，
                    // 因为如果这个值之前已经被访问过，说明之前有更短的路径达到这个值，那么当前这个比它长的路径就没有必要尝试下去了
                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
        }
        // 这个问题必有解因为n可以被n个1相加 正常情况是不会走到这里的 必然能在BFS中找到解
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        PerfectSquares perfectSquares = new PerfectSquares();
        System.out.println(perfectSquares.numSquares(3));
        String path = "/root/kkFileView-4.1.0-SNAPSHOT/file/temp/";
        System.out.println(path.substring(0, path.length() - 5));
    }
}
