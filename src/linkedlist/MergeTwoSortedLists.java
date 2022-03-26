package linkedlist;

/**
 * @author novo
 * @date 2022/3/26-23:00
 */
public class MergeTwoSortedLists {
    public static class ListNode {
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        // 注意剩余不为空的链表结点直接挂上去就行，不需要更改list的next，否则会缺失结点
        cur.next = list1 != null ? list1 : list2;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(-9);
        list1.next = new ListNode(3);
        ListNode list2 = new ListNode(5);
        list2.next = new ListNode(7);
        MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
        mergeTwoSortedLists.mergeTwoLists(list1, list2);
    }
}
