package tree;

import jdk.nashorn.internal.ir.LiteralNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 *
 * @author novo
 * @date 2022/3/16-23:17
 */
public class BinaryTreeRightSideView {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // BFS
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(cur);
        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            for (int i = 0; i < levelCount; i++) {
                cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                // 如果是一层中的最右侧的一个节点 加入值
                if (i == levelCount - 1) {
                    res.add(cur.val);
                }
            }
        }
        return res;
    }

    // dfs
    private List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView1(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 这个depth指的是上一层的depth 也就是说当前res的大小仍然等于上一层的深度，说明当前节点是该层出现的第一个结点
        // 我们控制先访问右子树 就可以获取右视图
        if (depth == res.size()) {
            res.add(root.val);
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }
}
