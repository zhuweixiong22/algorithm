package subsequence;

import java.util.Arrays;

/**
 * 435. 无重叠区间
 * 可以转化成递增子序列问题
 *
 * @author novo
 * @date 2022/3/11-22:36
 */
public class Submissions {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 因为该题是要求移除区间的最小数量，使剩余区间互不重叠，区间的位置是无关紧要的，是可以移动的，而最长递增子序列中的元素是不能被移动的，所以我们要先将区间排好序 按start排序
        Arrays.sort(intervals, (o1, o2) -> (o1[0] == o2[0] ? (o1[1] - o2[1]) : (o1[0] - o2[0])));
        int n = intervals.length;
        // dp[i] 表示考虑intervals[0...i]内能构成最长不重叠区间的长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 如果存在j < i 且intervals[j]的end 小于等于 intervals[i]的start 说明这两个区间不重叠
                if (intervals[j][1] <= intervals[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // 找到dp最大值
        int res = 1;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        // 需要移除的最少区间 = 总区间长度 - 最长不重叠区间长度
        return n - res;
    }

    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        int n = intervals.length;
        // 注意这里排序规则是 按end值排序 end值越小 越靠前
        Arrays.sort(intervals, (o1, o2) -> (o1[1] == o2[1] ? (o1[0] - o2[0]) : (o1[1] - o2[1])));
        int res = 1;
        int pre = 0;
        for (int i = 1; i < n; i++) {
            // 如果区间i的起始值 大于等于 前一个区间的end值 则添加区间res++ 更新pre
            if (intervals[i][0] >= intervals[pre][1]) {
                // 这里可以体现出作出的每步贪心决策都无法改变，因为贪心策略是由上一步的最优解推导下一步的最优解，而上一步之前的最优解则不作保留
                res++;
                pre = i;
            }
        }
        return n - res;
    }
}
