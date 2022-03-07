package day08;

import day02.DoubleListToStackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author novo
 * @date 2022/3/6-12:33
 */
public class SymmetricTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        // 都为null
        if (left == null && right == null) {
            return true;
        }
        // 仅有一颗子树为null
        if (left == null || right == null) {
            return false;
        }
        // left和right不为空且值不相等
        if (left.val != right.val) {
            return false;
        }
        // 镜像递归两组： 左子树的左子树和右子树的右子树、左子树的右子树和右子树的左子树
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    // 层序遍历 队列
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while(!queue.isEmpty()) {
            TreeNode left = queue.remove();
            TreeNode right = queue.remove();
            // 都为null
            if (left == null && right == null) {
                continue;
            }
            // 仅有一个为null
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            // 两组放入顺序可以变 但是要保证依次放入的是一组镜像
            queue.add(left.left);
            queue.add(right.right);

            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
}
