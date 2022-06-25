package 剑指offer;

/**
 * 剑指 Offer II 013. 二维子矩阵的和
 * @author zwx
 * @date 2022-06-22 23:02
 */
public class Offer013 {
    class NumMatrix {
        private int[][] preSum;
        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            preSum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }

            // preSum[i][j]为 matrix[i - 1][j - 1]的二维前缀和
        }

        public int sumRegion(int x1, int y1, int x2, int y2) {
            // 求某一段区域和 [i, j] 的模板是 preSum[x2][y2] - preSum[x1 - 1][y2] - preSum[x2][y1 - 1] + preSum[x1 - 1][y1 - 1];
            // 但由于我们源数组下标从 0 开始，因此要在模板的基础上偏移
            x1++;
            x2++;
            y1++;
            y2++;
            return preSum[x2][y2] - preSum[x1 - 1][y2] - preSum[x2][y1 - 1] + preSum[x1 - 1][y1 - 1];
        }
    }
}
