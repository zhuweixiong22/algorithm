package day05;


import java.util.HashSet;
import java.util.Set;

/**
 * @author novo
 * @date 2022/1/30-16:23
 */
public class FindLoopNode {
    public static class ListNode {
        int data;
        public ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    public static ListNode findLoopNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode faster = head.next;
        ListNode slower = head;
        while (faster != null && faster.next != null && faster != slower) {
            faster = faster.next.next;
            slower = slower.next;
        }
        // 说明快慢指针没有相遇 无环
        if (faster == null || faster.next == null) {
            return null;
        }
        // 快慢指针相遇 慢指针走一步 快指针回到head
        slower = slower.next;
        faster = head;
        while (faster != slower) {
            faster = faster.next;
            slower = slower.next;
        }
        // 此时快慢指针相遇则为入环点
        return faster;
    }

    public static ListNode findLoopNodeByHash(ListNode head) {
        ListNode cur = head;
        Set<ListNode> set = new HashSet<>();
        while (cur != null) {
            if (!set.contains(cur)) {
                set.add(cur);
            } else {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }
}
