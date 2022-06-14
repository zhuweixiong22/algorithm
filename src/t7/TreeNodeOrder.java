package day07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的深度优先遍历：先、中、后
 * 广度优先遍历：层序遍历
 *
 * @author novo
 * @date 2022/1/31-11:20
 */
public class TreeNodeOrder {

    public static class TreeNode {
        public TreeNode leftChild;
        public TreeNode rightChild;
        public int data;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.leftChild);
        preOrder(root.rightChild);
    }

    public static void preOrderWithOutRecursion(TreeNode root) {
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            // 先访问根 再遍历左 最后遍历右
            if (cur != null) {
                System.out.print(cur.data + " ");
                // 先访问值 再压入栈
                stack.push(cur);
                cur = cur.leftChild;
            } else {
                cur = stack.pop();
                cur = cur.rightChild;
            }
        }
    }

    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.leftChild);
        System.out.print(root.data + " ");
        inOrder(root.rightChild);
    }

    public static void inOrderWithOutRecursion(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 遇到一个结点，就把它压栈，并去遍历它的左子树；
            // 当左子树遍历结束后，从栈顶弹出这个结点并访问它；
            // 访问完就去遍历右
            if (cur != null) {
                // 先压入栈 出栈再访问
                stack.push(cur);
                cur = cur.leftChild;
            } else {
                cur = stack.pop();
                System.out.print(cur.data + " ");
                cur = cur.rightChild;
            }
        }
    }

    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.leftChild);
        postOrder(root.rightChild);
        System.out.print(root.data + " ");
    }

    /**
     * 第二种思路(借助双指针pre记录访问过的结点 cur：当前结点，不需要设置标志)：
     * 要保证根结点在左子树在右子树访问之后才能访问，因此对于任一结点P，先将其入栈。
     * 如果P不存在左子树在右子树，则可以直接访问它；
     * 或者P存在左子树在右子树，但是其左子树在右子树都已被访问过了，
     * 则同样可以直接访问该结点。
     * 若非上述两种情况，则将P的右孩子和左孩子依次入栈，
     * 这样就保证了 每次取栈顶元素的时候，左子树在右子树前面被访问，
     * 左子树和右子树都在根结点前面被访问。
     *
     * @param root
     */
    public static void postOrderWithOutRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if ((cur.leftChild == null && cur.rightChild == null)
                    // 只要pre等于cur任意一个子树 则说明else分支已经走完==左右子树已遍历完
                    || (pre == cur.leftChild || pre == cur.rightChild)) {
                System.out.print(cur.data + " ");
                pre = stack.pop();
            } else {
                //这里入栈顺序不能改变 一定是右子树在左子树之前入栈
                //才能保证出栈访问时 左子树先于右子树被访问
                if (cur.rightChild != null) {
                    stack.push(cur.rightChild);
                }
                if (cur.leftChild != null) {
                    stack.push(cur.leftChild);
                }
            }
        }
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
            System.out.print(cur.data + " ");
            if (cur.leftChild != null) {
                queue.offer(cur.leftChild);
            }
            if (cur.rightChild != null) {
                queue.offer(cur.rightChild);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.leftChild = new TreeNode(2);
        root.rightChild = new TreeNode(3);
        root.leftChild.leftChild = new TreeNode(4);
        root.leftChild.rightChild = new TreeNode(5);
        root.rightChild.leftChild = new TreeNode(6);
        root.rightChild.rightChild = new TreeNode(9);
        //root.leftChild = new TreeNode(2);
        preOrder(root);
        System.out.println("先序递归=============");
        preOrderWithOutRecursion(root);
        System.out.println("先序非递归=============");
        inOrder(root);
        System.out.println("中序递归=============");
        inOrderWithOutRecursion(root);
        System.out.println("中序非递归=============");
        postOrder(root);
        System.out.println("后序递归=============");
        postOrderWithOutRecursion(root);
        System.out.println("后序非递归=============");
        levelOrder(root);
        System.out.println("层序遍历=============");
    }
}
