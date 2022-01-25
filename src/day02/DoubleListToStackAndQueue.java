package day02;

/**
 * 双链表实现队列、双端队列和栈
 *
 * @author novo
 * @date 2022/1/25-15:14
 */
public class DoubleListToStackAndQueue {
    public static class DoubleList<T> {
        public T data;
        public DoubleList<T> next;
        public DoubleList<T> pre;

        public DoubleList(T data) {
            this.data = data;
        }
    }

    public static class DoubleQueue<T> {
        private DoubleList<T> head;
        private DoubleList<T> tail;

        public void addFromHead(T data) {
            // 注意并没有一开始就生成tail指针 只有队列为空才会初始化tail
            DoubleList<T> cur = new DoubleList<>(data);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.pre = cur;
                head = cur;
            }
        }

        public void addFromBottom(T data) {
            DoubleList<T> cur = new DoubleList<>(data);
            if (head == null) {
                head = cur;
                head = cur;
            } else {
                cur.pre = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }
            DoubleList<T> cur;
            if (head == tail) { // 只剩一个节点 因为由空构建一个节点时时没有对next和pre做空处理的 不知道会不会有错误
                cur = head;
                head = null;
                tail = null;
            } else {
                cur = head;
                head = head.next;
                head.pre = null;
            }
            return cur.data;
        }

        public T popFromBottom() {
            if (head == null) {
                return null;
            }
            DoubleList<T> cur;
            if (head == tail) {
                cur = head;
                head = null;
                tail = null;
            } else {
                cur = tail;
                tail = tail.pre;
                tail.next = null;
            }
            return cur.data;
        }
        public boolean isEmpty(){
            return head == null;
        }
    }
    public static class Stack<T> {
        private DoubleQueue<T> queue;
        public Stack(){
            this.queue = new DoubleQueue<>();
        }
        // 限制只能从一端进出
        public void push(T data){
            this.queue.addFromBottom(data);
        }
        public T pop(){
            return this.queue.popFromBottom();
        }
        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }
    public static class Queue<T> {
        private DoubleQueue<T> queue;

        public Queue() {
            queue = new DoubleQueue<T>();
        }

        public void push(T value) {
            queue.addFromBottom(value);
        }

        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
}
