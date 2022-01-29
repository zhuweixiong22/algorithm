package day05;

import java.util.HashMap;
import java.util.Map;

/**
 * @author novo
 * @date 2022/1/29-22:34
 */
public class CopyRandomList {
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node copyRandomListByHashMap(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node origin = head;
        while (origin != null) {
            map.put(origin, new Node(origin.val));
            origin = origin.next;
        }
        origin = head;
        while (origin != null) {
            map.get(origin).next = map.get(origin.next);
            map.get(origin).random = map.get(origin.random);
            origin = origin.next;
        }
        return map.get(head);
    }

    public static Node copyRandomListBySelf(Node head) {
        Node cur = head;
        Node next = null;
        // 1 在每个原结点后复制出一个新的
        while (cur != null) {
            next = cur.next;
            // copy = cur.next; copy.next = next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        // 一对一对（origin + copy）的遍历 添加副本结点的random指针
        cur = head;
        Node curCopy = null;
        // 这里为什么不用判cur.next != null 是因为cur.next是cur的副本 只用判cur就可以了
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            // cur.random是原结点的random 我们通过它获取cur.random的副本 而副本是接在原结点后面的
            curCopy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        Node copyHead = head.next;
        // 分离原结点和副本
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = curCopy.next;
            // 要特殊处理一下最后一个结点next为空 next.next会报错
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return copyHead;
    }
}
