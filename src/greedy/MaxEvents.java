package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最多可以参加的会议数目 与leetcode上的题目不同
 * 数组0 1 放的是会议开始时间和结束时间
 * @author novo
 * @date 2022/2/3-16:56
 */
public class MaxEvents {
    public int maxEvents(int[][] events) {
        Arrays.sort(events,(Comparator.comparingInt(event -> event[1])));
        int curTimeLine = 0;
        int res = 0;
        for (int[] event : events) {
            if (curTimeLine <= event[0]) {
                res++;
                curTimeLine = event[1];
            }
        }
        return res;
    }
}
