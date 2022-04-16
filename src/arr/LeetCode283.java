package arr;

/**
 * 283. 移动零
 * @author novo
 * @date 2022/4/15-23:40
 */
public class LeetCode283 {
    // 双指针交换 循环不变量k来维护一个不为零的区间[ )
    public void moveZeroes(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 判断 i 是否大于 k 可以减少无用的交换
                if (i > k) {
                    nums[k++] = nums[i];
                    nums[i] = 0;
                } else {
                    k++;
                }
            }
        }
    }

    // 双指针覆盖
    public void moveZeroes2(int[] nums) {
        int k = 0;
        // 覆盖前面的值
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }
        // 把后面的0补上
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
