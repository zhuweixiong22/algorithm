package arr;

/**
 * 345. 反转字符串中的元音字母
 * @author zwx
 * @date 2022/4/22-23:22
 */
public class LeetCode345 {
    public String reverseVowels(String s) {
        char[] str = s.toCharArray();
        int l = 0;
        int r = str.length - 1;
        while (l < r) {
            while (l < r && !isVowels(str[l])) l++;
            while (l < r && !isVowels(str[r])) r--;
            if (l < r) { // 也可以不判断 因为 l == r 时原地交换一次就结束
                char temp = str[l];
                str[l] = str[r];
                str[r] = temp;
                l++;
                r--;
            }
        }
        return new String(str);
    }

    private boolean isVowels(char c) {
        char lowerChar = Character.toLowerCase(c);
        return lowerChar == 'a' || lowerChar == 'e' || lowerChar == 'i'
                || lowerChar == 'o' || lowerChar == 'u';
    }

    public static void main(String[] args) {
        LeetCode345 leetCode345 = new LeetCode345();
        System.out.println(leetCode345.reverseVowels("xxxbbbcccddd"));
    }
}
