package day06;

/**
 * 可能含环的相交链表
 *
 * @author novo
 * @date 2022/2/2-21:32
 */
public class IntersectionCycleList {
    public static class ListNode {
        public ListNode next;
        public int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

public static ListNode isIntersection(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
        return null;
    }
    ListNode loopA = findLoopNode(headA);
    ListNode loopB = findLoopNode(headB);
    // 两个无环链表相交问题
    if (loopA == null && loopB == null) {
        return getIntersectionNoLoop(headA, headB);
    }
    // 两个有环链表相交问题
    if (loopA != null && loopB != null) {
        return getIntersectionAllLoop(headA, loopA, headB, loopB);
    }
    // 一个有环 一个无环 必不相交
    return null;
}

// 判断链表是否有环，有则返回入环点，无则返回null
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
    if (faster == null || faster.next == null) {
        return null;
    }
    slower = slower.next;
    faster = head;
    while (faster != slower) {
        faster = faster.next;
        slower = slower.next;
    }
    return faster;
}

// 求两个有环链表相交问题 如果相交求出交点
public static ListNode getIntersectionAllLoop(ListNode headA, ListNode loopA, ListNode headB, ListNode loopB) {
    ListNode curA = headA;
    ListNode curB = headB;
    // 入环点一样 那么入环点之前的情况跟无环链表一样
    if (loopA == loopB) {
        while (curA != curB) {
            // 不让指针走入环内
            curA = (curA != loopA) ? curA.next : headB;
            curB = (curB != loopA) ? curB.next : headA;
        }
        return curA;
    }
    // 入环点不一样 即相交点就等于入环点 随便返回一个
    return loopA;

}

// 求两个无环链表相交问题 如果相交求出交点
public static ListNode getIntersectionNoLoop(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
        return null;
    }
    ListNode curA = headA;
    ListNode curB = headB;
    while (curA != curB) {
        curA = (curA != null) ? curA.next : headB;
        curB = (curB != null) ? curB.next : headA;
    }
    return curA;
}

// for test
public static void main(String[] args) {
    // 1->2->3->4->5->6->7->null
    ListNode head1 = new ListNode(1);
    head1.next = new ListNode(2);
    head1.next.next = new ListNode(3);
    head1.next.next.next = new ListNode(4);
    head1.next.next.next.next = new ListNode(5);
    head1.next.next.next.next.next = new ListNode(6);
    head1.next.next.next.next.next.next = new ListNode(7);

    // 0->9->8->6->7->null
    ListNode head2 = new ListNode(0);
    head2.next = new ListNode(9);
    head2.next.next = new ListNode(8);
    head2.next.next.next = head1.next.next.next.next.next; // 8->6
    System.out.println(isIntersection(head1, head2).val);

    // 1->2->3->4->5->6->7->4...
    head1 = new ListNode(1);
    head1.next = new ListNode(2);
    head1.next.next = new ListNode(3);
    head1.next.next.next = new ListNode(4);
    head1.next.next.next.next = new ListNode(5);
    head1.next.next.next.next.next = new ListNode(6);
    head1.next.next.next.next.next.next = new ListNode(7);
    head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

    // 0->9->8->2...
    head2 = new ListNode(0);
    head2.next = new ListNode(9);
    head2.next.next = new ListNode(8);
    head2.next.next.next = head1.next; // 8->2
    System.out.println(isIntersection(head1, head2).val);

    // 0->9->8->6->4->5->6..
    head2 = new ListNode(0);
    head2.next = new ListNode(9);
    head2.next.next = new ListNode(8);
    head2.next.next.next = head1.next.next.next.next.next; // 8->6
    System.out.println(isIntersection(head1, head2).val);

}
}
