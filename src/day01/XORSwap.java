package day01;

/**
 * @author novo
 * @date 2022/1/23-22:43
 */
public class XORSwap {
    public static void main(String[] args) {

        int a = 3;
        int b = 3;

        System.out.println(a);
        System.out.println(b);


        a = a ^ b;
        b = a ^ b;
        a = a ^ b;


        System.out.println("a-->" + a);
        System.out.println("b-->" + b);


        int[] arr = {3, 1, 100};

        int i = 1;
        int j = 1;

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

        System.out.println(arr[i] + " , " + arr[j]);


        System.out.println(arr[0]);
        System.out.println(arr[2]);

        swap(arr, 0, 0);

        System.out.println(arr[0]);
        System.out.println(arr[2]);


    }

    public static void swap(int[] arr, int i, int j) {
        // arr[0] = arr[0] ^ arr[0];
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
