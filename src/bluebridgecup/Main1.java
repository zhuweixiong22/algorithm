package bluebridgecup;

import java.util.Arrays;

/**
 * 1550: [蓝桥杯2021初赛] 卡片
 *
 * @author novo
 * @date 2022/3/21-23:22
 */
public class Main1 {
    public static void main(String[] args) {
        // 模拟10张卡片
        int[] nums = new int[10];
        Arrays.fill(nums, 2021);
        boolean flag = true;
        int res = 0;
        // 可用 for (int i = 1; ; i++) 代替
        while (flag) {
            int num = res + 1;
            while (num != 0) {
                // 取个位数
                int index = num % 10;
                if (nums[index] <= 0) {
                    flag = false;
                    break;
                }
                nums[index]--;
                // 若可以取到个位数 则10进制数继续右移 循环判断是否能取到下一个个位数
                num /= 10;
            }
            res++;
        }
        // 注意结果是res - 1 因为break出来后还是会res++，多加了一次
        System.out.println(res - 1);
    }
}
