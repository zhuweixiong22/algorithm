package day11;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 *
 * @author novo
 * @date 2022/2/7-12:41
 */
public class LetterPhoneNumber {
    private String[] letterMap = {
            " ",   // 0
            "",    // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };
    private List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        // 测试用例可能为空
        if ("".equals(digits)){
            return res;
        }
        String path = "";
        backTracking(digits.toCharArray(), 0, path);
        return res;
    }

    public void backTracking(char[] digits, int index, String path) {
        System.out.println(index + ":" + path);
        if (index == digits.length) {
            res.add(path);
            return;
        }
        // 减去'0' 对应的就是哪个letter
        String letters = letterMap[digits[index] - '0'];
        int n = letters.length();
        for (int i = 0; i < n; i++) {
            System.out.println("digits[" + index + "] = " + path + " , use " + letters.charAt(i));
            backTracking(digits, index + 1, path + letters.charAt(i));
        }
        System.out.println("digits[" + index + "] = " + path + " complete, return");
    }

    // for test
    private static void printList(List<String> list) {
        for (String s : list)
            System.out.println(s);
    }

    public static void main(String[] args) {
        printList(new LetterPhoneNumber().letterCombinations("234"));
    }
}
