package 剑指offer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zwx
 * @date 2022-06-07 22:46
 */
public class Offer005 {

    // 位运算
    public int maxProduct(String[] words) {
        int length = words.length;
        int[] code = new int[length];

        // 对每个单词进行编码预处理
        for (int i = 0; i < length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                // |= 经常用于填充1 从右往左
                code[i] |= (1 << (word.charAt(j) - 'a'));
            }
        }

        int res = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                // 如果两个单词的code进行与运算结果不为0 说明存在某些位两个单词的bit都是1，说明存在相同字符
                if ((code[i] & code[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }

        return res;
    }

    /*// 暴力枚举
    public int maxProduct(String[] words) {
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            String stri = words[i];
            Set<Character> set = new HashSet<>();
            for (int k = 0; k < stri.length(); k++){
                set.add(stri.charAt(k));
            }

            for (int j = i + 1; j < words.length; j++) {
                String strj = words[j];
                boolean notHasSame = true;
                for (int k = 0; k < strj.length(); k++) {
                    if (set.contains(strj.charAt(k))) {
                        notHasSame = false;
                        break;
                    }
                }
                if (notHasSame) {
                    res = Math.max(res, stri.length() * strj.length());
                }
            }
        }
        return res;
    }*/

    public static void main(String[] args) {
        Offer005 offer005 = new Offer005();
        String[] words = new String[]{"abcw","baz","foo","bar","fxyz","abcdef"};
        System.out.println(offer005.maxProduct(words));
    }
}
