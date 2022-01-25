package day02;

/**
 * @author novo
 * @date 2022/1/25-14:23
 */
public class review {
    public static class NodeList {
        public int data;
        public NodeList next;

        public NodeList(int data) {
            this.data = data;
        }
    }

    public static class DoubleList {
        public int data;
        public DoubleList next;
        public DoubleList pre;

        public DoubleList(int data) {
            this.data = data;
        }
    }

    public static NodeList reverseList(NodeList head) {
        NodeList pre = null;
        NodeList cur = head;
        NodeList next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static DoubleList reverseDouble(DoubleList head) {
        DoubleList pre = null;
        DoubleList cur = head;
        DoubleList next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            cur.pre = next;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static NodeList remove(NodeList head, int value) {
        while (head != null) {
            if (head.data != value) {
                break;
            }
            head = head.next;
        }
        NodeList pre = head;
        NodeList cur = head;
        while (cur != null) {
            if (cur.data == value) {
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
