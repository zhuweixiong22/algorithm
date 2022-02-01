package day08;

/**
 * 判断是否为平衡二叉树
 * @author novo
 * @date 2022/2/1-22:08
 */
public class IsBalancedTree {
    public static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public static class Info{
        public boolean isBalanced;
        public int depth;
        public Info(boolean isBalanced,int depth) {
            this.isBalanced = isBalanced;
            // 深度
            this.depth = depth;
        }
    }
    public static boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }
    public static Info process(TreeNode root) {
        // 假定空树是平衡二叉树
        if (root == null) {
            return new Info(true,0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        boolean isBalanced = true;
        int curDepth = Math.max(leftInfo.depth,rightInfo.depth) + 1;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced
                || Math.abs(leftInfo.depth - rightInfo.depth) > 1) {
            isBalanced = false;
        }
        return new Info(isBalanced,curDepth);
    }

    public static boolean isBalanced1(TreeNode head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(TreeNode head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
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
            if (isBalanced1(head) != isBalanced(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

