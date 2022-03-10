package binarysearch;

/**
 * @author novo
 * @date 2022/1/24-10:40
 */
public class review {
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] < num) {
                L = mid + 1;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            }
        }
        return sortedArr[L] == num;
    }

    public static int nearestIndexLeft(int[] sortedArr, int value) {
        if (sortedArr == null || sortedArr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        int index = -1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    public static int nearestIndexRight(int[] sortedArr, int value) {
        if (sortedArr.length == 0 || sortedArr == null) {
            return -1;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        int index = -1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] <= value) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    public static int getLessIndex(int[] arr, int value) {
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
        int R = arr.length - 2;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid - 1] < arr[mid]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return L;
    }

    public static void printOddTimesNum1(int[] arr) {
        int XOR = 0;
        for (int i = 0; i < arr.length; i++) {
            XOR ^= arr[i];
        }
        System.out.println(XOR);
    }

    public static void printOddTimesNum2(int[] arr) {
        // 得到a ^ b
        int XOR1 = 0;
        for (int i = 0; i < arr.length; i++) {
            XOR1 ^= arr[i];
        }
        // 找出其中一个位置的1
        int onlyOne = XOR1 & (~XOR1 + 1);
        int XOR2 = 0;
        // 将a b 分组
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & onlyOne) != 0) { // !=0 对该位置为1的一半进行异或
                XOR2 ^= arr[i];
            }
        }
        System.out.println(XOR2 + "" + (XOR1 ^ XOR2));
    }

    public static int onlyOneKTimes(int[] arr, int k, int m) {
        // 将每个num位的状态都存入count
        int[] count = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) != 0) { // 与1(00...001)进行与运算不等于0说明比较的第i位为1,就累加
                    count[i]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((count[i] % m) != 0) { // 说明出现k次的num在这位上不为0
                res |= (1 << i);
            }
        }
        return res;
    }
}
