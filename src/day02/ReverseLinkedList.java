package day02;

/**
 * @author novo
 * @date 2022/1/24-20:35
 */
public class ReverseLinkedList {
    public static class ListNode<T> {
        public T data;
        public ListNode<T> next;

        public ListNode(T data) {
            this.data = data;
        }
    }

    public static class DoubleListNode<T> {
        public T data;
        public DoubleListNode<T> next;
        public DoubleListNode<T> pre;

        public DoubleListNode(T data) {
            this.data = data;
        }
    }

    //  空间复杂度O(1) 不能使用容器类的东西
    //   a    ->   b    ->  c  ->  null
    //   c    ->   b    ->  a  ->  null
    public static <T> ListNode<T> reverseList(ListNode<T> head) {
        ListNode<T> pre = null;
        ListNode<T> cur = head;
        ListNode<T> next = null;
        while (cur != null) {
            next = cur.next; //记录next
            cur.next = pre; // 当前next指向pre
            pre = cur; // 将当前节点设置为pre
            cur = next;// cur后移
        }
        return pre;
    }

    // 双链表反转
    public static <T> DoubleListNode<T> reverseDoubleList(DoubleListNode<T> head) {
        DoubleListNode<T> pre = null;
        DoubleListNode<T> cur = head;
        DoubleListNode<T> next = null;
        while (cur != null) {
            next = cur.next; // 记录next
            cur.next = pre; // 修改cur的pre和next 顺序无所谓
            cur.pre = next; // 因为将cur.next保存给next了 所以先反转pre还是next的顺序无所谓
            pre = cur; // 将当前节点设置为pre
            cur = next; // cur后移 等价于cur = cur.pre 反转前的next值
        }
        return pre;
    }


}
