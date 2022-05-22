package exp.fenzhi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 半数集问题
 * @author zwx
 * @date 2022-05-22 13:51
 */
public class Main4 {
    public static void main(String[] args) {
        System.out.println("input the n: ");
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        Arrays.fill(memory, -1);
        System.out.println(dfs(n));
    }

    // 记忆化搜索
    private static int[] memory = new int[100000];

    public static int dfs(int n) {
        // 当前选择添加n
        int res = 1;
        if (memory[n] != -1) {
            return memory[n];
        }
        // f(n)=1+f(1)+f(2)+....+f(n/2)
        // 递归终止条件隐含在i <= n / 2
        for (int i = 1; i <= n / 2; i++) {
            // 选择添加i，则最近添加的数就为i，传递下去
            res += dfs(i);
            memory[n] = res;
        }
        return res;
    }
}
