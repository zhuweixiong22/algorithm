package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 *
 * @author novo
 * @date 2022/2/7-15:35
 */
public class Palindrome {
    private List<List<String>> res = new ArrayList<>();
    private List<String> path = new ArrayList<>();
    private StringBuilder str = new StringBuilder();

    public List<List<String>> partition(String s) {
        str.append(s);
        backTracking(str, 0);
        return res;
    }

    public void backTracking(StringBuilder str, int index) {
        // 切割完毕
        if (index == str.length()) {
            System.out.println("path：" + path + "切割完成");
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < str.length(); i++) {
            System.out.print("path:" + path + "   ");
            System.out.println("index: " + index + " i: " + i);
            // 不是回文串
            if (!isPalindrome(str, index, i)) {
                continue;
            }
            String s = str.substring(index, i + 1);
            path.add(s);
            backTracking(str, i + 1);
            path.remove(path.size() - 1);
        }
    }

    // 判断是否回文
    private boolean isPalindrome(StringBuilder s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<String>> list = new Palindrome().partition("aabb");
        list.forEach(System.out::println);
    }
}
