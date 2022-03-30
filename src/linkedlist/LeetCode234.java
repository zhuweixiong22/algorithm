package linkedlist;

/**
 * 234. 回文链表
 * @author novo
 * @date 2022/3/30-23:06
 */
public class LeetCode234 {
    class ListNode {
        ListNode next;
        int val;
        ListNode (int val) {
            this.val = val;
        }
    }
    public boolean isPalindrome(ListNode head) {
        boolean flag = true;

        ListNode faster = head.next;
        ListNode slower = head;
        while (faster != null && faster.next != null) {
            slower = slower.next;
            faster = faster.next.next;
        }
        ListNode right = reverse(slower.next);
        while(right != null) {
            if (head.val != right.val) {
                flag = false;
                break;
            }
            right = right.next;
            head = head.next;
        }
        // 最后最好把右链表再翻转一遍 还原回来
        return flag;
    }

    private ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
