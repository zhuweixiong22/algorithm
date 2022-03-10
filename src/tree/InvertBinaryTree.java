package tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转二叉树
 * @author novo
 * @date 2022/3/5-23:19
 */
public class InvertBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    // DFS
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 前序后序都可以翻转
        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }
    // 层序遍历 依然是自顶向下
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode temp = null;
        TreeNode cur = null;
        while(!queue.isEmpty()) {
            cur = queue.poll();
            temp = cur.right;
            cur.right = cur.left;
            cur.left = temp;

            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return root;
    }

    // 迭代
    public TreeNode invertTree2(TreeNode root) {
        TreeNode cur = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while(cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // 前序交换
                TreeNode temp = cur.left;
                cur.left = cur.right;
                cur.right = temp;

                stack.offer(cur);
                cur = cur.left;
            } else {
                cur = stack.poll();
                cur = cur.right;
            }
        }
        return root;
    }
}
