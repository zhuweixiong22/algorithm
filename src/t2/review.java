package day02;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author novo
 * @date 2022/1/25-14:23
 */
public class review {
    public static class NodeList {
        public int data;
        public NodeList next;

        public NodeList(int data) {
            this.data = data;
        }
    }

    public static class DoubleList {
        public int data;
        public DoubleList next;
        public DoubleList pre;

        public DoubleList(int data) {
            this.data = data;
        }
    }

    public static NodeList reverseList(NodeList head) {
        NodeList pre = null;
        NodeList cur = head;
        NodeList next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static DoubleList reverseDouble(DoubleList head) {
        DoubleList pre = null;
        DoubleList cur = head;
        DoubleList next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            cur.pre = next;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static NodeList remove(NodeList head, int value) {
        while (head != null) {
            if (head.data != value) {
                break;
            }
            head = head.next;
        }
        NodeList pre = head;
        NodeList cur = head;
        while (cur != null) {
            if (cur.data == value) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static class GetMinStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public GetMinStack() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public boolean isEmpty() {
            if (stackData.isEmpty()) {
                return true;
            }
            return false;
        }

        public void push(int data) {
            if (stackMin.isEmpty()) {
                stackMin.push(data);
            } else if (data <= stackMin.peek()) {
                stackMin.push(data);
            }
            stackData.push(data);
        }

        public int pop() {
            if (this.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            int data = stackData.pop();
            if (data == stackMin.peek()) {
                stackMin.pop();
            }
            return data;
        }

        public int peek() {
            if (this.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return stackData.peek();
        }

        public int getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return this.stackMin.peek();
        }
    }

    public static class TwoStackQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStackQueue() {
            this.stackPop = new Stack<>();
            this.stackPush = new Stack<>();
        }

        public boolean isEmpty() {
            return stackPush.isEmpty() && stackPop.isEmpty();
        }

        private void pushToPop() {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void push(int data) {
            stackPush.push(data);
        }

        public int pop() {
            if (this.isEmpty()) {
                throw new RuntimeException("queue is empty");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek() {
            if (this.isEmpty()) {
                throw new RuntimeException("queue is empty");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static class OneQueueStack{
        private Queue<Integer> queue;
        public OneQueueStack(){
            this.queue = new LinkedList<>();
        }
        public boolean isEmpty(){
            return queue.isEmpty();
        }
        public void push(int data) {
            queue.offer(data);
        }
        public int pop() {
            if (this.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll());
            }
            return queue.poll();
        }
        public int peek() {
            if (this.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll());
            }
            int data = queue.peek();
            queue.offer(queue.poll());
            return data;
        }
    }
}
