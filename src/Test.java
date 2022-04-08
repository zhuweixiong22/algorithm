/**
 * @author novo
 * @date 2022/4/7-22:29
 */
public class Test {
    public static void main(String[] args) {
        int res = 0;
        for (int i = 1; i <= 2020; i++) {
            int temp = i;
            while(temp != 0) {
                int one = temp % 10;
                if (one == 2) {
                    res ++;
                    break;
                }
                temp /= 10;
            }
        }
        System.out.println(res);
    }
}
