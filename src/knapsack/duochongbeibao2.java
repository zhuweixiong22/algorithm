package knapsack;

import java.util.*;

public class duochongbeibao2 {
    static int n;
    static int C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        C = sc.nextInt();
        int count = 1;

        // 新物品的数量是 n * ([logSmax] + 1) --> 上取整 n为原不同物品的数量，所以wv的数组只要比它大就行
        int[] w = new int[20 * n];
        int[] v = new int[20 * n];
        int[] dp = new int[C + 1];

        for (int i = 1; i <= n; i++) {
            int W = sc.nextInt();
            int V = sc.nextInt();
            int S = sc.nextInt();
            int k = 1;
            // 按二进制拆分多个物品组合成一个新的物品，我们就可以当每一件组合成的物品都是不一样的，从而转化为01背包求解
            while (k <= S) {
                w[count] = k * W;
                v[count] = k * V;
                S -= k;
                k <<= 1; // 左移一位
                count++;
            }
            // 还有剩余
            if (S > 0) {
                w[count] = S * W;
                v[count] = S * V;
                count++;
            }
        }

        // 组合后新物品的总数量
        n = count;

        for (int i = 1; i <= n; i++) {
            for (int j = C; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }

        System.out.println(dp[C]);
    }
}
