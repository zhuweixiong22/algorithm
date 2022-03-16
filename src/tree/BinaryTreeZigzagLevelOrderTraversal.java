package tree;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 * @author novo
 * @date 2022/3/16-21:57
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        int index = 1;
        queue.offer(cur);
        while (!queue.isEmpty()) {
            List<Integer> levelList = new LinkedList<>();
            int levelCount = queue.size();
            for (int i = 0; i < levelCount; i++) {
                cur = queue.poll();
                if (index % 2 == 1) {
                    // 奇数层 左到右接收
                    levelList.add(cur.val);
                } else {
                    // 偶数层 右到左接收
                    levelList.add(0, cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(levelList);
            index++;
        }
        return res;
    }

}
