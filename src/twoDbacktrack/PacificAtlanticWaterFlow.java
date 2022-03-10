package twoDbacktrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 417. 太平洋大西洋水流问题
 *
 * @author novo
 * @date 2022/2/11-15:23
 */
public class PacificAtlanticWaterFlow {
    private int[][] directs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] visitedP; // dfs太平洋状态
    private boolean[] visitedA; // dfs大西洋状态
    private int m;
    private int n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0) {
            return res;
        }
        m = heights.length;
        n = heights[0].length;
        visitedA = new boolean[m * n];
        visitedP = new boolean[m * n];

        // 对最外面一层dfs
        for (int i = 0; i < m; i++) {
            // 第一列太平洋
            dfs(heights, i, 0, visitedP);
            // 最后一列大西洋
            dfs(heights, i, n - 1, visitedA);
        }
        for (int j = 0; j < n; j++) {
            // 第一行太平洋
            dfs(heights, 0, j, visitedP);
            // 最后一行大西洋
            dfs(heights, m - 1, j, visitedA);
        }
        // 遍历整个岛屿 找出重合点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visitedA[i * n + j] && visitedP[i * n + j]) {
                    List<Integer> position = new ArrayList<>();
                    position.add(i);
                    position.add(j);
                    res.add(new ArrayList<>(position));
                }
            }
        }
        return res;
    }

    // 找出所有逆向的的终点即山顶位置，visitedP和visitedA的交集就是结果
    private void dfs(int[][] heights, int x, int y, boolean[] visited) {
        visited[x * n + y] = true;
        for (int[] direct : directs) {
            int newX = x + direct[0];
            int newY = y + direct[1];
            // 未被访问过的合法区域且heights[x][y] <= heights[newX][newY]即逆向
            if (inArea(newX, newY) && !visited[newX * n + newY] && heights[x][y] <= heights[newX][newY]) {
                dfs(heights, newX, newY, visited);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        List<List<Integer>> lists = new PacificAtlanticWaterFlow().pacificAtlantic(matrix);
        System.out.println(lists);
    }
}
