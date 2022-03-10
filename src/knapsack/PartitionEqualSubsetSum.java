package knapsack;

/**
 * @author novo
 * @date 2022/2/22-22:21
 */
public class PartitionEqualSubsetSum {
    // dfs
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // 最基本的条件所有子集的和为偶数才能分割
        if (sum % 2 != 0) {
            return false;
        }
        return dfs(nums, n - 1, sum / 2);
    }

    // 使用nums[0...index]，是否可以完全填充一个容量为c的背包
    private boolean dfs(int[] nums, int index, int c) {
        if (c == 0) {
            return true;
        }
        if (index < 0 || c < 0) {
            return false;
        }
        return dfs(nums, index - 1, c)
                || dfs(nums, index - 1, c - nums[index]);
    }

    // 记忆化搜索
    // -1 表示未计算; 1 表示可以填充; 0 表示不可以填充
    private int[][] memory;

    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // 最基本的条件所有子集的和为偶数才能分割
        if (sum % 2 != 0) {
            return false;
        }
        memory = new int[n][sum / 2 + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                memory[i][j] = -1;
            }
        }
        return dfs2(nums, n - 1, sum / 2);
    }

    // 使用nums[0...index]，是否可以完全填充一个容量为c的背包
    private boolean dfs2(int[] nums, int index, int c) {
        if (c == 0) {
            return true;
        }
        if (index < 0 || c < 0) {
            return false;
        }
        if (memory[index][c] == -1) {
            memory[index][c] = ((dfs2(nums, index - 1, c)
                    || dfs2(nums, index - 1, c - nums[index]))) ? 1 : 0;
        }
        return memory[index][c] == 1;
    }

    public boolean canPartition3(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        // 在递推的过程中肯定是从小问题去向大问题逐步递推 所以不牵涉到判断某一个值是否被计算过 这里可以使用boolean
        // 因为肯定是没有被计算过的
        int C = sum / 2;
        boolean[] dp = new boolean[C + 1];
        // 初始化 最基础的问题 对于0索引这个物品是否能将容量为j的背包填满
        for(int j = 0; j <= C; j++) {
            dp[j] = (nums[0] == j);
        }

        for (int i = 1; i < n; i++) {
            for (int j = C; j >= nums[i]; j--) {
                // dp[j]能被填满或者dp[j - nums[i]]这个背包加上当前的nums[i]可以被填满
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[C];
    }
}
