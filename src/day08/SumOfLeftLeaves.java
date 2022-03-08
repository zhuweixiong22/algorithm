package day08;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 左叶子之和
 *
 * @author novo
 * @date 2022/3/8-16:24
 */
public class SumOfLeftLeaves {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 递归
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // curInfo表示当前root结点能获取到左叶子结点的信息
        int curInfo = 0;
        // Info表示root的孩子结点能获取到左叶子结点的信息
        int leftInfo = sumOfLeftLeaves(root.left);
        int rightInfo = sumOfLeftLeaves(root.right);
        // 判断当前节点是不是左叶子是无法判断的，必须要通过节点的父节点来判断其左孩子是不是左叶子
        // 左孩子不为空且左孩子是叶子结点
        if (root.left != null && root.left.left == null && root.left.right == null) {
            curInfo = root.left.val;
        }
        // 返回信息和
        return curInfo + leftInfo + rightInfo;
    }

    public int sumOfLeftLeaves1(TreeNode root) {
        TreeNode cur = root;
        Deque<TreeNode> stack = new LinkedList<>();
        int res = 0;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                if (cur.left != null && cur.left.left == null && cur.left.right == null) {
                    res += cur.left.val;
                }
                stack.offer(cur);
                cur = cur.left;
            } else {
                cur = stack.poll();
                cur = cur.right;
            }
        }
        return res;
    }

    // 层序遍历
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode cur = root;
        queue.offer(cur);
        int res = 0;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.left != null) {
                if (cur.left.left == null && cur.left.right == null) {
                    res += cur.left.val;
                }
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return res;
    }
}
