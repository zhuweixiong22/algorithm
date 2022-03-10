package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。 
 * candidates中元素可能重复用used数组
 * @author novo
 * @date 2022/2/8-17:27
 */
public class CombinationSum2 {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private boolean[] used;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        used = new boolean[candidates.length];
        // 按序搜索是避免 出现组合重复 [2, 2, 1], [2, 1, 2]
        // 而used数组是为了避免在同一层级上选到值一样的元素 第三位的2虽然来源不相等 但是他们的值是一样的 [2, 1, 2], [2, 1, 2]
        Arrays.sort(candidates);
        backTracking(candidates, 0, target);
        return res;
    }

    public void backTracking(int[] candidates, int index, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            // 去重 used[i - 1] == false 说明现在处在同一层 被回溯过不可以选
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            // 剪枝
            if (candidates[i] > target) {
                // 已经有序 可以break
                break;
                //continue;
            }
            used[i] = true;
            path.add(candidates[i]);
            backTracking(candidates, i + 1, target - candidates[i]);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2 ,2, 1, 2, 5};
        List<List<Integer>> lists = new CombinationSum2().combinationSum2(arr, 5);
        System.out.println(lists);
    }
}
