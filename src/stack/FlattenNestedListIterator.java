package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * @author novo
 * @date 2022/3/15-21:08
 */
public class FlattenNestedListIterator {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {

        private Deque<NestedInteger> stack = new ArrayDeque<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            // 对一层逆序压栈
            toStack(nestedList);
        }

        @Override
        public Integer next() {
            // 因为测试代码迭代器是有下一个元素才会调用next 没有就不会调用 所以这里的否定写什么都行
            return hasNext() ? stack.pollLast().getInteger() : Integer.MAX_VALUE;
        }

        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            } else {
                if (stack.peekLast().isInteger()) {
                    return true;
                } else {
                    List<NestedInteger> nestedList = stack.pollLast().getList();
                    // 不是数字 说明是列表 有层级继续调用 对一层逆序压栈
                    toStack(nestedList);
                    // 处理完一层后 递归调用hasNext()直至栈顶出现数字类型
                    return hasNext();
                }
            }
        }

        // 对一层逆序压栈
        private void toStack(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.addLast(nestedList.get(i));
            }
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
