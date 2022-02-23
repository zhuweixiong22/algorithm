package day14;

/**
 * @author novo
 * @date 2022/2/20-22:50
 */
public class HouseRobber3 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0],res[1]);
    }
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0,0};
        }
        int[] leftInfo = dfs(root.left);
        int[] rightInfo = dfs(root.right);
        // 后序处理
        // 当前结点的状态只有两种，偷取或不偷 dp[0]代表不偷 dp[1]代表偷
        int[] dp = new int[2];
        // 当前结点不偷 说明可以选择偷窃左右子结点
        dp[0] = Math.max(leftInfo[0],leftInfo[1]) + Math.max(rightInfo[0],rightInfo[1]);
        // 当前结点选择偷 说明不能选择偷窃左右子结点
        dp[1] = root.val + leftInfo[0] + rightInfo[0];
        return dp;
    }
}
