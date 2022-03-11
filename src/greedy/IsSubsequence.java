package greedy;

/**
 * 392. 判断子序列
 *
 * @author novo
 * @date 2022/3/11-19:49
 */
public class IsSubsequence {
    // 贪心 双指针
    public boolean isSubsequence(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        int i = 0;
        int j = 0;
        while (i < tLen) {
            // 在长串找到匹配的字符j++
            if (s.charAt(j) == t.charAt(i)) {
                j++;
            }
            i++;
        }
        // 如果j为短串的长度 说明能在长串中找到短串所有的字符
        return j == sLen;
    }

    public static void main(String[] args) {
        IsSubsequence isSubsequence = new IsSubsequence();
        System.out.println(isSubsequence.isSubsequence("axc", "ahbgdc"));
    }
}
