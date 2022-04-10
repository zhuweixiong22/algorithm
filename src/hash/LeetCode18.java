package hash;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 *
 * @author novo
 * @date 2022/4/10-23:30
 */
public class LeetCode18 {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private boolean[] used;

    public List<List<Integer>> fourSum(int[] nums, int target) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(target, nums, 0);
        return res;
    }

    private void dfs(int target, int[] nums, int index) {
        if (path.size() == 4) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.add(nums[i]);
            used[i] = true;
            dfs(target - nums[i], nums, i + 1);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            // a去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                // b去重
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 可以进行剪枝
                // 获取当前最小值,如果最小值比目标值大 直接break
                // nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target
                if (nums[i] + nums[i + 1] > target - nums[i + 2] - nums[i + 3]) {
                    break;
                }
                // 获取当前最大值,如果最大值比目标值小，该次循环必然找不到
                // nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target
                if (nums[i] + nums[nums.length - 3] < target - nums[nums.length - 2] - nums[nums.length - 1]) {
                    continue; // 跳过该次循环，更换更大的nums[i]
                }

                // 退化为twoSum
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    // 减法避免溢出 或者使用long
                    if (nums[i] + nums[j] == target - nums[left] - nums[right]) {
                        res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));
                        // c、d去重
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (nums[i] + nums[j] > target - nums[left] - nums[right]) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode18 leetCode18 = new LeetCode18();
        int[] nums = new int[]{1, -2, -5, -4, -3, 3, 3, 5};
        System.out.println(leetCode18.fourSum2(nums, -11));
        //System.out.println(Integer.MAX_VALUE);
    }
}
