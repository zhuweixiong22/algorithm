package linkedlist;

/**
 * 83. 删除排序链表中的重复元素
 * @author novo
 * @date 2022/3/21-22:26
 */
public class RemoveDuplicatesFromSortedList {
    public static class ListNode {
        ListNode next;
        int val;
        ListNode (int val) {
            this.val = val;
        }
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                // 如果有重复值被删除后 cur.next已经被更改，所以next值的正确的值
                cur = cur.next;
            }
        }
        return head;
    }
}
