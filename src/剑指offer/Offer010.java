package 剑指offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 010. 和为 k 的子数组
 *
 * @author zwx
 * @date 2022-06-19 22:31
 */
public class Offer010 {

    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // Si和 及 和为Si的数量
        Map<Integer, Integer> map = new HashMap<>();
        // 枚举每个区间的结尾 [0, n - 1]
        // 先将preSum[0] = 0 放进去
        map.put(0, 1);
        for (int i =  1; i <= n; i++) {
            // preSum[i] 枚举的终点是nums[i - 1]
            res += map.getOrDefault(preSum[i] - k, 0);
            map.put(preSum[i], map.getOrDefault(preSum[i], 0) + 1);
        }

        return res;
    }
    public int subarraySum3(int[] nums, int k) {
        int res = 0;
        int len = nums.length;
        int[] preSum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (preSum[j + 1] - preSum[i] == k) {
                    res++;
                }
            }
        }

        return res;
    }

    // 暴力枚举 左右边界
    public int subarraySum2(int[] nums, int k) {
        int res = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum += nums[j];
                if (sum == k) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Offer010 offer010 = new Offer010();
        int[] nums = new int[]{-1, -1, 1};
        offer010.subarraySum(nums, 0);
    }
}
