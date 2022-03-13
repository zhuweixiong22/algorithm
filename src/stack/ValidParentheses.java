package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 20. 有效的括号
 *
 * @author novo
 * @date 2022/3/13-23:27
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        // 字符长度为奇数肯定不有效
        if (s.length() % 2 == 1) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(s.charAt(i));
            } else {
                // 只有右符号的情况
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                if ((pop == '{' && s.charAt(i) == '}') || (pop == '[' && s.charAt(i) == ']') || ((pop == '(' && s.charAt(i) == ')'))) {
                } else {
                    // 不匹配返回false
                    return false;
                }
            }
        }
        // 注意还要判断只有左符号的情况
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        System.out.println(validParentheses.isValid("{[]}"));
    }
}
