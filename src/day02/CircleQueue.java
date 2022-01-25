package day02;

/**
 * @author novo
 * @date 2022/1/25-14:57
 */
public class CircleQueue {
    public static class MyQueue {
        private int[] data;
        private int head;
        private int tail;
        private final int queueSize;

        public MyQueue(int limit) {
            data = new int[limit + 1];
            this.head = 0;
            this.tail = 0;
            this.queueSize = limit + 1;
        }

        public void push(int value) {
            if ((tail + 1) % queueSize == head) {
                throw new RuntimeException("循环队列已满");
            }
            data[tail++] = value;
        }

        public int pop() {
            if (head == tail) {
                throw new RuntimeException("循环队列已空");
            }
            return data[head--];
        }

        public boolean isEmpty() {
            if (head == tail) {
                return true;
            }
            return false;
        }

        public int size() {
            return (tail - head + queueSize) % queueSize;
        }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(5);
        /*myQueue.push(3);
        myQueue.push(3);
        myQueue.push(3);
        myQueue.push(3);
        myQueue.push(3);*/
        int size = myQueue.size();
        System.out.println(myQueue.isEmpty());
        System.out.println(size);
    }
}
