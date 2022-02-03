package day10;

/**
 * 经典并查集
 *
 * @author novo
 * @date 2022/2/3-22:39
 */
public class UnionFindSet {
    private int[] parent;

    public UnionFindSet(int size) {
        this.parent = new int[size];
    }

    public void build(int n) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x){
            //return find(parent[x]);
            // 在返回的路上进行路径压缩 在每次查找时 把查找路径上(注意只压缩路径上的)的每个结点直接指向根节点
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int x,int y){
        if(x == y) {
            return;
        }
        // 将x接到y
        parent[find(x)] = find(y);
    }
}
