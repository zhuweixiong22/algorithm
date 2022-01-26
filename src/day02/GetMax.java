package day02;

/**
 * 递归求最大值
 *
 * @author novo
 * @date 2022/1/26-15:24
 */
public class GetMax {
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int LMax = process(arr, L, mid);
        int RMax = process(arr, mid + 1, R);
        return Math.max(LMax, RMax);
    }

    public static void main(String[] args) {
        int[] arr = {3,4,5,6,7,1,3,6,8,43,23,5};
        for(int i = 0;i<1000;i++){
            System.out.println(getMax(arr));
        }
    }
}
