package day11;

import java.util.function.DoubleToIntFunction;

/**
 * 汉诺塔
 * 移动步数一共需要 2^n - 1
 * @author novo
 * @date 2022/2/5-16:06
 */
public class Hanoi {
    /**
     * 从最左到最右只需要三步：
     * 1.上面 N - 1 的挪去辅助柱
     * 2.剩余的一个挪去最右
     * 3.辅助柱的挪去最右
     */
    public static void process(int N, String source, String target, String help) {
        if (N == 1) {
            System.out.println("move 1 from " + source + " to " + target);
        } else {
            // 1.上面 N - 1 的挪去辅助柱
            process(N - 1, source, help, target);
            // 2.剩余的一个挪去最右 打印的是 剩余的一个挪去最右
            System.out.println("move " + N + " from " + source + " to " + target);
            // 3.辅助柱的挪去最右
            process(N - 1, help, target, source);
        }

    }

    public static void hanoi(int N, String source, String target, String help) {
        process(N, source, target, help);
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(l);
        hanoi(3, "A", "B", "C");
        System.out.print("耗时");
        System.out.print(System.currentTimeMillis()-l);
        System.out.print("ms");
    }
}
