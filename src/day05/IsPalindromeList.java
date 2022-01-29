package day05;

import java.util.List;
import java.util.Stack;

/**
 * @author novo
 * @date 2022/1/29-20:47
 */
public class IsPalindromeList {
    public static class ListNode {
        int data;
        public ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    // 容器
    public static boolean isPal(ListNode head) {

        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.data);
            cur = cur.next;
        }
        while (head != null) {
            if (head.data != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 快慢指针
    public static boolean isPal2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 1 快慢指针找中点
        ListNode faster = head;
        ListNode slower = head;
        // 快慢指针 快2倍 如果奇数个 while停下 慢指针指向中点
        // 如果偶数个 慢指针指向第一个中点
        // 这里注意faster.next也要判空 否则否则会空指针
        while (faster.next != null && faster.next.next != null && slower.next != null) {
            faster = faster.next.next;
            slower = slower.next;
        }
        boolean res = true;
        // 2 反转右链表对比
        ListNode reverse = reverse(slower.next);
        // 如果要还原链表的话不能直接用reverse 会被改变
        ListNode temp = reverse;
        while (temp != null) {
            if (temp.data != head.data) {
                res = false;
                break;
            }
            temp = temp.next;
            head = head.next;
        }
        // 3 还原链表
        slower.next = reverse(reverse);
        return res;
    }

    public static ListNode reverse(ListNode head) {
        ListNode next = null;
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(2);
        listNode.next.next.next.next.next = new ListNode(1);
        //listNode.next = new ListNode(1);
        System.out.println(isPal2(listNode));
        //ListNode reverse = reverse(listNode);
        /*System.out.println(reverse.data);
        System.out.println(reverse.next.data);
        System.out.println(reverse.next.next.data);*/
        ListNode temp = listNode;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

}
