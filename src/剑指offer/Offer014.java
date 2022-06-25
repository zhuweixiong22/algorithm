package 剑指offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 剑指 Offer II 014. 字符串中的变位词
 *
 * @author zwx
 * @date 2022-06-25 14:10
 */
public class Offer014 {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        // 排列不会改变字符串中每个字符的个数 因此可以用数组记录子串中字符的个数
        int[] need = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            need[s1.charAt(i) - 'a']++;
        }
        int k = s1.length();
        int[] window = new int[26];
        int l = 0;
        int r = 0;
        // 维护左闭右开[l, r)的窗口
        while (r < s2.length()) {
            window[s2.charAt(r++) - 'a']++;
            if (r - l > k) {
                window[s2.charAt(l++) - 'a']--;
            }
            if (check(need, window)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(int[] need, int[] count) {
        for (int i = 0; i < 26; i++) {
            if (need[i] != count[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Offer014 offer014 = new Offer014();
        System.out.println(offer014.checkInclusion("ab", "eidbaooo"));
    }
}
