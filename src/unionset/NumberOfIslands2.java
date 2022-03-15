package unionset;

import java.util.ArrayList;
import java.util.List;

/**
 * @author novo
 * @date 2022/2/5-14:09
 */
public class NumberOfIslands2 {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFindSet set = new UnionFindSet(m * n + 1);
        List<Integer> res = new ArrayList<>();
        int[][] directs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] position : positions) {
            int i = position[0];
            int j = position[1];
            if (!set.visited[i * n + j]){
                set.size++;
            }
            set.visited[i * n + j] = true;
            // 四个方向检索
            for (int[] direct : directs) {
                int x = i + direct[0];
                int y = j + direct[1];
                // x、y没有越界且已经变成岛屿
                if (x >= 0 && y >= 0 && x < m && y < n && set.visited[x * n + y]) {
                    // 注意合并的方法这里需要判断是否已经合并过 否则size不准
                    set.union2(i * n + j, x * n + y);
                }
            }
            res.add(set.size);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] positions = new int[][] {{0,0},{0,1},{1,2},{2,1}};
        List<Integer> list = new NumberOfIslands2().numIslands2(3, 3, positions);
        System.out.println(list);
    }
}
