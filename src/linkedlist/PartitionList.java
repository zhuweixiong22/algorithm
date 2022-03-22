package linkedlist;

import java.util.List;

/**
 * 86. 分隔链表
 * 链表版的荷兰国旗
 *
 * @author novo
 * @date 2022/3/22-21:42
 */
public class PartitionList {
    public static class ListNode {
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode partition(ListNode head, int x) {
        // 分割链表再合并
        ListNode leftCur = new ListNode(-1);
        ListNode rightCur = new ListNode(-1);
        // 记录两个链表头
        ListNode leftHead = leftCur;
        ListNode rightHead = rightCur;
        while (head != null) {
            if (head.val < x) {
                leftCur.next = head;
                leftCur = leftCur.next;
            } else {
                rightCur.next = head;
                rightCur = rightCur.next;
            }
            head = head.next;
        }
        // 左链表接上右链表的第二个结点
        leftCur.next = rightHead.next;
        // 右链表最后记得要置空 否则会出现循环链表
        rightCur.next = null;
        // 返回左链表的第二个结点
        return leftHead.next;
    }

    public static void main(String[] args) {
        PartitionList partitionList = new PartitionList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        partitionList.partition(head,3);
    }
}
