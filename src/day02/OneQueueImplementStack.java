package day02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author novo
 * @date 2022/1/25-22:28
 */
public class OneQueueImplementStack {
    public static class OneQueueStack<T> {
        private Queue<T> queue;

        public OneQueueStack() {
            this.queue = new LinkedList<>();
        }

        public void push(T data) {
            queue.offer(data);
        }

        public T pop() {
            if (this.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll());
            }
            return queue.poll();
        }

        public T peek() {
            if (this.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll());
            }
            // 注意这里要取出data值 然后再把他放回队尾 相当于循环走了一次回归原位
            T data = queue.peek();
            queue.offer(queue.poll());
            return data;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
    public static void main(String[] args) {
        OneQueueStack<Integer> stack = new OneQueueStack<>();
        stack.push(1);
        stack.push(2);
        //stack.push(1);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
    }
}
