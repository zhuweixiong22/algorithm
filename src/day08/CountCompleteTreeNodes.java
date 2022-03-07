package day08;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 222. 完全二叉树的节点个数
 * @author novo
 * @date 2022/3/6-22:00
 */
public class CountCompleteTreeNodes {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    // 普通树的做法 遍历一遍
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    // 普通迭代
    public int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode cur = queue.poll();
                res++;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return res;
    }
    // O(logN*logN)比O(N)快
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        // 如果左子树深度等于右子树深度 说明左子树是满二叉树
        if (leftDepth == rightDepth) {
            return (1 << leftDepth) - 1 + countNodes2(root.right) + 1;
        } else {
            // 如果不等于 对于完全二叉树来说只能是左子树深度比右子树深度大1 说明右子树是满二叉树
            return (1 << rightDepth) - 1 + countNodes2(root.left) + 1;
        }
    }
    // 计算完全二叉树的深度
    private int getDepth(TreeNode root) {
        // 一直往左下遍历即可
        int depth = 0;
        while(root != null) {
            depth++;
            root = root.left;
        }
        return depth;
    }
}
