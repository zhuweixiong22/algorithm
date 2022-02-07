package day10;

/**
 * 经典并查集
 *
 * @author novo
 * @date 2022/2/3-22:39
 */
public class UnionFindSet {
    public int[] parent;
    private int[] rank;
    public boolean[] visited;
    public int size;
    public UnionFindSet(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        this.visited = new boolean[size];
        build(size);
    }

    public void build(int n) {
        for (int i = 0; i < n; i++) {
            visited[i] = false;
            rank[i] = 1;
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            //return find(parent[x]);
            // 在返回的路上进行路径压缩 在每次查找时 把查找路径上(注意只压缩路径上的)的每个结点直接指向根节点
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        if (x == y) {
            return;
        }
        // 将x接到y
        parent[find(x)] = find(y);
    }

    // 按秩合并
    public void union2(int x, int y) {
        x = find(x);
        y = find(y);
        // 两个结点代表结点相同 不用合并
        if (x == y) {
            return;
        }
        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;
            if (rank[x] == rank[y]) {
                rank[x]++;
            }
        }
        size--;
    }

    // 返回集合个数
    public int getUnionSize(int n) {
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (find(i) == i && visited[i]) {
                size++;
            }
        }
        return size;
    }

}
