package day08;

import java.util.ArrayList;
import java.util.List;

/**
 * 129. 求根节点到叶节点数字之和
 *
 * @author novo
 * @date 2022/3/9-10:33
 */
public class SumRootToLeafNumbers {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private int res = 0;
    private List<Integer> path = new ArrayList<>();

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return res;
    }

    // 回溯
    private void dfs(TreeNode root) {
        // 递归终止1
        if (root == null) {
            return;
        }
        // 递归终止2
        if (root.left == null && root.right == null) {
            path.add(root.val);
            res += getValue(path);
            path.remove(path.size() - 1);
            return;
        }
        // 经典回溯
        path.add(root.val);
        dfs(root.left);
        path.remove(path.size() - 1);

        path.add(root.val);
        dfs(root.right);
        path.remove(path.size() - 1);
    }

    // 转化成正确的值
    private int getValue(List<Integer> path) {
        int n = path.size();
        int value = 0;
        for (int i = 0; i < n; i++) {
            // 10的n - 1 - i 次方 乘上 i位的值
            value += Math.pow(10, n - 1 - i) * path.get(i);
        }
        return value;
    }

    private int getValue2(List<Integer> path) {
        int value = 0;
        for (int i : path) {
            value += i * 10 + path.get(i);
        }
        return value;
    }


    public int sumNumbers2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs2(root, "");
        return res;
    }

    // 回溯
    private void dfs2(TreeNode root, String path) {
        // 递归终止1
        if (root == null) {
            return;
        }
        path += root.val;
        // 递归终止2
        if (root.left == null && root.right == null) {
            res += Integer.parseInt(path);
            return;
        }
        // 经典回溯
        dfs2(root.left, path);
        dfs2(root.right, path);
    }

    public static void main(String[] args) {
        SumRootToLeafNumbers sumRootToLeafNumbers = new SumRootToLeafNumbers();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        /*root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);*/
        sumRootToLeafNumbers.sumNumbers(root);
    }
}
