package linkedlist;

import java.util.Deque;

/**
 * 2. 两数相加
 *
 * @author novo
 * @date 2022/3/24-22:41
 */
public class AddTwoNumbers {
    public static class ListNode {
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumHead = new ListNode(-1);
        ListNode cur = sumHead;
        int carry = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            // new Node 取余数
            cur.next = new ListNode(carry % 10);
            cur = cur.next;
            // 保留进位
            carry /= 10;
        }
        // 注意最后一个case 是否还有一个进位，有则新增一个node
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return sumHead.next;
    }
}
