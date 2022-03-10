package tree;

/**
 * @author novo
 * @date 2022/3/10-22:11
 */
public class LowestCommonAncestorOfABinarySearchTree {
    public class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // p、q都在root左侧
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // p、q都在root右侧
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // 其他情况 root就是所求
        return root;
    }
}
