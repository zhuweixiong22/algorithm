package hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 同构字符串
 *
 * @author novo
 * @date 2022/4/5-21:16
 */
public class LeetCode205 {
    // 这题解法和202单词规律一样
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] keys = s.toCharArray();
        char[] values = t.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < keys.length; i++) {
            if (map.containsKey(keys[i])) {
                if (map.get(keys[i]) != values[i]) {
                    return false;
                }
            } else {
                if (set.contains(values[i])) {
                    return false;
                }
                set.add(values[i]);
                map.put(keys[i], values[i]);
            }
        }
        return true;
    }
}
