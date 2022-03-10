package houserobber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 打家劫舍
 *
 * @author novo
 * @date 2022/2/20-15:14
 */
public class HouseRobber {
    // 回溯
    private List<Integer> res = new ArrayList<>();
    private int path;

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        backTracking(nums, 0);
        Collections.sort(res);
        return res.get(res.size() - 1);
    }

    private void backTracking(int[] nums, int index) {
        if (index >= nums.length) {
            res.add(path);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            path += nums[i];
            // 注意传的是i + 2 而不是index + 2
            backTracking(nums, i + 2);
            path -= nums[i];
        }
    }

    // 记忆化搜索
    private int[] memory;

    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        memory = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memory[i] = -1;
        }
        return backTracking2(nums, 0);
    }

    private int backTracking2(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        if (memory[index] == -1) {
            int res = 0;
            for (int i = index; i < nums.length; i++) {
                res = Math.max(res, nums[i] + backTracking2(nums, i + 2));
            }
            memory[index] = res;
        }
        return memory[index];
    }

    // 动态规划
    // 转移方程f(0) = max{ v(0) + f(0 + 2), v(1) + f(1 + 2), v(2) + f(2 + 2), ..., v(n - 3) + f(n - 3 + 2), v(n - 2), v(n - 1)}
    // 自底向上就是上面方程的从右到左
    public int rob3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        // 最基础的子问题考虑抢劫[n - 1, n - 1]只有一个房子
        dp[n - 1] = nums[n - 1];
        // 自底向上
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // 注意索引越界
                dp[i] = Math.max(dp[i], nums[j] + (j + 2 < n ? dp[j + 2] : 0));
            }
        }
        return dp[0];
    }

    // 另一种状态的定义 考虑偷取[0 ... x]的房子
    //那么对于每个x只有两个状态，偷和不偷
    //可以推出状态转移方程：
    //取房子x：dp[i] = dp[i - 1]
    //不偷取房子x：dp[i] = dp[i - 2] + nums[i]
    public int rob4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 防越界
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[] dp = new int[n];
        // 最基础的子问题
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0],nums[1]);
        for (int i = 2; i < n; i++){
            // 对于房子i 在偷与不偷中选择一个最大值
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 9, 3, 1};
        HouseRobber houseRobber = new HouseRobber();
        System.out.println(houseRobber.rob(nums));
        System.out.println(houseRobber.rob2(nums));
    }
}
