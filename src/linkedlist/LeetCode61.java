package linkedlist;

/**
 * @author novo
 * @date 2022/3/29-21:37
 */
public class LeetCode61 {
    class ListNode {
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        // 记录最后一个结点
        ListNode last = null;
        // 确定链表长度
        int n = 0;
        while (cur.next != null) {
            n++;
            cur = cur.next;
            if (cur.next == null) {
                last = cur;
            }
        }
        cur = dummyHead;
        k = k % n;
        // 不移动
        if (k == 0) {
            return head;
        }
        for (int i = 0; i < (n - k); i++) {
            cur = cur.next;
        }
        // right为倒数第k+1个结点，在此处断开链表 重新连接
        ListNode right = cur.next;
        cur.next = null;
        last.next = dummyHead.next;

        return right;
    }
}
