package day08;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 100. 相同的树
 * @author novo
 * @date 2022/3/6-20:19
 */
public class SameTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    // dfs
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 都为null
        if (p == null && q == null) {
            return true;
        }
        // 仅有一个为null
        if (p == null || q == null) {
            return false;
        }
        // 都不为null且值不相等
        if (p.val != q.val) {
            return false;
        }
        // 递归子树是否相等
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTree1(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while(!queue.isEmpty()) {
            TreeNode temp1 = queue.poll();
            TreeNode temp2 = queue.poll();
            // 都为null
            if (temp1 == null && temp2 == null) {
                // return true; 不能直接return true 可能出现p:[1] q:[1,null,2]这种情况
                continue;
            }
            // 仅有一个为null
            if (temp1 == null || temp2 == null) {
                return false;
            }
            // 都不为null且值不等
            if (temp1.val != temp2.val) {
                return false;
            }
            queue.offer(temp1.left);
            queue.offer(temp2.left);

            queue.offer(temp1.right);
            queue.offer(temp2.right);
        }
        return true;
    }
}
