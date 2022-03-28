package linkedlist;

/**
 * 148. 排序链表
 *
 * @author novo
 * @date 2022/3/28-21:36
 */
public class SortList {
    class ListNode {
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    // O(n log n) 时间复杂度 常规使用归并递归
    public ListNode sortList(ListNode head) {
        // 空 或者元素个数为1 返回
        if (head == null || head.next == null) {
            return head;
        }
        // 经典快慢指针寻找中点
        ListNode faster = head.next;
        ListNode slower = head;
        while (faster != null && faster.next != null) {
            faster = faster.next.next;
            slower = slower.next;
        }
        // 此时慢指针指向第一个中点，也就是数组排序中M的位置
        ListNode midNext = slower.next;
        // 断开链表
        slower.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(midNext);
        return merge(left, right);
    }

    // 合并两个有序链表经典算法
    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = (left != null) ? left : right;
        return dummyHead.next;
    }
}
