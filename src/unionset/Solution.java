package unionset;

/**
 * @author novo
 * @date 2022/2/4-23:23
 */
class Solution {
    public static class UnionFindSet {
        private int[] parent;
        private int[] rank;

        public UnionFindSet(int size) {
            this.parent = new int[size];
            this.rank = new int[size];
            build(size);
        }

        public void build(int size) {
            for (int i = 0; i < size; i++) {
                rank[i] = 1;
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            if (x == y) {
                return;
            }
            x = find(x);
            y = find(y);
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
        }

        public int getUnionSize(int n) {
            int size = 0;
            for (int i = 0; i < n; i++) {
                if (find(i) == i) {
                    size++;
                }
            }
            return size;
        }
    }

    public static int numIslands(char[][] grid) {
        int[][] directs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = grid.length;
        int n = grid[0].length;
        int zero = 0;
        UnionFindSet set = new UnionFindSet(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int[] direct : directs) {
                        int x = i + direct[0];
                        int y = j + direct[1];
                        if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == '1') {
                            set.union(x * n + y, i * n + j);
                        }
                    }
                } else {
                    zero++;
                }
            }
        }
        return set.getUnionSize(m * n) - zero;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(numIslands(grid));
    }
}
