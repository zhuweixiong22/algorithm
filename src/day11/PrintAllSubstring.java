package day11;

import sun.applet.AppletResourceLoader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 打印一个字符串的全部子序列
 * 打印一个字符串的去重全部子序列
 *
 * @author novo
 * @date 2022/2/5-23:07
 */
public class PrintAllSubstring {
    public static List<String> printAllSubString(String str) {
        ArrayList<String> res = new ArrayList<>();
        process(str.toCharArray(), 0, res, "");
        // 第一个全都不选 为""
        res.remove(0);
        return res;
    }

    // 遍历原字符串的每个位置
    public static void process(char[] str, int index, List<String> res, String path) {
        // 遍历完 已经没有选择了 path就是其中一个答案
        if (index == str.length) {
            res.add(path);
            return;
        }
        // 对于当前index位置，只有两个选择，选和不选
        process(str, index + 1, res, path);
        process(str, index + 1, res, path + str[index]);

    }

    public static void main(String[] args) {
        List<String> allSubString = printAllSubString("abc");
        Set<String> set = new HashSet<>(allSubString);
        allSubString.forEach(System.out::println);
        System.out.println("去重转化为set============");
        set.forEach(System.out::println);
    }
}
