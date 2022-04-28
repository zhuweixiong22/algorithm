package DataStructure.BST;

/**
 * @author zwx
 * @date 2022/4/28-16:54
 */
public class BST<E extends Comparable<E>> {

    private class TreeNode {
        public E e;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private TreeNode root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加元素
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 以root为根的二分搜索树中插入元素e并返回新的根
     *
     * @param root
     * @param e
     * @return
     */
    private TreeNode add(TreeNode root, E e) {
        if (root == null) {
            size++;
            return new TreeNode(e);
        }
        if (e.compareTo(root.e) < 0) {
            root.left = add(root.left, e);
        } else if (e.compareTo(root.e) > 0) {
            root.right = add(root.right, e);
        }
        return root;
    }

    /**
     * 查看二分搜索树中是否包含元素e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 查看以root为根的二分搜索树中是否包含元素e
     *
     * @param root
     * @param e
     * @return
     */
    private boolean contains(TreeNode root, E e) {
        if (root == null) {
            return false;
        }
        if (e.compareTo(root.e) == 0) return true;
        else if (e.compareTo(root.e) < 0) return contains(root.left, e);
        else return contains(root.right, e);
    }

    /**
     * 返回二分搜索树中最小的元素
     *
     * @return
     */
    public E getMin() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return getMin(root).e;
    }

    /**
     * 返回以root为根的二分搜索树中最小元素的节点
     *
     * @param root
     * @return
     */
    private TreeNode getMin(TreeNode root) {
        if (root.left == null) {
            return root;
        }
        return getMin(root.left);
    }

    /**
     * 返回二分搜索树中最大的元素
     *
     * @return
     */
    public E getMax() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return getMax(root).e;
    }

    /**
     * 返回以root为根的二分搜索树中最大元素的节点
     *
     * @param root
     * @return
     */
    private TreeNode getMax(TreeNode root) {
        if (root.right == null) {
            return root;
        }
        return getMax(root.right);
    }

    /**
     * 删除二分搜索树中的最小节点并返回值
     *
     * @return
     */
    public E removeMin() {
        E ret = getMin();
        removeMin(root);
        return ret;
    }

    /**
     * 删除以root为根的二分搜索树中的最小节点并返回新的根
     *
     * @param root
     * @return
     */
    private TreeNode removeMin(TreeNode root) {
        if (root.left == null) {
            // 找到最小节点root后，root可能会有右节点，保存待删节点的右子树并断开
            TreeNode rightNode = root.right;
            root.right = null;
            size--;
            return rightNode;
        }
        // 如果左子树不为空继续递归
        root.left = removeMin(root.left);
        return root;
    }


    /**
     * 删除二分搜索树中的最大节点并返回值
     *
     * @return
     */
    public E removeMax() {
        E ret = getMax();
        removeMax(root);
        return ret;
    }

    /**
     * 删除以root为根的二分搜索树中的最大节点并返回新的根
     *
     * @param root
     * @return
     */
    private TreeNode removeMax(TreeNode root) {
        if (root.right == null) {
            // 找到最大节点root后，root可能会有左子树，保存待删节点的左子树并断开
            TreeNode leftNode = root.left;
            root.left = null;
            size--;
            return leftNode;
        }
        // 如果右子树不为空继续递归
        root.right = removeMax(root.right);
        return root;
    }

    /**
     * 删除二分搜索树中任意元素e
     *
     * @param e
     * @return
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除以root为根的二分搜索树中值为e的节点并返回新的根
     *
     * @param root
     * @param e
     * @return
     */
    private TreeNode remove(TreeNode root, E e) {
        // 找不到则一定是在叶子结点找不到 返回空
        if (root == null) return null;

        if (e.compareTo(root.e) < 0) {
            root.left = remove(root.left, e);
            return root;
        } else if (e.compareTo(root.e) > 0) {
            root.right = remove(root.right, e);
            return root;
        } else { // 找到e
            // 左子树为空的情况 与删除最小节点一样
            if (root.left == null) {
                TreeNode rightNode = root.right;
                root.right = null;
                size--;
                return rightNode;
            }
            // 左子树为空的情况 与删除最大节点一样
            if (root.right == null) {
                TreeNode leftNode = root.left;
                root.left = null;
                size--;
                return leftNode;
            }
            // 左右子树均不为空的情况 Hibbard Deletion
            // 找到比待删除节点大的最小节点替代，即待删除节点的右子树的最小值
            // 或者找到比待删除节点小的最大节点替代，即待删除节点的左子树的最大值
            TreeNode rightMin = getMin(root.right);
            rightMin.right = removeMin(root.right);
            rightMin.left = root.left;
            root.left = null;
            root.right = null;
            return rightMin;
        }
    }

    /**
     * 返回比e小且最接近e的值
     *
     * @param e
     * @return
     */
    public E floor(E e) {
        // 如果树为空 或者e比树中最小元素还要小则不存在
        if (size == 0 || e.compareTo(getMin()) < 0) {
            return null;
        }
        return floor(root, e).e;
    }

    /**
     * 返回比e小的最接近e的节点
     *
     * @param root
     * @param e
     * @return
     */
    private TreeNode floor(TreeNode root, E e) {
        if (root == null) {
            return null;
        }
        // 当前节点已经比e大 所以往左找比e大且更接近e的节点
        if (root.e.compareTo(e) >= 0) {
            return floor(root.left, e);
        }
        // 当前节点比e小 往右找比e大的节点
        TreeNode floor = floor(root.right, e);
        // 如果左右子树为空 则当前节点就是比e大且最接近e的节点
        if (floor == null) return root;
        return floor;
    }

    /**
     * 返回比e大且最接近e的值
     *
     * @param e
     * @return
     */
    public E ceil(E e) {
        // 如果树为空 或者e比树中最大元素还要大则不存在
        if (size == 0 || e.compareTo(getMax()) > 0) {
            return null;
        }
        return ceil(root, e).e;
    }

    /**
     * 返回比e大的最接近e的节点
     *
     * @param root
     * @param e
     * @return
     */
    private TreeNode ceil(TreeNode root, E e) {
        if (root == null) {
            return null;
        }
        if (root.e.compareTo(e) <= 0) {
            return ceil(root.right, e);
        }
        TreeNode ceil = ceil(root.left, e);
        if (ceil == null) return root;
        return ceil;
    }
}
