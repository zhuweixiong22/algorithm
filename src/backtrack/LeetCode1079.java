package backtrack;

import java.util.*;

/**
 * @author zwx
 * @date 2022/5/2-22:19
 */
public class LeetCode1079 {
    private Set<String> set = new HashSet<>();
    private boolean[] used;

    public int numTilePossibilities(String tiles) {
        used = new boolean[tiles.length()];
        dfs(tiles.toCharArray(), "", 0);
        return set.size() - 1; // 减去 ""
    }

    // 超时
    private void dfs(char[] str, String path, int index) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        for (int i = 0; i < str.length; i++) {
            // 对于每次选择都可以选或不选一个字符
            dfs(str, path, index + 1);
            if (!used[i]) {
                // 如果该字符未被使用过
                used[i] = true;
                dfs(str, path + str[i], index + 1);
                used[i] = false;
            }
        }
    }

    // 转化为多个每个长度的全排列问题
    private int res = 0;

    public int numTilePossibilities2(String tiles) {
        used = new boolean[tiles.length()];
        char[] str = tiles.toCharArray();
        Arrays.sort(str);
        for (int i = 1; i <= str.length; i++) {
            // 对每个长度的子串进行去重全排列
            // 如排序后titles为AAB 则求每一个长度的全排列总数和
            backTracking(str, i);
        }
        return res; // 减去 ""
    }

    private void backTracking(char[] str, int index) {
        if (index == 0) {
            res++;
        }
        for (int i = 0; i < str.length; i++) {
            if (!used[i]) {
                if (i > 0 && str[i] == str[i - 1] && !used[i - 1]) {
                    continue;
                }
                used[i] = true;
                backTracking(str, index - 1);
                used[i] = false;
            }
        }
    }

    public int numTilePossibilities3(String tiles) {
        int[] count = new int[26];
        char[] str = tiles.toCharArray();
        for (char c : str) {
            count[c - 'A']++;
        }
        return backTracking2(count);
    }

    private int backTracking2(int[] count) {
        // 递归终止条件titles中所有字符均被使用
        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) {
                continue;
            }
            count[i]--;
            res++;
            res += backTracking2(count);
            count[i]++;
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode1079 leetCode1079 = new LeetCode1079();
        //System.out.println(leetCode1079.numTilePossibilities("AAB"));
        long l = System.currentTimeMillis();
        System.out.println(leetCode1079.numTilePossibilities("AAB"));
        System.out.println(System.currentTimeMillis() - l + "ms");
        l = System.currentTimeMillis();
        System.out.println(leetCode1079.numTilePossibilities2("AAB"));
        System.out.println(System.currentTimeMillis() - l + "ms");
    }
}
