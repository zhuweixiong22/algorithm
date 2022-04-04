package hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 290. 单词规律
 *
 * @author novo
 * @date 2022/4/4-23:52
 */
public class LeetCode290 {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        char[] keys = pattern.toCharArray();
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        // 这题关键在与pattern和words是双向映射的，键和值是共存亡并且一一对应的
        for (int i = 0; i < keys.length; i++) {
            if (map.containsKey(keys[i])) {
                // 判断一一对应关系
                if (!map.get(keys[i]).equals(words[i])) {
                    return false;
                }
            } else {
                // 判断共存亡关系 不可能出现 没有键却set中已经出现这个键对应的值 这种情况
                if (set.contains(words[i])) {
                    return false;
                }
                map.put(keys[i], words[i]);
                set.add(words[i]);
            }
        }
        return true;
    }
}
