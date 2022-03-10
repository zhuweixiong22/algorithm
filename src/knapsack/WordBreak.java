package knapsack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 *
 * @author novo
 * @date 2022/2/27-15:33
 */
public class WordBreak {
    // 回溯超时
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        // 一个技巧将list转化成set，使用contains可以降低时间复杂度到大常数级别
        // 而list本质就是通过数组实现的，查找一个元素是否包含要用到遍历，时间复杂度是O（n）
        Set<String> set = new HashSet<>(wordDict);
        return backTracking(s, set, 0);
    }

    private boolean backTracking(String s, Set<String> set, int index) {
        if (index >= s.length()) {
            return true;
        }
        for (int i = index; i < s.length(); i++) {
            // 如果wordDict含有[index, i + 1)的子串，则继续对剩余子串做考察
            // 由于subString生成了新的字符串对象，因此不会对原来的字符串产生影响，因此回溯之后不需要做状态重置。
            if (set.contains(s.substring(index, i + 1))) {
                // 如果wordDict不含有剩余子串则回溯到上一层，i递进 继续考察[index, i + 1)
                if (backTracking(s, set, i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 记忆化搜索
    private int[] memory;

    public boolean wordBreak1(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        memory = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            memory[i] = -1;
        }
        Set<String> set = new HashSet<>(wordDict);
        return backTracking1(s, set, 0);
    }

    private boolean backTracking1(String s, Set<String> set, int index) {
        if (index >= s.length()) {
            return true;
        }
        if (memory[index] != -1) {
            return memory[index] == 1;
        }
        for (int i = index; i < s.length(); i++) {
            // 如果wordDict含有[index, i + 1)的子串，则继续对剩余子串做考察
            // 由于subString生成了新的字符串对象，因此不会对原来的字符串产生影响，因此回溯之后不需要做状态重置。
            if (set.contains(s.substring(index, i + 1))) {
                // 如果wordDict不含有剩余子串则回溯到上一层，i递进 继续考察[index, i + 1)
                if (backTracking1(s, set, i + 1)) {
                    memory[index] = 1;
                    return true;
                }
            }
        }
        memory[index] = 0;
        return false;
    }

    // 动态规划
    public boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        // 遍历背包 字符串
        for (int i = 1; i <= n; i++) {
            // 遍历物品 字典
            for (String word : wordDict) {
                int pre = i - word.length();
                if (pre >= 0 && set.contains(s.substring(pre, i)) && dp[pre] == true) {
                    dp[i] = true;
                    // 只要有一个可以组成就break
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        String str = "catsandog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("an");
        wordDict.add("cat");
        wordDict.add("leet");
        wordDict.add("code");
        WordBreak wordBreak = new WordBreak();
        System.out.println(wordBreak.wordBreak(str, wordDict));
        //System.out.println(str.substring(0,str.length() - 4));
    }
}
