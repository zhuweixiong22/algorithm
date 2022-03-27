package linkedlist;

/**
 * @author novo
 * @date 2022/3/27-11:07
 */
public class SwapNodesInPairs {
    class ListNode {
        ListNode next;
        int val;
        ListNode (int val) {
            this.val = val;
        }
    }
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        ListNode node1 = null;
        ListNode node2 = null;
        ListNode next = null;
        while (cur.next != null && cur.next.next != null) {
            // 为什么不在循环外面先初始值是因为如果cur.next为null 给node2赋值会空指针异常
            node1 = cur.next;
            node2 = cur.next.next;
            next = node2.next;
            // 交换
            node2.next = node1;
            node1.next = next;
            cur.next = node2;
            // 修改下一次循环的cur指针
            cur = node1;
        }
        return dummyHead.next;
    }
}
