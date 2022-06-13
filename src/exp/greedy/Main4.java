package exp.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * SSTF寻道算法（贪心）
 * 输入请求磁道个数：
 * 8
 * 输入磁盘请求序列：
 * 98 183 37 122 14 124 65 67
 * 输入当前磁头位置：
 * 53
 * [14, 37, 53, 65, 67, 98, 122, 124, 183]
 * 53->65->67->37->14->98->122->124->183	磁头移动总数：236
 *
 * @author zwx
 * @date 2022-06-12 16:11
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("输入请求磁道个数：");
        int n = read.nextInt();
        int[] nums = new int[n + 1];
        System.out.println("输入磁盘请求序列：");
        for (int i = 0; i < n; i++) {
            nums[i] = read.nextInt();
        }
        System.out.println("输入当前磁头位置：");
        nums[n] = read.nextInt();
        solution(nums, nums[n]);
    }

    private static void solution(int[] nums, int c) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        System.out.print(c);
        int curIndex = -1;
        int n = nums.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == c) {
                curIndex = i;
                visited[i] = true;
                n--;
                break;
            }
        }

        int sum = 0;
        int min = -1;
        while (n-- > 0) {
            int l = curIndex;
            int r = curIndex;
            while (l > 0 && visited[l]) l--;
            while (r < nums.length - 1 && visited[r]) r++;
            // l == 0的情况
            if (visited[l]) {
                min = nums[r] - nums[curIndex];
                curIndex = r;
            } else if (visited[r]) {
                // r 为最后一个数的情况
                min = nums[curIndex] - nums[l];
                curIndex = l;
            } else {
                min = Math.min(nums[curIndex] - nums[l], nums[r] - nums[curIndex]);
                curIndex = nums[curIndex] - nums[l] < nums[r] - nums[curIndex] ? l : r;
            }
            System.out.print("->" + nums[curIndex]);
            sum += min;
            visited[curIndex] = true;
        }
        System.out.println("\t磁头移动总数：" + sum);
    }


    private static void solution2(int[] nums, int c) {
        // TODO
    }


    // 使用List来二分，删除元素复杂度依然为O（N）
    private static int binarySearch(List<Integer> list, int cur) {
        int l = 0;
        int r = list.size() - 1;
        while (r - l >= 2) {
            int mid = l + ((r - l) >> 1);
            if (list.get(mid) == cur) {
                return list.get(mid);
            } else if (list.get(mid) < cur) {
                l = mid;
            } else if (list.get(mid) > cur) {
                r = mid;
            }
        }
        // 返回最接近的两个数，如果寻找的cur不在两端，则返回ceil和floor
        int ceil = list.get(l);
        int floor = list.get(r);
        if (Math.abs(ceil - cur) < Math.abs(floor - cur)) {
            list.remove(l);
            return ceil;
        } else {
            list.remove(r);
            return floor;
        }
    }
}
