package tool;

/**
 * @author zwx
 * @date 2022-05-05 15:14
 */
public class LeetCodeTool {
    public static String getArr(String str) {
        char[] charArr = str.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == '[') {
                charArr[i] = '{';
            } else if (charArr[i] == ']') {
                charArr[i] = '}';
            }
        }
        return new String(charArr);
    }
}
