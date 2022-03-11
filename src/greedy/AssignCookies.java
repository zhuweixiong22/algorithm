package greedy;

import java.util.Arrays;
import java.util.Collections;

/**
 * 455. 分发饼干
 *
 * @author novo
 * @date 2022/3/11-19:30
 */
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        // 贪心算法 尽量用大的饼干满足胃口大的小朋友
        Arrays.sort(g);
        Arrays.sort(s);

        int res = 0;
        int i = g.length - 1;
        int j = s.length - 1;

        while (i >= 0 && j >= 0) {
            // 如果最大的饼干能满足当前最大胃口的孩子 则res + 1
            if (s[j] >= g[i]) {
                res++;
                i--;
                j--;
            } else {
                // 否则当前最大胃口的孩子无法满足 跳过该孩子
                i--;
            }
        }
        return res;
    }

    public int findContentChildren1(int[] g, int[] s) {
        // 贪心算法 尽量用小的饼干满足胃口小的小朋友
        Arrays.sort(g);
        Arrays.sort(s);

        int res = 0;
        int i = 0;
        int j = 0;

        while (i < g.length && j < s.length) {
            // 如果最小的饼干能满足当前最小胃口的孩子 则res + 1
            if (s[j] >= g[i]) {
                res++;
                i++;
            }
            // 无论是否满足 当前饼干索引都要加1
            j++;
        }
        return res;
    }
}
