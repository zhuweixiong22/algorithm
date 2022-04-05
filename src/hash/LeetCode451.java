package hash;

import java.util.*;

/**
 * @author novo
 * @date 2022/4/5-21:23
 */
public class LeetCode451 {
    // 模拟
    public String frequencySort(String s) {
        char[] c = s.toCharArray();
        int[][] count = new int[128][2];
        StringBuilder str = new StringBuilder();
        // 第一维记录下原来的索引，因为后面排序会导致位置发生变化
        for (int i = 0; i < 128; i++) {
            count[i][0] = i;
        }
        // 第二维记录数量
        for (int i = 0; i < c.length; i++) {
            count[c[i]][1]++;
        }
        Arrays.sort(count, (o1, o2)-> o2[1] - o1[1]);
        for (int i = 0; i < 128; i++) {
            // 每一个字符的数量
            int n = count[i][1];
            while (n-- > 0) {
                str.append((char)count[i][0]);
            }
        }
        return str.toString();
    }

    class Node {
        char c;
        int num;
        Node(char c, int num) {
            this.c = c;
            this.num = num;
        }
    }
    public String frequencySort2(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder str = new StringBuilder();
        PriorityQueue<Node> maxHeap = new PriorityQueue<>(((o1, o2) -> {
            if (o1.num == o2.num) {
                // 如果添加条件 数量一致按字典序
                return o2.c - o1.c;
            } else {
                return o2.num - o1.num;
            }
        }));
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        for (char c : map.keySet()) {
            maxHeap.add(new Node(c, map.get(c)));
        }

        while (!maxHeap.isEmpty()) {
            Node node = maxHeap.poll();
            int n = node.num;
            while (n-- > 0) {
                str.append(node.c);
            }
        }

        return str.toString();
    }
    public static void main(String[] args) {
        LeetCode451 leetCode451 = new LeetCode451();
        System.out.println(leetCode451.frequencySort("tree"));
    }
}
