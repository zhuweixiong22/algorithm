package day07;

/**
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向 上方对折1次，压出折痕后展开。
 * 此时 折痕是凹下去的，即折痕 突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折 2 次，压出折痕后展开，此时有三条折痕，
 * 从上到下依次是下折 痕、下折痕和上折痕。 给定一 个输入参数N，代表纸条都从下边向上方连续对折N次，
 * 请从上到下打印所有折痕的方向。 例如：
 * <p>
 * N=1时，
 * 打印： down
 * N=2时，
 * 打印： down down up
 *
 * @author novo
 * @date 2022/1/31-22:11
 */
public class PaperFolding {
    /**
     * 实际上就是一颗二叉树 折痕当做结点
     * 该树的特点 树的根节点是凹 左孩子结点为凹 右孩子结点为凸
     * 用 i 表示层数 如果是纸条从上往下打印 就是中序遍历
     * @param N
     */
    public static void printAllFolding(int N) {
        inOrder(1, N, true);
    }

    public static void inOrder(int i, int N, boolean isDown) {
        if (i > N) {
            return;
        }
        inOrder(i + 1, N, true);
        System.out.print(isDown ? "第" + i + "次凹 " : "第" + i + "次凸 ");
        inOrder(i + 1, N, false);
    }

    public static void main(String[] args) {
        printAllFolding(3);
    }
}
