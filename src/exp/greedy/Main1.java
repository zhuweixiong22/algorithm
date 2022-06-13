package exp.greedy;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 求解一个序列中出现次数最多的元素问题
 *
 * @author zwx
 * @date 2022-06-12 10:34
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = read.nextInt();
        }

        System.out.println(solution2(nums));
        System.out.println(solution1(nums));
    }

    // 空间O(1)
    private static int solution1(int[] nums) {
        Arrays.sort(nums);
        int k = 1;
        int maxCount = 0;
        int maxIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                k++;
                if (k > maxCount) {
                    maxCount = k;
                    maxIndex = i;
                }
            } else {
                k = 1;
            }
        }
        return nums[maxIndex];
    }


    // 哈希表
    private static int solution2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int maxNum = Integer.MAX_VALUE;
        int maxCount = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            int key = entry.getKey();

            if (value >= maxCount) {
                if (value == maxCount && key > maxNum) {
                    // 如果次数相同，则保存值较小的那个
                    continue;
                }
                maxCount = value;
                maxNum = key;
            }
        }
        return maxNum;
    }

}
