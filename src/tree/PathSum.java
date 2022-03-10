package tree;

/**
 * @author novo
 * @date 2022/3/7-22:57
 */
public class PathSum {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum);
    }
    private boolean dfs(TreeNode root, int targetSum) {
        // 为空直接返回false
        if (root == null) {
            return false;
        }
        // 如果是叶子结点判断当前这个叶子结点是否能凑够targetSum
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return dfs(root.left, targetSum - root.val) || dfs(root.right, targetSum - root.val);

    }
}
