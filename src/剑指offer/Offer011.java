package 剑指offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 011. 0 和 1 个数相同的子数组
 * @author zwx
 * @date 2022-06-20 22:46
 */
public class Offer011 {
    // 跟010思路一样 前缀信息+哈希表
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 预处理前缀信息 preSum[i]存储 [0~ i - 1]区间内1和0的数量差值
        int[] preSum = new int[n + 1];
        int oneCount = 0;
        int zeroCount = 0;
        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] == 1) {
                oneCount++;
            } else {
                zeroCount++;
            }
            preSum[i] = oneCount - zeroCount;
        }

        // 因为求的是最长的一个符合要求的区间，所以map的value存的是key第一次出现的下标，因为下标越小，与i的距离越大
        // 使得区间[j, i - 1]中1和0的差值为0  ==> preSum[i] - preSum[j] = 0 ==> preSum[i] = preSum[j]
        // 也就是当枚举到nums[i - 1]时 我们在i之前寻找是值等于preSum[i]的 j
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        // TODO 为什么算区间长度的时候不用加1?
        for (int i = 1; i <= n; i++) {
            if (map.containsKey(preSum[i])) {
                res = Math.max(res, i - map.get(preSum[i]));
            } else {
                map.put(preSum[i], i);
            }
        }
        return res;
    }
}
