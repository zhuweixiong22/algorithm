package tree;

/**
 * 二叉树最大直径
 * @author novo
 * @date 2022/2/1-21:18
 */
public class MaxDiameter {
    public class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    private int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        process(root);
        return res;
    }
    public int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = process(root.left);
        int rightDepth = process(root.right);
        res = Math.max(res, leftDepth + rightDepth);
        return Math.max(leftDepth,rightDepth) + 1;
    }
}
