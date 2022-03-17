package greedy;

import java.util.Scanner;

/**
 * @author novo
 * @date 2022/3/17-16:42
 */
public class Acwing3358 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] songArr = sc.nextLine().toCharArray();
        char[] listerArr = sc.nextLine().toCharArray();

        // 记录song中所有字符的位置
        int[] index = new int[26];
        for (int i = 0; i < 26; i++) {
            index[songArr[i] - 'a'] = i;
        }
        int res = 1;
        for (int i = 1; i < listerArr.length; i++) {
            // 如果短串邻接的两个字符中出现逆序或相等，这两个字符必然不能出现在同一次中
            if ((index[listerArr[i]] - 'a') <= (index[listerArr[i - 1] - 'a'])) {
                res++;
            }
        }
        System.out.println(res);
    }
}
