package day08;

import java.util.ArrayList;
import java.util.List;

/**
 * @author novo
 * @date 2022/3/8-23:34
 */
public class PathSum2 {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return res;
        }
        dfs(root, targetSum);
        return res;
    }

    private void dfs(TreeNode root, int targetSum) {
        // 递归终止1
        if (root == null) {
            return;
        }
        path.add(root.val);
        // 递归终止2 叶子结点只能在上一结点判断
        if (root.left == null && root.right == null) {
            if (targetSum == root.val) {
                // 当前结点的值还没添加到列表中，所以要先添加，然后再移除
                res.add(new ArrayList<>(path));
            }
            path.remove(path.size() - 1);
            return;
        }
        // 下面就是经典的回溯 状态重置 其实可以再简洁一点 因为进入左右分支的 path 是一样的 所以可以省略掉左递归之后的remove 和 右递归之前的add
        dfs(root.left, targetSum - root.val);
        path.remove(path.size() - 1);

        path.add(root.val);
        dfs(root.right, targetSum - root.val);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        PathSum2 pathSum2 = new PathSum2();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        System.out.println(pathSum2.pathSum(root, 22));
    }
}
