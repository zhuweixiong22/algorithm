package day01;

/**
 * 给出一个无序且相邻两个数不相等的数组，找出一个局部最小数（找谷底）
 * <p>
 * 给出局部最小定义：
 * <p>
 * [0]位置时，只要[0] < [1]，那么[0]就是局部最小
 * <p>
 * [N - 1]位置时，只要[N - 2] > [N - 1]，那么[N - 1]就是局部最小
 * <p>
 * [i]位置(不在0和N - 1)，需要[i - 1] > [i] < [i + 1]
 *
 * @author novo
 * @date 2022/1/23-22:00
 */
public class BinarySearchAwesome {
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            // 我们只需要得到确定某一半肯定有的条件就可以，不需要有具体分情况
            // 如左右位置都比mid小 下一步二分找哪一边都行
            if (arr[mid - 1] < arr[mid]) { //   \  ?  ?  ? /       /
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) { //   \     \ ? ? ? /
                L = mid + 1;
            } else { //   \    \/    /
                return mid;
            }
        }
        return L; // 因为循环条件至少是两个数 还剩一个数没有判断 返回L和R都可以
    }
}
