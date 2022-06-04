package math;

import java.util.Scanner;

/**
 * 试除法判定质数
 *
 * @author zwx
 * @date 2022-06-05 0:40
 */
public class AcWing866 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        while (n-- > 0) {
            int x = read.nextInt();
            if (isPrime(x)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
