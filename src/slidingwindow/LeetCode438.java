package slidingwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * @author zwx
 * @date 2022/4/25-23:37
 */
public class LeetCode438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) { // 可以提前判断
            return res;
        }
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        char[] pStr = p.toCharArray();
        char[] sStr = s.toCharArray();
        for (char c : pStr) {
            pCount[c - 'a']++;
        }
        int l = 0, r = -1;
        // 维护窗口[l, r]
        while (r + 1 < s.length()) { // 窗口右边界到边界，循环就可以结束了
            sCount[sStr[++r] - 'a']++;
            // 维护窗口长度
            if (r - l + 1 > pStr.length) {
                sCount[sStr[l++] - 'a']--;
            }
            // 窗口长度等于p长度时判断是否为p的异位词
            if (r - l + 1 == pStr.length && isAnagrams(pCount, sCount)) {
                res.add(l); // 添加窗口的左边界
            }
        }
        return res;
    }

    // 判断窗口内的字符是否为p的异位词
    private boolean isAnagrams(int[] pCount, int[] sCount) {
        for (int i = 0; i < 26; i++) {
            if (pCount[i] != sCount[i]) {
                return false;
            }
        }
        return true;
    }

}
