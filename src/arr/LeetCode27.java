package arr;

/**
 * @author novo
 * @date 2022/4/16-21:24
 */
public class LeetCode27 {
    public int removeElement(int[] nums, int val) {
        int k = 0;
        // 定义：维护一个不含val的区间[0, k)
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                if (i > k) { // 减少无效操作
                    nums[k++] = nums[i];
                } else {
                    k++;
                }
            }
        }
        return k;
    }
}
