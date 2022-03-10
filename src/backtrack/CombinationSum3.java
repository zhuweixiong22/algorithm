package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 *
 * @author novo
 * @date 2022/2/8-21:47
 */
public class CombinationSum3 {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k == 0) {
            return res;
        }
        backTracking(k, 1, n);
        return res;
    }

    public void backTracking(int k, int index, int n) {
        if (path.size() == k) {
            if(n == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = index; i <= 9; i++) {
            path.add(i);
            backTracking(k, i + 1, n - i);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new CombinationSum3().combinationSum3(3, 9);
        System.out.println(lists);
    }
}
