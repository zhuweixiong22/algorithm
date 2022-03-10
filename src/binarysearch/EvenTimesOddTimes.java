package binarysearch;

/**
 * @author novo
 * @date 2022/1/24-13:41
 */
public class EvenTimesOddTimes {
    // arr中，只有一种数出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int XOR = 0;
        for (int i = 0; i < arr.length; i++) {
            XOR ^= arr[i];
        }
        System.out.println(XOR);
    }

    // arr中，有两种数a，b出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int XOR1 = 0;
        for (int i = 0; i < arr.length; i++) {
            XOR1 ^= arr[i]; //最终结果 a ^ b
        }
        int onlyOne = XOR1 & (~XOR1 + 1); // XOR1 & (-XOR1)
        int XOR2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & onlyOne) != 0) { // 或者(arr[i] & onlyOne) == 0
                XOR2 ^= arr[i];
            }
        }
        System.out.println((XOR1 ^ XOR2) + " " + XOR2);
    }

    // arr中有一种数出现K次，其余数均出现M次且M > 1,K < M,找出出现K次的数
    public static int onlyOneKTimes(int[] arr, int k, int m) {
        int[] count = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                /*if (((num >> i) & 1) != 0) { // 与1(00...001)进行与运算不等于0说明比较的第i位为1,就累加
                    count[i]++;
                }*/
                // 优化版本 (count[i] >> i)为0的时候再与1 相当于+0 等于不累加
                count[i] += ((num >> i) & 1);
            }
        }
        // 注意这里如果出现K次那个数为0的话 if是进不去的 但是恰好res初始值为0 所以返回值res正确
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((count[i] % m) != 0) { // 说明i位上 出现k次那个数的二进制数不为0(即为1)
                res |= (1 << i); // 0或上第i位上的1 从右至左在符合条件的位置上填1
            }
        }
        return res;
    }

    // 在上一个问题上改进一下如果其余数都出现M次，有一个数出现K次返回这个数，这个数不出现K次返回-1
    public static int onlyOneKTimesOrNon(int[] arr, int k, int m) {
        int[] count = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                count[i] += ((num) >> i) & 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (count[i] % m == 0) { // 说明i位上那个数不为1 跳出一次循环
                continue;
            }
            if (count[i] % m == k) { // 走到这说明count[i] % m != 0且count[i] % m == k
                res |= (1 << i);
            } else {
                return -1;
            }
        }
        // 注意res == 0 不一定是结果 可能是不出现M次的数为0，是一个边界条件 需要特殊处理一下
        if (res == 0) {
            int i = 0;
            for (int num : arr) {
                if (num == 0) {
                    i++;
                }
            }
            if (i != k) {
                return -1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 2, 3, 3, 4};
        int[] arr2 = {1, 2, 2, 3, 4, 4};
        int[] arr3 = {1, 1, 2, 2, 3, 3, 0};
        int[] arr4 = {1, 1, 2, 2, 3, 3, 0};
        printOddTimesNum1(arr1);
        printOddTimesNum2(arr2);
        int i = onlyOneKTimes(arr3, 1, 2);
        System.out.println(i);
        int i1 = onlyOneKTimesOrNon(arr4,0,2);
        System.out.println(i1);
    }
}
