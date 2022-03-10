package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * 最大二叉搜索子树
 *
 * @author novo
 * @date 2022/2/2-13:50
 */
public class MaxSubBinarySearchTree {
    public static class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static int findMaxSubBSTreeSize(TreeNode root) {
        /*if (root == null) {
            return 0;
        }*/
        return process(root).maxBSTSubTreeSize;
    }

    public static class Info {
        public boolean isAllBST;
        public int maxBSTSubTreeSize;
        public int max;
        public int min;

        public Info(boolean isAllBST, int maxBSTSubTreeSize, int max, int min) {
            this.isAllBST = isAllBST;
            this.maxBSTSubTreeSize = maxBSTSubTreeSize;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        boolean curIsAllBST = false;
        int curMin = Math.min(root.val, Math.min(leftInfo.min, rightInfo.min));
        int curMax = Math.max(root.val, Math.max(leftInfo.max, rightInfo.max));
        // 若左右子树都不存在搜索树 curMaxBSTSubTreeSize默认值就是1 自身一个节点（假定只有一个结点时也是二叉搜索树）
        int curMaxBSTSubTreeSize = 1;
        // 子树存在最大二叉搜索树，取其最大值
        if (leftInfo.maxBSTSubTreeSize != 0 || rightInfo.maxBSTSubTreeSize != 0) {
            curMaxBSTSubTreeSize = Math.max(leftInfo.maxBSTSubTreeSize, rightInfo.maxBSTSubTreeSize);
        }
        // 左右子树整颗都为二叉搜索树且满足左子树最大值 < root.val 右子树最小值> root.val
        // 更新curMaxBSTSubTreeSize 等于左+右+自身的一个节点
        if (leftInfo.isAllBST && rightInfo.isAllBST && leftInfo.max < root.val && rightInfo.min > root.val) {
            curMaxBSTSubTreeSize = leftInfo.maxBSTSubTreeSize + rightInfo.maxBSTSubTreeSize + 1;
            curIsAllBST = true;
        }
        return new Info(curIsAllBST, curMaxBSTSubTreeSize, curMax, curMin);
    }

    public static int getBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
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

    public static void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode cur = root;
        queue.offer(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.val + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    public static String levelSerialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        TreeNode cur = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur == null) {
                res.append("#").append(",");
                continue;
            }
            res.append(cur.val).append(",");
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        //TreeNode head = generateRandomBST(maxLevel, maxValue);
        TreeNode head = new TreeNode(28);
        head.left = new TreeNode(96);
        head.right = null;
        head.left.left = new TreeNode(18);
        head.left.right = new TreeNode(8);
        head.left.left.left = null;
        head.left.left.right = new TreeNode(98);
        head.left.left.right.left = null;
        head.left.left.right.right = null;
        head.left.right.left = null;
        head.left.right.right = new TreeNode(32);
        head.left.right.right.left = null;
        head.left.right.right.right = null;
        System.out.println(maxSubBSTSize1(head));
        System.out.println(findMaxSubBSTreeSize(head));
        System.out.println(levelSerialize(head));
        System.out.println();

        for (int i = 0; i < testTimes; i++) {
            TreeNode head2 = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head2) != findMaxSubBSTreeSize(head2)) {
                System.out.println(maxSubBSTSize1(head2));
                System.out.println(findMaxSubBSTreeSize(head2));
                System.out.println(levelSerialize(head2));
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }
}
