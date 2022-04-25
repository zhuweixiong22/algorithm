package arr;

/**
 * @author zwx
 * @date 2022/4/22-23:37
 */
public class LeetCode11 {
    // 暴力枚举
    public int maxArea(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < height.length; j++) {
                if (i != j) {
                    int lowHigh = Math.min(height[i], height[j]);
                    res = Math.max(res, Math.abs(j - i) * lowHigh);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LeetCode11 leetCode11 = new LeetCode11();
        int[] nums = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(leetCode11.maxArea(nums));
    }
}
