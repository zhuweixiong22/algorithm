package backtrack;

import com.sun.org.apache.xpath.internal.objects.XBoolean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * @author zwx
 * @date 2022-05-09 16:41
 */
public class Offer38 {
    private boolean[] visited;
    List<String> res = new ArrayList<>();
    public String[] permutation(String s) {
        visited = new boolean[s.length()];
        char[] charArr = s.toCharArray();
        Arrays.sort(charArr);
        dfs(charArr, "",0);
        return res.stream().toArray(String[]::new);
    }
    private void dfs(char[] charArr, String path,int index) {
        if (index == charArr.length) {
            res.add(path);
            return;
        }

        for (int i = 0; i < charArr.length; i++) {
            if (!visited[i]) {
                // 去重
                if (i > 0 && !visited[i - 1] && charArr[i] == charArr[i - 1]) {
                    continue;
                }
                visited[i] = true;
                dfs(charArr, path + charArr[i], index + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Offer38 offer38 = new Offer38();
        System.out.println(Arrays.toString(offer38.permutation("aba")));
    }
}
