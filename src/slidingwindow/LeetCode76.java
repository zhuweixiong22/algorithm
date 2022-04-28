package slidingwindow;

import java.util.Arrays;

/**
 * 76. 最小覆盖子串
 * @author zwx
 * @date 2022/4/26-22:20
 */
public class LeetCode76 {
    public String minWindow(String s, String t) {
        // t为匹配字串
        if (s.length() < t.length()) {
            return "";
        }

        int begin = 0; // 记录最小覆盖子串的起始位置 末位置即为窗口的右边界
        int minSize = Integer.MAX_VALUE; // 记录当前最小覆盖子串的长度
        int[] sCount = new int[256];
        int[] tCount = new int[256];
        char[] sStr = s.toCharArray();
        char[] tStr = t.toCharArray();

        // 记录匹配串的字符数
        for (char c : tStr) {
            tCount[c - 'A']++;
        }

        // 编辑距离，指窗口内子串覆盖匹配字符的一个编辑距离，若编辑距离等于匹配字符的长度，说明窗口已经覆盖匹配子串
        // 通俗的来讲就是窗口内已经还差 tLen - distance 个字符能覆盖匹配子串
        int distance = 0;
        int l = 0, r = -1;
        while (r + 1 < sStr.length) {
            // 注意这里sStr[r + 1]字符严格小于所需要的字符时纳入窗口后编辑距离才+1，否则说明窗口内该字符的数量已经大于需求量，纳入后编辑距离不变
            if (sCount[sStr[r + 1] - 'A'] < tCount[sStr[r + 1] - 'A']) {
                distance++;
            }
            // 移动右边界将新字符纳入窗口并记录窗口内字符的个数
            sCount[sStr[++r] - 'A']++;

            // 窗口已经覆盖匹配串时移动左边界来缩小窗口 （进行缩小窗口的时机很重要）
            while (distance == tStr.length) {
                // 维护最小覆盖子串的长度
                if (r - l + 1 < minSize) {
                    minSize = r - l + 1;
                    begin = l;
                }
                // 注意等于的时候减去这个字符后，窗口内才会缺少这个字符，大于的时候减去一个仍然不缺
                if (sCount[sStr[l] - 'A'] == tCount[sStr[l] - 'A']) {
                    distance--;
                }
                sCount[sStr[l++] - 'A']--;
            }
        }
        // 找不到则返回空否则返回结果子串
        return (minSize == Integer.MAX_VALUE ? "" : s.substring(begin, begin + minSize));
    }

    // 获取窗口内的子串
    private String getWindowStr(char[] str, int l, int r) {
        return new String(Arrays.copyOfRange(str, l, r + 1));
    }

    // 判断窗口内子串是否覆盖匹配串的字符
    private boolean isCover(int[] sCount, int[] tCount) {
        // 该算法对于匹配串中的字符可以重复
        for (int i = 0; i < 256; i++) {
            if (sCount[i] < tCount[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode76 leetCode76 = new LeetCode76();
        System.out.println(leetCode76.minWindow("ab", "a"));
    }
}
