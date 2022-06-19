package exp.greedy;

import java.util.Scanner;

/**
 * 求解汽车加油问题
 * 且旅途中有 n个加油站。 第二行有 n个整数，表示第 n 个加油站与第n-1 个加油站之间的距离。
 * 第 0 个加油站表示出发地，汽车已加满油（到达最后一个加油站则认为到达目的地）。
 *
 * @author zwx
 * @date 2022-06-12 12:06
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("输入满油一次行驶的最大距离：");
        int d = read.nextInt();
        System.out.println("输入加油站的个数及距离：");
        int n = read.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = read.nextInt();
        }
        solution(nums, d);
    }

    // 贪心策略：应尽可能地使汽车的油量耗尽再进行加油
    private static void solution(int[] nums, int d) {

        int max = d;
        for (int i = 1; i < nums.length; i++) {
            d -= nums[i - 1];
            if (d < 0) {
                System.out.println("汽车无法到达终点");
            }
            // 已到达加油站i - 1
            if (d < nums[i]) {
                System.out.println("在加油站" + i + "加油");
                d = max;
            }
        }
    }
}
