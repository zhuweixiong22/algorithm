package linkedlist;

/**
 * 328. 奇偶链表
 * @author novo
 * @date 2022/3/22-23:05
 */
public class OddEvenLinkedList {
    public class ListNode {
        ListNode next;
        int val;
        ListNode (int val) {
            this.val = val;
        }
    }
    public ListNode oddEvenList(ListNode head) {
        ListNode oddCur = new ListNode(-1);
        ListNode evenCur = new ListNode(-1);
        ListNode oddHead = oddCur;
        ListNode evenHead = evenCur;
        int count = 1;
        while (head != null) {
            if (count % 2 == 1) {
                oddCur.next = head;
                oddCur = oddCur.next;
            } else {
                evenCur.next = head;
                evenCur = evenCur.next;
            }
            head = head.next;
            count++;
        }
        oddCur.next = evenHead.next;
        evenCur.next = null;
        return oddHead.next;
    }
}
