package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author novo
 * @date 2022/3/14-20:37
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        if (tokens.length == 0) {
            return 0;
        }
        int n = tokens.length;
        int res = 0;
        Deque<String> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (isOperator(tokens[i])) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                switch (tokens[i]) {
                    case "+":
                        res = num2 + num1;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num2 * num1;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                }
                stack.push(res + "");
            }
            if (isNum(tokens[i])) {
                stack.push(tokens[i]);
            }
        }
        return res;
    }

    // 虽然题目给的都是合法数据 最好还是校验一下
    private boolean isNum(String c) {
        try {
            Integer.parseInt(c);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isOperator(String c) {
        return "+".equals(c) || "-".equals(c) || "*".equals(c) || "/".equals(c);
    }

    public static void main(String[] args) {
        String[] s = {"2", "1", "+", "3", "*"};
        EvaluateReversePolishNotation evaluateReversePolishNotation = new EvaluateReversePolishNotation();
        System.out.println(evaluateReversePolishNotation.evalRPN(s));

    }
}
