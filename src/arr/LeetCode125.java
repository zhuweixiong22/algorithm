package arr;

/**
 * @author zwx
 * @date 2022/4/21-23:26
 */
public class LeetCode125 {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        char[] str = s.toCharArray();
        int l = 0;
        int r = str.length - 1;
        while (l < r) {
            // 先匹配到有效的字符
            while (l < r && !(str[l] + "").matches("[0-9A-Za-z]")) l++;
            while (l < r && !(str[r] + "").matches("[0-9A-Za-z]")) r--;
            String lstr = str[l] + "";
            String rstr = str[r] + "";
            // 转换成String再忽略大小写进行比较
            if (!lstr.equalsIgnoreCase(rstr)) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    // Character.isLetterOrDigit()：判断一个字符是否为数字或字母 比正则表达式快几百倍
    public boolean isPalindrome2(String s) {
        if (s.length() == 0) return true;
        char[] str = s.toCharArray();
        int l = 0;
        int r = str.length - 1;
        while (l < r) {
            // 先匹配到有效的字符
            while (l < r && !Character.isLetterOrDigit(str[l])) l++;
            while (l < r && !Character.isLetterOrDigit(str[r])) r--;
            String lstr = str[l] + "";
            String rstr = str[r] + "";
            // 转换成String再忽略大小写进行比较
            if (!lstr.equalsIgnoreCase(rstr)) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode125 leetCode125 = new LeetCode125();
        System.out.println(leetCode125.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(leetCode125.isPalindrome("race a car"));
        System.out.println(leetCode125.isPalindrome("abba"));
        System.out.println(leetCode125.isPalindrome("aba"));
    }
}
