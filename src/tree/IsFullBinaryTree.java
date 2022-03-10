package tree;

/**
 * 判断是否为满二叉树
 * 对于满二叉树的定义，国内外不一样
 * 国内：除最后一层无任何子节点外，每一层上的所有结点都有两个子结点二叉树。
 *
 * 国外：如果一棵二叉树的结点要么是叶子结点，要么它有两个孩子结点，这样的树就是满二叉树。
 * 这里算法按照国内的来
 *
 * @author novo
 * @date 2022/2/1-23:02
 */
public class IsFullBinaryTree {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public int depth;
        public int nodes;

        public Info(int depth, int nodes) {
            this.depth = depth;
            this.nodes = nodes;
        }
    }

    public static boolean isFullBTree(TreeNode root) {
        Info rootInfo = process(root);
        //return Math.pow(2, rootInfo.depth) - 1 == rootInfo.nodes;
        // 次方可以用位运算
        return (1 << rootInfo.depth) - 1 == rootInfo.nodes;
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int curDepth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
        int curNodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(curDepth, curNodes);
    }

    public static boolean isFull1(TreeNode head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    public static int h(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return Math.max(h(head.left), h(head.right)) + 1;
    }

    public static int n(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return n(head.left) + n(head.right) + 1;
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
            if (isFullBTree(head) != isFull1(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
