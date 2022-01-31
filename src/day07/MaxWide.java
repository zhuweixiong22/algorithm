package day07;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 662. 二叉树最大宽度
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * @author novo
 * @date 2022/1/31-18:47
 */
public class MaxWide {
    public static class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int val;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode cur = root;
        Deque<TreeNode> queue = new LinkedList<>();
        int position = 0;
        cur.val = position;
        queue.offer(cur);
        int maxWide = 0;
        int levelCount = 0;
        while (!queue.isEmpty()) {
            levelCount = queue.size();
            maxWide = Math.max(maxWide, queue.peekLast().val - queue.peekFirst().val + 1);
            for (int i = 0; i < levelCount; i++) {
                cur = queue.poll();
                position = cur.val;
                if (cur.left != null) {
                    cur.left.val = position * 2;
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    cur.right.val = position * 2 + 1;
                    queue.offer(cur.right);
                }
            }
        }
        return maxWide;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = null;
        root.right.right = new TreeNode(7);
        System.out.println(widthOfBinaryTree(root));
    }
}
