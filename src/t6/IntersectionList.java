package day06;

/**
 * 普通链表是否相交
 * @author novo
 * @date 2022/1/30-21:29
 */
public class IntersectionList {
    public static class  ListNode{
        ListNode next;
        int val;
        public ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenA = 0;
        int lenB = 0;
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != null) {
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        while(lenA > lenB) {
            lenA--;
            curA = curA.next;
        }
        while(lenB > lenA) {
            lenB--;
            curB = curB.next;
        }
        while (curB != curA) {
            curB = curB.next;
            curA = curA.next;
        }
        return curA;
    }

    public static void main(String[] args) {

    }
}
