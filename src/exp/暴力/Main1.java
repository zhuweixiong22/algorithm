package exp.force;
import java.util.Scanner;

/**
 * @author zwx
 * @date 2022-05-25 11:25
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        //int n = read.nextInt();
        //assert n >= 0 : "请输入非负数";
        for (int k = 1; k <= 20; k++) {
            for (int i = 1; ; i++) {
                if (i * i > k) {
                    System.out.println("⌊√" + k + "⌋ = " + (i - 1));
                    break;
                }
            }
        }

    }
}
