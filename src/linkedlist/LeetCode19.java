package linkedlist;

/**
 * 19. 删除链表的倒数第 N 个结点
 *
 * @author novo
 * @date 2022/3/29-20:48
 */
public class LeetCode19 {
    class ListNode {
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 遍历两遍
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0;
        // 建立虚拟头结点 减少边界处理
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null) {
            count++;
            cur = cur.next;
        }
        cur = dummyHead;
        for (int i = 0; i < count - n; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummyHead.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode p = dummyHead;
        ListNode q = dummyHead;
        // q从dummyHead移动n + 1
        for (int i = 0; i <= n; i++) {
            q = q.next;
        }
        // 寻找被删除结点的前一个位置
        while (q != null) {
            p = p.next;
            q = q.next;
        }
        // 删除
        p.next = p.next.next;
        return dummyHead.next;
    }
}
