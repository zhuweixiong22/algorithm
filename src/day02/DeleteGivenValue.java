package day02;

/**
 * 删除单链表中给定的value 返回新头指针
 *
 * @author novo
 * @date 2022/1/24-22:08
 */
public class DeleteGivenValue {
    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    // 无头结点
    public static Node remove(Node head, int value) {
        // 先确定新的头指针 因为如果头结点data就是给定的值，那么头指针是要发生变化的
        // head指向第一个不需要被删的位置
        while (head != null) {
            if (head.data != value) {
                break;
            }
            head = head.next;
        }
        // 运行到这head可以为null(整个链表都被删除) 和head不为null
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.data == value) { // 这里第一次if肯定是不相等的 因为新的head的值肯定不等于value
                pre.next = cur.next;
            } else {
                pre = cur; // 不用被删除 pre紧跟cur
            }
            cur = cur.next;
        }
        return head;
    }
}
