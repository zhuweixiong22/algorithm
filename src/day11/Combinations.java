package day11;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 *
 * @author novo
 * @date 2022/2/8-14:19
 */
public class Combinations {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || k > n) {
            return res;
        }
        backTracking(n, k, 1);
        return res;
    }

    public void backTracking(int n, int k, int index) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // [1, n]的组合 所以index从1开始 <= n
        // 还剩 k - path.size() 个空位，所以[i...n]中至少要有k-path.size()个元素
        // 例如至少要有2个元素 i要<= n-1 至少要有1个元素 i <= n
        for (int i = index; i <= n - (k - path.size()) + 1; i++) {
            /*if (path.size() >= k) {
                break;
            }*/
            path.add(i);
            /*System.out.println("index:" + index);
            System.out.println("path: " + path);*/
            backTracking(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        List<List<Integer>> combine = new Combinations().combine(25, 6);
        System.out.println(System.currentTimeMillis() - l + "ms");
        //System.out.println(combine);
    }
}
