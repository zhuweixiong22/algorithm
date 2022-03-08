package day08;

import day11.BinaryWatch;

import java.util.*;

/**
 * 257. 二叉树的所有路径
 * 根到叶子的路径
 *
 * @author novo
 * @date 2022/3/8-16:53
 */
public class BinaryTreePaths {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private List<String> res = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return res;
        }
        dfs(root, "");
        return res;
    }

    // path用的字符串 隐式回溯
    private void dfs(TreeNode root, String path) {
        if (root == null) {
            return;
        }
        dfs(root.left, path + root.val + "->");
        dfs(root.right, path + root.val + "->");
        if (root.left == null && root.right == null) {
            // 注意到了叶子结点就不需要加 -> 了
            res.add(path + root.val);
        }
    }

    // 迭代
    public List<String> binaryTreePaths2(TreeNode root) {
        if (root == null) return res;
        Deque<Object> stack = new LinkedList<>();
        stack.push(root);
        stack.push(String.valueOf(root.val)); // 节点，当前路径
        while (!stack.isEmpty()) {
            // 注意pop的顺序要对应上
            String path = (String) stack.pop();
            TreeNode cur = (TreeNode) stack.pop();
            if (cur.left == null && cur.right == null) {
                res.add(path);
            }
            // 因为root的值先加了 所以 -> 后拼上
            if (cur.right != null) {
                stack.push(cur.right);
                stack.push(path + "->" + cur.right.val);
            }
            if (cur.left != null) {
                stack.push(cur.left);
                stack.push(path + "->" + cur.left.val);
            }
        }
        return res;
    }

    // bfs
    public List<String> binaryTreePaths3(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Object> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root.val + "");
        while (!queue.isEmpty()) {
            TreeNode cur = (TreeNode) queue.poll();
            String path = (String) queue.poll();
            //如果到叶子节点，说明找到了一条完整路径
            if (cur.left == null && cur.right == null) {
                res.add(path);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                queue.offer(path + "->" + cur.right.val);
            }
            if (cur.left != null) {
                queue.offer(cur.left);
                queue.offer(path + "->" + cur.left.val);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        BinaryTreePaths binaryTreePaths = new BinaryTreePaths();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = null;
        root.left.right = new TreeNode(5);
        List<String> strings = binaryTreePaths.binaryTreePaths3(root);
        System.out.println(strings);
    }
}
