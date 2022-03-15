package math;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author novo
 * @date 2022/3/15-19:23
 */
public class ABC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[7];
        for (int i = 0; i < 7; i++) {
            nums[i] = sc.nextInt();
        }

        Arrays.sort(nums);
        int C = nums[6] - nums[0] - nums[1];
        System.out.println(nums[0] + " " + nums[1] + " " + C);
    }
}
