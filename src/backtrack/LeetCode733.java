package backtrack;

import tool.LeetCodeTool;

/**
 * 733. 图像渲染
 * @author zwx
 * @date 2022-05-05 15:01
 */
public class LeetCode733 {
    private int[][] directs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int m;
    private int n;
    private boolean[] visited;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image.length == 0) return null;
        m = image.length;
        n = image[0].length;
        visited = new boolean[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == sr && j == sc) {
                    int init = image[i][j]; // 记录初始的像素值
                    image[i][j] = newColor;
                    dfs(image, i, j, newColor, init);
                }
            }
        }
        return image;
    }

    private void dfs(int[][] image, int x, int y, int newColor, int init) {
        visited[n * x + y] = true;
        for (int[] direct : directs) {
            int newX = x + direct[0];
            int newY = y + direct[1];
            // 未被访问过的合法区域且像素值与初始坐标相同的相连像素点继续进行深搜
            if (inArea(newX, newY) && !visited[n * newX + newY] && image[newX][newY] == init) {
                image[newX][newY] = newColor;
                dfs(image, newX, newY, newColor, init);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        LeetCode733 leetCode733 = new LeetCode733();
        int[][] images = new int[][]{{0, 0, 0}, {0, 1, 0}};
        /*leetCode733.floodFill(images, 1, 0, 2);
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[0].length; j++) {
                System.out.print(images[i][j] + " ");
            }
            System.out.println();
        }*/
        System.out.println("================");
        images = new int[][]{{0, 0, 0}, {0, 0, 0}};
        leetCode733.floodFill(images, 0, 0, 2);
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[0].length; j++) {
                System.out.print(images[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(LeetCodeTool.getArr("[[0,0,0],[0,1,0]]"));
    }
}
