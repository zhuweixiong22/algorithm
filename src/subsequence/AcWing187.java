package subsequence;

import java.util.Scanner;

/**
 * 导弹系统
 *
 * @author zwx
 * @date 2022-05-27 23:55
 */
public class AcWing187 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        while (read.hasNext()) {
            int n = read.nextInt();
            if (n == 0) break;
            int[] nums = new int[n];
            res = Integer.MAX_VALUE; // 多组数据记得重置res
            for (int i = 0; i < n; i++) {
                nums[i] = read.nextInt();
            }
            dfs(nums, 0, 0, 0, n);
            System.out.println(res);
        }
    }


    private static int[] up = new int[10000]; // up数组存储每个上升序列的结尾元素
    private static int[] down = new int[10000]; // down数组存储每个下降序列的结尾元素
    private static int res = Integer.MAX_VALUE; // 维护全局最优值

    // 先枚举将该数放到上升序列还是下降序列中
    private static void dfs(int[] nums, int index, int upLen, int downLen, int len) {
        // 剪枝 如果当前分支子序列的个数已经大于等于res，则没必要继续下去
        if (upLen + downLen >= res) {
            return;
        }
        if (index == len) {
            res = Math.min(res, upLen + downLen);
            return;
        }

        // 情况一：当前数nums[index]接到已有上升序列后
        boolean flag = false;
        for (int i = 0; i < upLen; i++) {
            // 接到结尾元素小于该数且最接近该数的上升序列后
            if (up[i] < nums[index]) {
                // up数组是单调下降的，因为越靠近前面的元素会越来越大，所以第一个小于nums[index]的就是最接近nums[index]的
                int temp = up[i];
                up[i] = nums[index];
                dfs(nums, index + 1, upLen, downLen, len);
                // 状态重置
                up[i] = temp;
                flag = true;
                break;
            }
        }
        // 情况二：当前数nums[index]作为新的一个上升序列
        if (!flag) {
            up[upLen] = nums[index];
            dfs(nums, index + 1, upLen + 1, downLen, len);
        }

        // 情况三：当前数nums[index]接到已有下降序列后
        flag = false;
        for (int i = 0; i < downLen; i++) {
            // 接到结尾元素大于该数且最接近该数的下降序列后
            if (down[i] > nums[index]) {
                // down数组是单调上升的，所以第一个大于nums[index]的就是最接近nums[index]的
                int temp = down[i];
                down[i] = nums[index];
                dfs(nums, index + 1, upLen, downLen, len);
                // 状态重置
                down[i] = temp;
                flag = true;
                break;
            }
        }
        // 情况四：当前数nums[index]作为新的一个下降序列
        if (!flag) {
            down[downLen] = nums[index];
            dfs(nums, index + 1, upLen, downLen + 1, len);
        }
    }
}
