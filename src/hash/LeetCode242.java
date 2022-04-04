package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. 有效的字母异位词
 *
 * @author novo
 * @date 2022/4/2-23:21
 */
public class LeetCode242 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        // 给定字符串会有重复，与次数有关，使用map
        Map<Character, Integer> map = new HashMap<>();
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        boolean flag = true;
        for (char c : arr1) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : arr2) {
            if (map.containsKey(c) && map.get(c) > 0) {
                // 用掉一次比较 减一
                map.put(c, map.get(c) - 1);
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
