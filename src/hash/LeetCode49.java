package hash;

import java.util.*;

/**
 * @author novo
 * @date 2022/4/11-22:00
 */
public class LeetCode49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            // 字母异位词排序后字符位置必然相等 所以当key
            Arrays.sort(chars);
            String key = new String(chars);
            // 如果有该key对应的list 则将str加入list中
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}
