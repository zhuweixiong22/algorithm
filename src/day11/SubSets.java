package day11;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。
 * 返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * @author novo
 * @date 2022/2/8-22:14
 */
public class SubSets {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        backTracking(nums, 0, path);
        return res;
    }

    public void backTracking(int[] nums, int index, List<Integer> path) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 这是一种思路
        // 为什么不需要for循环 因为本质上就是一颗二叉树
        // 每个结点只有选和不选两种选择，而不是选什么元素的问题
        // 不选
        backTracking(nums, index + 1, path);

        // 选
        path.add(nums[index]);
        backTracking(nums, index + 1, path);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0};
        List<List<Integer>> subsets = new SubSets().subsets(nums);
        System.out.println(subsets);
    }
}
