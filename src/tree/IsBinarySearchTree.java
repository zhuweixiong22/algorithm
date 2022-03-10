package tree;


import java.util.ArrayList;

/**
 * 判断是否为二叉搜索树
 * 如果要用到最大最小值 需要用Long 否则会被卡边界
 * @author novo
 * @date 2022/2/2-11:22
 */
public class IsBinarySearchTree {
    public static class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isBinarySearchTree(TreeNode root) {
        /*if (root == null) {
            return true;
        }*/
        return process2(root).isBST;
        //return process(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean process(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }
        if (root.val >= lower || root.val <= upper) {
            return false;
        }
        return process(root.left, lower, root.val) && process(root.right, root.val, upper);
    }

    public static Info process1(TreeNode root) {
        Info curInfo = new Info(true, root.val, root.val);
        if (root.left != null) {
            Info leftInfo = process1(root.left);
            if (!leftInfo.isBST || leftInfo.max >= root.val) {
                curInfo.isBST = false;
            }
            // 只需要在左子树更新最小值和在右子树更新最大值，就算出现了不是搜索子树导致更新最值不正确也没什么所谓，因为已经记录的false值了
            //curInfo.max = Math.max(leftInfo.max,root.val);
            curInfo.min = Math.min(leftInfo.min, root.val);
        }
        if (root.right != null) {
            Info rightInfo = process1(root.right);
            if (!rightInfo.isBST || rightInfo.min <= root.val) {
                curInfo.isBST = false;
            }
            //curInfo.min = Math.max(rightInfo.min,root.val);
            curInfo.max = Math.max(rightInfo.max, root.val);
        }
        return curInfo;
    }

    public static Info process2(TreeNode root) {
        if (root == null) {
            return new Info(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        Info leftInfo = process2(root.left);
        Info rightInfo = process2(root.right);
        int curMax = Math.max(root.val, Math.max(leftInfo.max, rightInfo.max));
        int curMin = Math.min(root.val, Math.min(leftInfo.min, rightInfo.min));
        boolean curIsBST = false;
        if (leftInfo.isBST && rightInfo.isBST && leftInfo.max < root.val && rightInfo.min > root.val) {
            curIsBST = true;
        }
        return new Info(curIsBST, curMax, curMin);
    }


    // for test
    public static boolean isBST1(TreeNode head) {
        if (head == null) {
            return true;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return false;
            }
        }
        return true;
    }

    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;

        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBinarySearchTree(head)) {
                System.out.println("Oops!");
                //break;
            }
        }
        //System.out.println(binarySearchTree);
        System.out.println("finish!");
    }
}
