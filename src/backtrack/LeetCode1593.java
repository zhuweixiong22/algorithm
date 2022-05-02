package backtrack;

import slidingwindow.LeetCode209;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zwx
 * @date 2022/5/1-22:12
 */
public class LeetCode1593 {
    //private List<String> path = new ArrayList<>();
    private Set<String> set = new HashSet<>(); // 保证子字符串唯一
    private int res = 0;

    public int maxUniqueSplit(String s) {
        backTracking(s, 0);
        return res;
    }

    private void backTracking(String s, int index) {
        if (index == s.length()) {
            res = Math.max(res, set.size());
            return;
        }

        for (int i = 1; i <= s.length() - index; i++) {
            String substr = s.substring(index, index + i);
            if (!set.contains(substr)) {
                //path.add(substr);
                set.add(substr);
                backTracking(s, index + i);
                //path.remove(path.size() - 1);
                set.remove(substr);
            }

        }
    }

    public static void main(String[] args) {
        LeetCode1593 leetCode1593 = new LeetCode1593();
        System.out.println(leetCode1593.maxUniqueSplit("ababccc"));
        System.out.println(leetCode1593.maxUniqueSplit("aba"));
    }
}
