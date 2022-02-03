package day08;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否为完全二叉树
 *
 * @author novo
 * @date 2022/2/1-14:32
 */
public class IsCompleteBinaryTree {
    public static class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isCompleteBTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 是否有孩子不全的结点
        boolean isNotFullChild = false;
        TreeNode cur = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();

            // 第一种情况：有孩子不全的结点出现且后面结点不都为叶子结点
            // 第二种情况：右子树为空且左子树不为空
            if ((isNotFullChild && (cur.left != null || cur.right != null))
                    || (cur.right != null && cur.left == null)) {
                return false;
            }

            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }

            // 第一次出现孩子不全的结点，进行标记
            // 标记一定要在最后进行，因为我们判断的是之后的结点是否都为叶子结点，
            // 如果if放在前面会出现把自己标记，又拿自己参与判断的情况
            if (cur.left == null || cur.right == null) {
                isNotFullChild = true;
            }
        }
        return true;
    }
    public static boolean isCompleteBinaryTree2(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    // 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }

    public static Info process(TreeNode X) {
        if (X == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);



        int height = Math.max(leftInfo.height, rightInfo.height) + 1;


        boolean isFull = leftInfo.isFull
                &&
                rightInfo.isFull
                && leftInfo.height == rightInfo.height;


        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else { // 以x为头整棵树，不满
            if (leftInfo.isCBT && rightInfo.isCBT) {


                if (leftInfo.isCBT
                        && rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull
                        &&
                        rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull
                        && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }


            }
        }
        return new Info(isFull, isCBT, height);
    }
    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isCompleteBTree(head) != isCompleteBinaryTree2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
