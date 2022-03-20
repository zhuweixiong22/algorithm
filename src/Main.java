import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author novo
 * @date 2022/3/20-19:24
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int res = 0;
        int num = sc.nextInt();
        Map<Character, Integer> map = new HashMap<>();
        map.put('0', 1);
        map.put('4', 1);
        map.put('6', 1);
        map.put('9', 1);
        map.put('A', 1);
        map.put('D', 1);
        map.put('8', 2);
        map.put('B', 2);
        map.put('1', 0);
        map.put('2', 0);
        map.put('3', 0);
        map.put('5', 0);
        map.put('7', 0);
        map.put('C', 0);
        map.put('E', 0);
        map.put('F', 0);
        char[] nums = intTohex(num).toCharArray();
        for (int i = 0; i < nums.length; i++) {
            res += map.get(nums[i]);
        }
        System.out.println(res);
    }
    private static String intTohex(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            s = s.append(b[n % 16]);
            n = n / 16;
        }
        a = s.reverse().toString();
        return a;
    }
}
