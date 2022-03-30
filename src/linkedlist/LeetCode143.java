package linkedlist;

/**
 * 143. 重排链表
 * @author novo
 * @date 2022/3/30-22:44
 */
public class LeetCode143 {
    class ListNode {
        ListNode next;
        int val;
        ListNode (int val) {
            this.val = val;
        }
    }
    public void reorderList(ListNode head) {
        ListNode slower = head;
        ListNode faster = head.next;
        while (faster != null && faster.next != null) {
            slower = slower.next;
            faster = faster.next.next;
        }
        // 找到第一个中点的下一个位置 先断开再翻转
        ListNode right = slower.next;
        slower.next = null;
        right = reverse(right);

        ListNode left = head;

        while (right != null) {
            ListNode nextL = left.next;
            ListNode nextR = right.next;
            left.next = right;
            left = nextL;

            right.next = left;
            right = nextR;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode next = null;
        ListNode pre = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
