package backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zwx
 * @date 2022-05-04 22:29
 */
public class LeetCode784 {
    // 用set存储，因为会重复
    private Set<String> res = new HashSet<>();

    public List<String> letterCasePermutation(String s) {
        dfs(s.toCharArray(), 0, "");
        return new ArrayList<>(res);
    }

    private void dfs(char[] str, int index, String path) {
        if (index == str.length) {
            res.add(path);
            return;
        }
        // 对于每一位字符都可以选择转成大写或者转成小写
        dfs(str, index + 1, path + Character.toLowerCase(str[index]));
        dfs(str, index + 1, path + Character.toUpperCase(str[index]));
    }

    private List<String> list = new ArrayList<>();

    public List<String> letterCasePermutation2(String s) {
        dfs2(s.toCharArray(), 0, "");
        return list;
    }

    private void dfs2(char[] str, int index, String path) {
        if (index == str.length) {
            list.add(path);
            return;
        }
        // 如果是数字则不用管
        if (Character.isDigit(str[index])) {
            dfs2(str, index + 1, path + str[index]);
        } else {
            // 如果是字母则可以选择进行大小写转化也可以选择不转
            dfs2(str, index + 1, path + changeLetter(str[index]));
            dfs2(str, index + 1, path + str[index]);
        }
    }

    private char changeLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') ? (char) (ch - 32) : (char) (ch + 32);
    }

    public static void main(String[] args) {
        LeetCode784 leetCode784 = new LeetCode784();
        System.out.println(leetCode784.letterCasePermutation2("a1b2"));
        char ch = 'a';
        System.out.println(Character.toUpperCase(ch));
    }
}
