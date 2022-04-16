package arr;

/**
 * @author novo
 * @date 2022/4/16-23:10
 */
public class LeetCode80 {
    public int removeDuplicates(int[] nums) {
        // 定义：维护一个[0, k) 每个元素 最多出现两次的升序数组
        // 那么nums[k - 2]就是前两个元素，只需要判断枚举nums[i]是否和nums[k - 2]是否相等即可
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (k < 2 || nums[i] != nums[k - 2]) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }
}
