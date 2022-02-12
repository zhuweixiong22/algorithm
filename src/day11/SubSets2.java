package day11;

import com.sun.xml.internal.txw2.output.DumpSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 给你一个整数数组 nums ，其中可能包含重复元素，
 * 请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * @author novo
 * @date 2022/2/9-14:57
 */
public class SubSets2 {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private boolean[] used;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backTracking(nums, 0);
        return res;
    }

    public void backTracking(int[] nums, int index) {
        res.add(new ArrayList<>(path));
        // 终止条件可以不写
        if (index == nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false){
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backTracking(nums, i + 1);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,4,4,1,4};
        List<List<Integer>> lists = new SubSets2().subsetsWithDup(nums);
        System.out.println(lists);
    }
}
