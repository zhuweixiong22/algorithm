package backtrack;

import sun.util.resources.cldr.zh.CalendarData_zh_Hans_HK;

import java.util.*;

/**
 * 22. 括号生成
 *
 * @author zwx
 * @date 2022/5/3-21:29
 */
public class LeetCode22 {
    private List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        dfs(n, n, "");
        for (String str : res) {
            if (isValid(str)) {
                ret.add(str);
            }
        }
        return ret;
    }

    // 一次性生成所有可能，之后在判断有效性
    private void dfs(int leftCount, int rightCount, String path) {
        if (leftCount == 0 && rightCount == 0) {
            res.add(path);
        }
        if (leftCount > 0) {
            dfs(leftCount - 1, rightCount, path + "(");
        }
        if (rightCount > 0) {
            dfs(leftCount, rightCount - 1, path + ")");
        }
    }



    private boolean isValid(String str) {
        if ((str.length() & 1) == 1 || str.length() == 0) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else {
                // ch == ')'
                // 如果此时stack为空
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return true;
    }

    public List<String> generateParenthesis2(int n) {
        dfs2(n, n, "");
        return res;
    }

    //
    private void dfs2(int leftCount, int rightCount, String path) {
        if (leftCount == 0 && rightCount == 0) {
            res.add(path);
        }
        // 这个剪枝很巧妙 右括号比左括号多一定是不符合条件的
        if (leftCount > rightCount) {
            return;
        }
        if (leftCount > 0) {
            dfs2(leftCount - 1, rightCount, path + "(");
        }
        if (rightCount > 0) {
            dfs2(leftCount, rightCount - 1, path + ")");
        }
    }
    public static void main(String[] args) {
        LeetCode22 leetCode22 = new LeetCode22();
        System.out.println(leetCode22.generateParenthesis2(3));
    }
}
