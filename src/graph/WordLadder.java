package graph;

import java.util.*;

/**
 * @author novo
 * @date 2022/3/18-22:14
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0) {
            return 0;
        }
        // 字典放到哈希表中，因为我们需要快速的判断某个单词是否在字典中
        Set<String> wordSet = new HashSet<>(wordList);

        // 因为这个是一个无向图 为了避免匹配重复单词 需要记录已访问的结点
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int depth = 0;
        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            depth++;
            // O(26 * word.length())
            for (int i = 0; i < levelCount; i++) {
                String cur = queue.poll();
                char[] curWord = cur.toCharArray();
                // 枚举cur修改一个字符后的所有单词 26 * 单词长度 - 1种可能（所有单词长度相等）
                for (int j = 0; j < endWord.length(); j++) {
                    // 因为每个单词只能修改一个字符，所以在更换前需要备份，之后需要恢复
                    char originChar = curWord[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        // 跳过本身的字符
                        if (j == originChar) {
                            continue;
                        }
                        // 修改一个字符
                        curWord[j] = k;
                        String next = String.valueOf(curWord);
                        // 判断修改一个字符后的单词是否在字典中
                        if (wordSet.contains(next)) {
                            // 判断是否新单词是否与end匹配
                            if (next.equals(endWord)) {
                                return depth + 1;
                            }
                            // 该结点未被访问过，则选择该结点当做路径 并标记
                            if (!visited.contains(next)) {
                                visited.add(next);
                                queue.add(next);
                            }
                        }
                    }
                    // 恢复该字符，进入下一个字符位枚举
                    curWord[j] = originChar;
                }
            }
        }
        // 不存在这样的转换序列返回0
        return 0;
    }
}
