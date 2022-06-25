package 剑指offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 015. 字符串中的所有变位词
 *
 * @author zwx
 * @date 2022-06-25 16:24
 */
public class Offer015 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) {
            return res;
        }

        // 排列不会改变字符串中每个字符的个数 因此可以用数组记录子串中字符的个数
        int[] need = new int[26];
        int k = p.length();
        for (int i = 0; i < k; i++) {
            need[p.charAt(i) - 'a']++;
        }
        int[] window = new int[26];
        int l = 0, r = 0;
        // 维护左闭右开[l, r)的窗口
        while (r < s.length()) {
            window[s.charAt(r++) - 'a']++;
            if (r - l > k) {
                window[s.charAt(l++) - 'a']--;
            }
            if (check(need, window)) {
                //System.out.println("yes");
                res.add(l);
            }
        }
        return res;
    }

    private boolean check(int[] need, int[] window) {
        for (int i = 0; i < 26; i++) {
            if (need[i] != window[i]) return false;
        }
        return true;
    }
}
