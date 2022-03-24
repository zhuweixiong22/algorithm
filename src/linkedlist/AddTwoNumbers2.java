package linkedlist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author novo
 * @date 2022/3/24-23:04
 */
public class AddTwoNumbers2 {
    public static class ListNode {
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stackL1 = new LinkedList<>();
        Deque<Integer> stackL2 = new LinkedList<>();
        // 从低位开始加，需要将两个链表的值都推入辅助栈
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                stackL1.push(l1.val);
                l1 = l1.next;
            }
            if (l2 != null) {
                stackL2.push(l2.val);
                l2 = l2.next;
            }
        }
        // 相加后头插即可
        ListNode pail = null;
        ListNode cur = null;
        int carry = 0;
        while (!stackL1.isEmpty() || !stackL2.isEmpty()) {
            if (!stackL1.isEmpty()) {
                carry += stackL1.pop();
            }
            if (!stackL2.isEmpty()) {
                carry += stackL2.pop();
            }
            // 头插
            cur = new ListNode(carry % 10);
            cur.next = pail;
            pail = cur;
            carry /= 10;
        }
        // 检查最后一个case有无进位
        if (carry == 1) {
            cur = new ListNode(1);
            cur.next = pail;
        }
        return cur;
    }
}
