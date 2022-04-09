package hash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 15. 三数之和
 *
 * @author novo
 * @date 2022/4/9-23:29
 */
public class LeetCode15 {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private boolean[] used;

    public List<List<Integer>> threeSum(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(0, nums, 0);
        return res;
    }

    private void dfs(int target, int[] nums, int index) {
        if (path.size() == 3) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // 剪枝
            if (nums[i] > target) {
                break;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(target - nums[i], nums, i + 1);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> path = new ArrayList<>();
                        path.add(nums[i]);
                        path.add(nums[j]);
                        path.add(nums[k]);
                        set.add(new ArrayList<>(path));
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        LeetCode15 leetCode15 = new LeetCode15();
        System.out.println(leetCode15.threeSum2(nums));
    }
}
