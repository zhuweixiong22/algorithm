package day02;

import java.util.Stack;

/**
 * 设计一个支持 `push` ，
 * `pop` ，`top` 操作，
 * 并能在常数时间内检索到最小元素的栈。
 *
 * @author novo
 * @date 2022/1/25-18:44
 */
public class GetMinStack {
    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int data) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(data);
            } else if (data <= this.getMin()) {
                this.stackMin.push(data);
            }
            this.stackData.push(data);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            int data = stackData.pop();
            if (data == this.getMin()) {
                this.stackMin.pop();
            }
            return data;
        }

        public int getMin(){
            if (this.stackMin.isEmpty()){
                throw new RuntimeException("stack is empty");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack stack1 = new MyStack();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");
    }
}
