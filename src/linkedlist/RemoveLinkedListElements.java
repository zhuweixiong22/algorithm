package linkedlist;

/**
 * 203. 移除链表元素
 * @author novo
 * @date 2022/3/26-22:19
 */
public class RemoveLinkedListElements {
    public class ListNode {
        ListNode next;
        int val;
        ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode removeElements(ListNode head, int val) {
        // 需要对头结点特殊判断 找到一个值不为val的头结点
        while (head != null) {
            if (head.val != val) {
                break;
            }
            head = head.next;
        }
        // 运行到这head是可能为null的
        ListNode cur = head;
        ListNode pre = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                // cur值不为val pre紧跟cur
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    // 不对头结点特殊处理 我们在头结点前新增一个虚拟结点，使得所有结点都统一处理
    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        // 保证了cur必不为空
        ListNode cur = dummyHead;
        while(cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
}
