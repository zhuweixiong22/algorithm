package arr;

/**
 * @author novo
 * @date 2022/4/16-23:09
 */
public class LeetCode26 {
    public int removeDuplicates(int[] nums) {
        // 定义：维护一个严格升序的区间[0, k] 长度为 k+1
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[k]) {
                if (i > k) {
                    nums[++k] = nums[i];
                } else {
                    k++;
                }
            }
        }
        return k + 1;
    }
}
