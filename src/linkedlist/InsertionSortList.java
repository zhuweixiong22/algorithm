package linkedlist;

/**
 * @author novo
 * @date 2022/3/28-20:45
 */
public class InsertionSortList {
    class ListNode {
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        // 注意这里有点巧妙 dummy不能指向head否则会成环，第一次循环就是把头结点接到dummy后
        ListNode cur = head;
        ListNode next = null;
        ListNode pre = dummyHead;
        while (cur != null) {
            next = cur.next;
            pre = dummyHead;
            // 与数组插入排序不同，由于链表的next属性，只能从头往后寻找插入的位置
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            // 找到要插入的位置了
            cur.next = pre.next;
            pre.next = cur;
            //
            cur = next;
        }
        return dummyHead.next;
    }
}
