package sort;

/**
 * @author novo
 * @date 2022/3/28-20:32
 */
public class InsertSort {
    public static void main(String[] args) {

    }

    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while ((j >= 0) && (temp < arr[j])) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }
}
