package day11;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。
 * 你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 *
 输入: candidates = [2,3,5], target = 8
 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * @author novo
 * @date 2022/2/8-15:42
 */
public class CombinationSum1 {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        backTracking(candidates, target);
        return res;
    }

    public void backTracking(int[] candidates, int target) {
        if (getSum(path) >= target) {
            if (getSum(path) == target) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = 0; i < candidates.length; i++) {
            // 保证有序(升序)选择 去重
            if (!path.isEmpty() && (path.get(path.size() - 1) > candidates[i])) {
                continue;
            }
            path.add(candidates[i]);
            backTracking(candidates, target);
            path.remove(path.size() - 1);
        }
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        backTracking2(candidates, 0, target);
        return res;
    }

    // 使用index
    public void backTracking2(int[] candidates, int index, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                // 没用排序 只能continue  如果用排序的话可以直接break
                continue;
            }
            path.add(candidates[i]);
            // 可以用i来控制每次的深搜都可以选所有值，而不是i+1 一次完整的深搜到尽头后才会++，又保证的下一次深搜不会选到重复的值
            backTracking2(candidates, i, target - candidates[i]);
            path.remove(path.size() - 1);
        }
    }

    public int getSum(List<Integer> path) {
        int sum = 0;
        for (int i : path) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 6, 7};
        List<List<Integer>> lists = new CombinationSum1().combinationSum(arr, 7);
        System.out.println(lists);
    }
}
