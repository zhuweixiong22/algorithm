package day07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author novo
 * @date 2022/1/31-14:07
 */
public class TreeSerializeAndDeserialize {
    public static class TreeNode {
        public TreeNode leftChild;
        public TreeNode rightChild;
        public int data;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    // 因为是递归调用 需要全局变量记录
    // 递归调用最后的一个逗号不好去掉，可以留着
    private static StringBuilder preRes = new StringBuilder();

    public static String preSerialize(TreeNode root) {
        if (root == null) {
            return preRes.append("#").append(",").toString();
        }
        preRes.append(root.data).append(",");
        preSerialize(root.leftChild);
        preSerialize(root.rightChild);
        return preRes.toString();
    }

    public static TreeNode preDeserialize(String data) {
        // 将字符串转化成列表
        LinkedList<String> nodes = new LinkedList<>();
        for (String node : data.split(",")) {
            nodes.addLast(node);
        }
        return preDeserialize(nodes);
    }

    // 辅助函数，通过 nodes 列表构造二叉树 前序遍历的框架 将打印改为生成结点
    // deserialize 方法首先寻找 root 节点的值，然后递归计算左右子节点
    public static TreeNode preDeserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        // 列表最左侧就是根节点
        String cur = nodes.removeFirst();
        if ("#".equals(cur)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(cur));
        root.leftChild = preDeserialize(nodes);
        root.rightChild = preDeserialize(nodes);
        return root;
    }

    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.leftChild);
        preOrder(root.rightChild);
    }

    private static StringBuilder postRes = new StringBuilder();

    public static String postSerialize(TreeNode root) {
        if (root == null) {
            return postRes.append("#").append(",").toString();
        }
        postSerialize(root.leftChild);
        postSerialize(root.rightChild);
        return postRes.append(root.data).append(",").toString();
    }

    public static TreeNode postDeserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String node :data.split(",")) {
            nodes.addLast(node);
        }
        return postDeserialize(nodes);
    }
    // 同样首先寻找 root 节点的值，然后递归计算左右子节点
    public static TreeNode postDeserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        // 后序反序列化 最后一个才是根节点
        String cur = nodes.removeLast();
        if ("#".equals(cur)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(cur));
        // 一定是先构造右子树再构造左子树
        root.rightChild = postDeserialize(nodes);
        root.leftChild = postDeserialize(nodes);
        return root;
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
     * 其实就是在标准的层序遍历框架上修改
     * 因为序列化的过程是要记录空指针的，所以当cur为null时我们需要记录一下在跳过该次循环
     * 所以对空指针的检验从「将元素加入队列」的时候改成了「从队列取出元素」的时候
     *
     * @param root
     * @return
     */
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
            res.append(cur.data).append(",");
            queue.offer(cur.leftChild);
            queue.offer(cur.rightChild);
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    /**
     * for 循环部分的代码也是是标准层级遍历的代码衍生出来的
     *
     * @param data
     * @return
     */
    public static TreeNode levelDeserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] nodeArr = data.split(",");
        // 第一个元素就是 root 的值
        TreeNode root = new TreeNode(Integer.parseInt(nodeArr[0]));
        // 队列记录父节点，将 root 加入队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < nodeArr.length; ) {
            TreeNode parent = queue.poll();
            String leftChild = nodeArr[i++];
            if (!"#".equals(leftChild)) {
                // 这里提示可能会空指针 如果是序列化的格式是正确的 这里提示不用管
                parent.leftChild = new TreeNode(Integer.parseInt(leftChild));
                queue.offer(parent.leftChild);
            } else {
                parent.leftChild = null;
            }
            String rightChild = nodeArr[i++];
            if (!"#".equals(rightChild)) {
                parent.rightChild = new TreeNode(Integer.parseInt(rightChild));
                queue.offer(parent.rightChild);
            } else {
                parent.rightChild = null;
            }
        }
        return root;
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
        root.rightChild.rightChild = new TreeNode(7);
        //root.leftChild = new TreeNode(2);
        System.out.print(levelSerialize(root));
        System.out.println("=======层序遍历反序列化结果======");
        TreeNode levelRoot = levelDeserialize(levelSerialize(root));
        levelOrder(levelRoot);
        System.out.println("=========================层序遍历结果==========");


        String preStr = preSerialize(root);
        System.out.print(preStr);
        System.out.println("======先序遍历反序列化结果======");
        TreeNode preRoot = preDeserialize(preStr);
        preOrder(preRoot);
        System.out.println("=========================先序遍历结果==========");


        String postStr = postSerialize(root);
        System.out.print(postStr);
        System.out.println("======后序遍历反序列化结果======");
        TreeNode postRoot = postDeserialize(postStr);
        postOrder(postRoot);
        System.out.println("=========================后序遍历结果==========");
    }
}
