package day16;

import java.util.HashMap;
import java.util.Map;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * <p>
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * @author novo
 * @date 2022/2/27-20:42
 */
public class TargetSum {
    // dfs通过
    private int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        dfs(nums, target, 0);
        return count;
    }

    private void dfs(int[] nums, int target, int index) {
        if (index == nums.length) {
            if (target == 0) {
                this.count++;
            }
            return;
        }
        // 添加负号
        dfs(nums, target - nums[index], index + 1);
        // 添加正号
        dfs(nums, target + nums[index], index + 1);
    }

    // 记忆化搜索
    // 由于target可能是负权值 所以不能直接使用一维来做缓存
    private Map<String, Integer> memory = new HashMap<>();

    public int findTargetSumWays1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return dfs1(nums, target, 0);
    }

    private int dfs1(int[] nums, int target, int index) {
        if (index == nums.length) {
            if (target == 0) {
                return 1;
            }
            return 0;
        }
        String key = index + "_" + target;
        if (!memory.containsKey(key)) {
            int res = dfs1(nums, target - nums[index], index + 1) + dfs1(nums, target + nums[index], index + 1);
            memory.put(key, res);
        }
        return memory.get(key);
    }

    // 动态规划
    public int findTargetSumWays2(int[] nums, int target) {
        int n = nums.length;
        int border = 0;
        for (int i = 0; i < n; i++) {
            border += Math.abs(nums[i]);
        }
        // 如果target的绝对值 大于边界的话 不存在方案
        if (Math.abs(target) > border) {
            return 0;
        }
        // target的范围只可能在[-border, border]间
        int[][] dp = new int[n + 1][2 * border + 1];
        // 因为target可能存在负权值 为了能够计算负权值 我们将所有dp的索引都做一个border的右偏移
        dp[0][0 + border] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = -border; j <= border; j++) {
                // 两个if保证索引值不为负
                if ((j - nums[i - 1]) + border >= 0) {
                    dp[i][j + border] += dp[i - 1][(j - nums[i - 1]) + border];
                }
                if ((j + nums[i - 1]) + border <= 2 * border) {
                    dp[i][j + border] += dp[i - 1][(j + nums[i - 1]) + border];
                }
            }
        }
        return dp[n][target + border];
    }

    // 动态规划优化 不使用偏移量 问题转化 + 降维
    public int findTargetSumWays3(int[] nums, int target) {
        int n = nums.length;
        int border = 0;
        for (int i = 0; i < n; i++) {
            border += Math.abs(nums[i]);
        }
        // x = (target + border) / 2 所以(target + border)要为偶数 否则也是没有方案 因为实际意义是要除以2不能下取整的 不可能有容量为小数的背包
        if ((target + border) % 2 == 1) {
            return 0;
        }
        // 如果target的绝对值 大于边界的话 不存在方案
        if (Math.abs(target) > border) {
            return 0;
        }
        int x = (target + border) / 2;
        int[] dp = new int[x + 1];
        // 背包为0时只有一种方案
        dp[0] = 1;
        // 遍历物品
        for (int i = 0; i < n; i++) {
            // 遍历背包
            for (int j = x; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[x];
    }

    public static void main(String[] args) {
        TargetSum targetSum = new TargetSum();
        int[] nums = new int[]{1};

        System.out.println(targetSum.findTargetSumWays(nums, 1));
    }
}
