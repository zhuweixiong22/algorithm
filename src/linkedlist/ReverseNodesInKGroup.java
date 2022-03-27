package linkedlist;

/**
 * @author novo
 * @date 2022/3/27-11:27
 */
public class ReverseNodesInKGroup {
    static class ListNode {
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode node1 = null;
        ListNode node2 = dummyHead;
        ListNode cur = dummyHead;
        // next初始值为head用于判断head是否为null
        ListNode next = dummyHead.next;

        while (next != null) {
            // node1指向上次的next node2指向上次处理的片段链表末尾
            node1 = next;
            for (int i = 0; i < k && node2 != null; i++) {
                node2 = node2.next;
            }
            if (node2 == null) {
                break;
            }
            next = node2.next;
            // cur.next 指向翻转后的头结点 翻转后node1为该片段链表的末尾节点
            cur.next = reverse(node1, next);
            node1.next = next;
            // 更新cur,node2 指向上个片段链表的末尾
            cur = node1;
            node2 = cur;
        }
        return dummyHead.next;
    }

    // 与普通的翻转链表差不多，只是循环条件由cur != null 改为 cur != end
    // end值为整个链表的next指针
    private ListNode reverse(ListNode node1, ListNode end) {
        ListNode pre = null;
        ListNode cur = node1;
        ListNode next = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ReverseNodesInKGroup reverseNodesInKGroup = new ReverseNodesInKGroup();
        reverseNodesInKGroup.reverseKGroup(head, 2);
    }
}
