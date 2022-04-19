package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 什么时候快排会退化：
 * 1、完全有序：每次选的pivot都为最小值
 * 2、所有元素相等 : 只分两个区间的问题 >= <
 *
 * @author novo
 * @date 2022/4/18-21:01
 */
public class QuickSort {
    private static Random random = new Random();

    private QuickSort() {
    }

    private static <E extends Comparable<E>> void sort(E[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    /**
     * 单路快排
     * @param nums
     * @param l
     * @param r
     * @param <E>
     */
    private static <E extends Comparable<E>> void sort(E[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition2(nums, l, r);
        sort(nums, l, p - 1);
        sort(nums, p + 1, r);
    }


    private static <E extends Comparable<E>> int partition(E[] nums, int l, int r) {
        // nums[l]为基准值v nums[l + 1...j] < v , nums[j + 1...i] >= v
        int p = l + random.nextInt(r - l + 1); // [0, r - l]的随机索引 + l ==> [l, r]的随机索引
        swap(nums, l, p);
        E pivot = nums[l];
        int j = l;
        for (int i = l + 1; i <= r; ) {
            if (nums[i].compareTo(pivot) >= 0) {
                // nums[i] >= v
                i++;
            } else {
                // nums[i] < v
                swap(nums, ++j, i++);
            }
        }
        // 最后一步
        swap(nums, l, j);
        return j;
    }


    private static <E extends Comparable<E>> void sort2ways(E[] nums) {
        sort2ways(nums, 0, nums.length - 1);
    }

    /**
     * 双路快排
     * @param nums
     * @param l
     * @param r
     * @param <E>
     */
    private static <E extends Comparable<E>> void sort2ways(E[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition2(nums, l, r);
        // p之前的数 < nums[p]; p之后的数 >= nums[p]
        sort2ways(nums, l, p - 1);
        sort2ways(nums, p + 1, r);
    }

    private static <E extends Comparable<E>> int partition2(E[] nums, int l, int r) {
        // nums[l]为基准值v nums[l + 1...i] <= v , nums[j...r] >= v (这里的i,j指处理后)
        // i，j 都等于v时，也交换，从到达到将等于v的元素平均分配到两个区间
        int p = l + random.nextInt(r - l + 1);
        swap(nums, l, p);
        E pivot = nums[l];
        int i = l, j = r + 1;
        while (i < j) { // 循环里没有使用break，所以这里也是严格小于，否则 i == j 时会死循环
            do i++; while (i < r && nums[i].compareTo(pivot) < 0); // 注意边界 因为是do-while 所以严格小于
            do j--; while (j > l && nums[j].compareTo(pivot) > 0);
            if (i < j) swap(nums, i, j);
        }

        swap(nums, l, j);
        return j;
    }


    /**
     * 三路快排
     * @param nums
     * @param <E>
     */
    private static <E extends Comparable<E>> void sort3ways(E[] nums) {
        sort3ways(nums, 0, nums.length - 1);
    }

    private static <E extends Comparable<E>> void sort3ways(E[] nums, int l, int r) {
        if (l >= r) return;
        int p = l + random.nextInt(r - l + 1);
        swap(nums, l, p);
        E pivot = nums[l];
        // nums[l + 1, lt] < v, nums[lt + 1, i - 1] == v, nums[gt, r] > v
        int lt = l, i = l + 1, gt = r + 1;
        while (i < gt) {
            if (nums[i].compareTo(pivot) < 0) {
                swap(nums, ++lt, i++);
            } else if (nums[i].compareTo(pivot) > 0) {
                swap(nums, --gt, i);
            } else {
                i++;
            }
        }

        swap(nums, l, lt);
        // 最后一步交换后区间定义改变 nums[l, lt - 1] < v, nums[lt, gt - 1] == v, nums[gt, r] > v
        sort3ways(nums, l, lt - 1);
        sort3ways(nums, gt, r);
    }

    private static <E> void swap(E[] nums, int i, int j) {
        E temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static Integer[] gennerateArray(int len, int max) {
        Integer[] arr = new Integer[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }


    /**
     * 经典双路快排
     * @param q
     * @param l
     * @param r
     */
    public static void quick_sort(Integer[] q, int l, int r) {
        if (l >= r) return;

        int i = l - 1, j = r + 1, x = q[l + r >> 1];
        while (i < j) {
            do i++; while (q[i] < x);
            do j--; while (q[j] > x);
            if (i < j) swap(q, i, j);
        }
        quick_sort(q, l, j);
        quick_sort(q, j + 1, r);
    }


    public static <E> void print(E[] nums) {
        for (E num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {

    }
}
