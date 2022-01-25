package day02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author novo
 * @date 2022/1/25-21:52
 */
public class TwoQueueImplementStack {
    public static class TwoQueueStack{
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;

        public TwoQueueStack(){
            this.queue1 = new LinkedList<>();
            this.queue2 = new LinkedList<>();
        }

        public void push(int data){
            queue1.offer(data);
        }

        public int pop(){
            if(this.isEmpty()){
                throw new RuntimeException("stack is empty");
            }
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }
            int data = queue1.poll();
            // 交换地址
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
            return data;
        }

        public int peek(){
            if(this.isEmpty()){
                throw new RuntimeException("stack is empty");
            }
            while (queue1.size() > 1){
                queue2.offer(queue1.poll());
            }
            // 因为只是获取值 最后一个先队还要入队
            int data = queue1.poll();
            queue2.offer(data);
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
            return data;
        }
        public boolean isEmpty(){
            // 只要用户输入的这个队列为空则栈为空 因为辅助队列只是在进行pop和peek操作进行转移
            return queue1.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack myStack = new TwoQueueStack();
        myStack.push(2);
        System.out.println(myStack.pop());
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (myStack.peek() !=(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (myStack.pop()!=(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }
}
