package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author novo
 * @date 2022/3/14-21:19
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        assert path.length() > 0;
        // 选用切割"/"的方案 这样就可以将完整的每个文件夹分开 ".."也是一个完整的字符串 不需要记录前一个的"."
        String[] paths = path.split("/");
        if (paths.length == 0) {
            return "/";
        }
        Deque<String> stack = new ArrayDeque<>();
        for (String s : paths) {
            // 注意split出来的可能还会有空字符串
            if ("".equals(s) || ".".equals(s)) {
                continue;
            }
            // ".."且栈不为空 弹出一个文件夹
            if ("..".equals(s)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            // 如果是文件夹 入栈
            stack.push(s);
        }
        // 到这里 栈中已经有正确路径的文件夹了，只需要逆序出来补上"/"即可
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            // 头插法
            sb.insert(0, stack.pop());
            sb.insert(0, "/");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplifyPath("/a/./b/../../c/"));
        System.out.println(simplifyPath.simplifyPath("/../"));
        System.out.println(simplifyPath.simplifyPath("/home///foo/"));
        String[] strs = "/home///foo/".split("/");
        System.out.println(Arrays.toString("/".split("/")));
        System.out.println(Arrays.toString("//".split("/")));
        System.out.println(Arrays.toString("///".split("/")));
        System.out.println(Arrays.toString("8///".split("/")));
        System.out.println(Arrays.toString("/8///".split("/")));
        System.out.println(Arrays.toString("/8////8".split("/")));
        System.out.println(Arrays.toString("/8////8/".split("/")));
    }
}
