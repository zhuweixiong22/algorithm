package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author novo
 * @date 2022/3/15-22:05
 */
public class BinaryTreeLevelOrderTraversal2 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode cur = root;
        queue.offer(cur);

        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < levelCount; i++) {
                cur = queue.poll();
                // 记录一层的信息
                levelList.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 每一层的数据采用头插法即可，忘记这个api可以在最后翻转一下res即可
            res.add(0, levelList);
        }
        return res;
    }
}
