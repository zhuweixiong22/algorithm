package hash;

import org.omg.CORBA.INTERNAL;

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

    // 排序+双指针
    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 将问题转化为twoSum
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                // 因为已升序，nums[i] > 0 而后面的数是大于等于nums[i]的 所以加起来必然大于零
                break;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 退化为求两数之和
            int target = 0 - nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    // 关键：去重 找到指向值不等的指针
                    // 比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6
                    // [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    // 哈希表
    public List<List<Integer>> threeSum4(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            // a去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            Set<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 2 && nums[j] == nums[j - 1] && nums[j - 1] == nums[j - 2]) {
                    //b去重，发生重复是第一个nums[j-2]已经insert到set中了，nums[j-1]用于判断c是否等于0（此时可能已经push到结果中了），而nums[i]我们已经用不到了，所以跳过
                    continue;
                }
                int target = 0 - (nums[i] + nums[j]);
                if (set.contains(target)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], target)));
                    // c去重
                    set.remove(target);
                } else {
                    set.add(nums[j]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        LeetCode15 leetCode15 = new LeetCode15();
        System.out.println(leetCode15.threeSum4(nums));
    }
}
