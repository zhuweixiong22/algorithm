package subsequence;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 拦截导弹
 *
 * @author zwx
 * @date 2022-05-27 22:01
 */
public class AcWing1010 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String str = read.nextLine();
        String[] strings = str.split(" ");
        int n = strings.length;
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(strings[i - 1]);
        }

        int[] f = new int[n + 1];
        int[] g = new int[n + 1];
        Arrays.fill(f, 1);
        Arrays.fill(g, 1);
        int res1 = 1, res2 = 1;

        // 第一问 最长下降子序列（非严格下降）
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (nums[j] >= nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            res1 = Math.max(res1, f[i]);
        }

        // 第二问 最长下降子序列的划分 等价于最长反链长度即最长上升子序列长度
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (nums[j] < nums[i]) {
                    g[i] = Math.max(g[i], g[j] + 1);
                }
            }
            res2 = Math.max(res2, g[i]);
        }

        System.out.println(res1);
        System.out.println(res2);
    }
}
