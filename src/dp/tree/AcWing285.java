package dp.tree;

import java.util.*;

/**
 * @author novo
 * @date 2022/4/6-12:51
 */
public class AcWing285 {
    static int N = 100000;

    static class Edge {
        int to;
        int next;

        Edge(int to, int next) {
            this.to = to;
            this.next = next;
        }
    }

    static Edge[] edges = new Edge[N]; // 边域
    static int[] head = new int[N]; // 顶点域
    static int index = 0;
    static boolean[] hasFather = new boolean[N];
    static int[][] dp = new int[N][2]; // 根节点为N这颗树的最大happy值
    static int[] happy = new int[N];

    // 插入一条边 (form, to)
    private static void insert(int from, int to) {
        // 链表头插
        // 新结点的next指向from的原头结点head[from] from的头结点再更新为新插入的结点
        edges[index] = new Edge(to, head[from]);
        head[from] = index;
        index++;
    }

    public static void main(String[] args) {
        Arrays.fill(head, -1);
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();

        for (int i = 1; i <= n; i++) {
            happy[i] = read.nextInt();
        }
        // 插入n-1条边
        for (int i = 0; i < n - 1; i++) {
            int l = read.nextInt();
            int k = read.nextInt();
            insert(k, l);
            hasFather[l] = true;
        }

        // 找出根节点 没有父节点的即为整棵树的根节点
        int root = 1; // 初始值为第一个结点
        while (hasFather[root]) {
            root++;
        }
        dfs(root);

        int res = Math.max(dp[root][0], dp[root][1]);
        System.out.println(res);
    }

    private static void dfs(int root) {
        dp[root][1] = happy[root];
        // 横向搜索子树
        for (int i = head[root]; i != -1; i = edges[i].next) {
            int to = edges[i].to;
            dfs(to);
            dp[root][0] += Math.max(dp[to][0], dp[to][1]);
            dp[root][1] += dp[to][0];
        }
    }
}
