package tree;

/**
 * 437. 路径总和 III
 * 不要求
 * @author novo
 * @date 2022/3/8-23:34
 */
public class PathSum3 {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private int res = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        dfs(root, targetSum);
        return res;
    }

    // 只有在确定路径起点结点这一步中 每个结点才有选与不选两种选择
    // 一旦路径起点被确定 后面的结点就一定要选上
    private void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        // 选择root当路径
        dfsHasRoot(root, targetSum);
        // 不选root当路径
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);
    }

    private void dfsHasRoot(TreeNode root, int targetSum) {
        // 注意因为结点值可能有负值，后面的路径可能还会满足res 所以不能直接return
        if (targetSum == root.val) {
            res += 1;
        }
        // 一旦root被选上当路径，也就是路径起点 root的子树一定要选，因为路径是连续的
        if (root.left != null) {
            dfsHasRoot(root.left, targetSum - root.val);
        }
        if (root.right != null) {
            dfsHasRoot(root.right, targetSum - root.val);
        }
    }

    public static void main(String[] args) {
        PathSum3 pathSum2 = new PathSum3();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        System.out.println(pathSum2.pathSum(root, 22));
    }
}
