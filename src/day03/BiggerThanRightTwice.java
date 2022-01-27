package day03;

/**
 * @author novo
 * @date 2022/1/27-15:41
 */
public class BiggerThanRightTwice {
    public static int biggerThan2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }
    public static int biggerThan2Zuo(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return processZuo(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    public static int processZuo(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1, R) + mergeZuo(arr, L, mid, R);
    }

    // 复制指针
    public static int merge(int[] arr, int L, int M, int R) {
        int[] mergeArr = new int[R - L + 1];
        // 因为p1 p2从右边开始 merge数组也要从右边写入
        int i = mergeArr.length - 1;
        // 因为是升序 求右边有多少个数比它小，索引p1 p2就要从右边开始比较
        int p1 = M;
        int p2 = R;
        int res = 0;
        int p3 = p1;
        int p4 = p2;
        while (p3 >= L && p4 > M) {
            if (arr[p3] > arr[p4] * 2) {
                res += p4 - M;
                p3--;
            } else {
                p4--;
            }
        }
        while (p1 >= L && p2 > M) {
            // p2 - M == p2 - (M+1) + 1 ==> 右小组中有多少个数比它小
            // 注意这里 arr[p1] == arr[p2]时 一定是右小组先写入merge
            // 因为需要知道右小组中有多少个数比它小 需要让p2指针移动
            mergeArr[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            mergeArr[i--] = arr[p1--];
        }
        while (p2 > M) {
            mergeArr[i--] = arr[p2--];
        }
        for (i = 0; i < mergeArr.length; i++) {
            arr[L + i] = mergeArr[i];
        }
        return res;
    }

    // 复制指针
    public static int mergeZuo(int[] arr, int L, int M, int R) {

        int res = 0;
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = M + 1;
        for (int i = L; i <= M; i++) {
            while (windowR <= R && arr[i] > (arr[windowR] * 2)) {
                windowR++;
            }
            res += windowR - M - 1;
        }

        int[] mergeArr = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            mergeArr[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            mergeArr[i++] = arr[p1++];
        }
        while (p2 <= R) {
            mergeArr[i++] = arr[p2++];
        }
        for (i = 0; i < mergeArr.length; i++) {
            arr[L + i] = mergeArr[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (biggerThan2(arr1) != biggerThan2Zuo(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
