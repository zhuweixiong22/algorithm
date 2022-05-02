package math;

/**
 * @author zwx
 * @date 2022/4/28-23:16
 */
public class LeetCode9 {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        long n = 1;
        while (x / n != 0) {
            n *= 10;
        }
        n /= 10;
        System.out.println(n);
        while (x != 0) {
            int l = (int) (x / n);
            int r = x % 10;
            if (l != r) return false;
            x = (int) ((x % n) / 10);
            n /= 100;
        }
        return true;
    }
}
