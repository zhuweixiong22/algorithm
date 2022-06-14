package day02;

import java.util.Stack;

/**
 * 用栈实现队列
 *
 * @author novo
 * @date 2022/1/25-21:24
 */
public class TwoStacksImplementQueue {
    public static class TwoStacksQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStacksQueue() {
            this.stackPush = new Stack<>();
            this.stackPop = new Stack<>();
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

        public boolean isEmpty() {
            return stackPush.isEmpty() && stackPop.isEmpty();
        }
    }
    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.push(1);
        test.push(2);
        test.push(3);
        System.out.println(test.peek());
        System.out.println(test.pop());
        System.out.println(test.peek());
        System.out.println(test.pop());
        System.out.println(test.peek());
        System.out.println(test.pop());
    }
}
