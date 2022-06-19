package exp.force;

import java.util.Scanner;

/**
 * @author zwx
 * @date 2022-05-25 10:50
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int M = 1000;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < M; k++) {
                    if (i + 2 * j + 5 * k == n) {
                        System.out.println(i + "枚1分硬币 + " + j + "枚2分硬币 + " + k + "枚5分硬币");
                    }
                }
            }
        }
    }
}
