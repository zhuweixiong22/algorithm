package subsequence;

import java.util.*;

public class OneAnsOfLongestIncreasingSubsequenceAcm {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] nums = new int[n];
        int[] dp = new int[n];
        int[] memory = new int[n]; // 用于记录转移的过程
        int index = 0;
        int res = 1;

        for (int i = 0; i < n; i++) {
            nums[i] = read.nextInt();
        }
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    // 记录下每个状态从哪里来的
                    memory[i] = j;
                }
            }
            // 求出最大长度 和 该序列结尾的索引
            if (dp[i] > res) {
                res = dp[i];
                index = i;
            }
        }

        Deque<Integer> stack = new ArrayDeque<>();
        // 倒序输入栈
        for (int i = 0, len = dp[index]; i < len; i++) {
            stack.push(nums[index]);
            index = memory[index];
        }
        // 最后再输出栈即可
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
