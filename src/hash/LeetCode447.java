package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 447. 回旋镖的数量
 *
 * @author novo
 * @date 2022/4/12-21:27
 */
public class LeetCode447 {
    // 暴力
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (j == i) continue;
                for (int k = 0; k < points.length; k++) {
                    if (k == j || k == i) continue;
                    double x = Math.pow(points[j][0] - points[i][0], 2) + Math.pow(points[j][1] - points[i][1], 2);
                    double y = Math.pow(points[k][0] - points[i][0], 2) + Math.pow(points[k][1] - points[i][1], 2);
                    if (x == y) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    // hash
    public int numberOfBoomerangs2(int[][] points) {
        int res = 0;
        // 对于每一个点point[i] 枚举其他点到point[i]的距离，将距离放入哈希表中 key=距离 value为与point[i]相距distance的点的个数
        for (int i = 0; i < points.length; i++) {
            // 注意对于每个点都是新的map
            Map<Integer, Integer> map = new HashMap<>();
            // 存入数据
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    int distance = (int) getDistance(points[i], points[j]);
                    map.put(distance, map.getOrDefault(distance, 0) + 1);
                }
            }

            // 查表
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int count = entry.getValue();
                // 能否找到至少有两个点与point[i]有相同的距离，才能构成回旋镖
                if (count >= 2) {
                    // 考虑顺序所以是排列 An2 从count中选两个点出来排列
                    res += count * (count - 1);
                }
            }
        }

        return res;
    }

    private double getDistance(int[] point1, int[] point2) {
        return Math.pow(point1[0] - point2[0], 2) + Math.pow(point1[1] - point2[1], 2);
    }

    public static void main(String[] args) {

    }
}
