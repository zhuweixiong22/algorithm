package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author novo
 * @date 2022/3/14-21:19
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        assert path.length() > 1;
        int n = path.length();
        char[] paths = path.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        stack.push(paths[0]);
        boolean hasOneDot = false;
        for (int i = 1; i < n; i++) {
            if (!isOp(paths[i])) {
                stack.push(paths[i]);
            }
            if (i == n - 1) {
                continue;
            }
            if (paths[i] == '/' && stack.peek() != '/') {
                stack.push(paths[i]);
                continue;
            }
            if (hasOneDot && stack.peek() == '/') {
                continue;
            }
            if (paths[i] == '.') {
                if (hasOneDot) {
                    // ..返回上个目录
                    stack.pop();
                    if (!stack.isEmpty()) {
                        // "/../"的情况
                        stack.pop();
                    } else {
                        stack.push('/');
                    }
                    hasOneDot = false;

                } else {
                    hasOneDot = true;
                }
                continue;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }
        return sb.toString();
    }

    private boolean isOp(char c) {
        return c == '/' || c == '.';
    }

    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplifyPath("/a/./b/../../c/"));
        System.out.println(simplifyPath.simplifyPath("/../"));
        System.out.println(simplifyPath.simplifyPath("/home//foo/"));
    }
}
