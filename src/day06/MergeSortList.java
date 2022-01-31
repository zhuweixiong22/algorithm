package day06;

/**
 * 链表排序归并递归
 * @author novo
 * @date 2022/1/30-11:56
 */
public class MergeSortList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return process(head);
    }

    public static ListNode process(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode faster = head.next;
        ListNode slower = head;
        while (faster != null && faster.next != null) {
            faster = faster.next.next;
            slower = slower.next;
        }
        ListNode right = slower.next;
        slower.next = null;
        ListNode leftNode = process(head);
        ListNode rightNode = process(right);
        return merge(leftNode, rightNode);
    }

    public static ListNode merge(ListNode left, ListNode right) {
        ListNode res = new ListNode(0);
        ListNode cur = res;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left != null ? left : right;
        /*
        while (left != null) {
            cur.next = left;
            left = left.next;
            cur = cur.next;
        }
        while (right != null) {
            cur.next = right;
            right = right.next;
            cur = cur.next;
        }
         */
        return res.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode a = new ListNode(4);
        ListNode b = new ListNode(1);
        ListNode c = new ListNode(3);


        head.next = a;
        head.next.next = b;
        //head.next.next.next = c;

        ListNode listNode = mergeSort(head);

        while (listNode != null) {
            System.out.print(listNode.val + " ");

            listNode = listNode.next;
        }
    }
}
