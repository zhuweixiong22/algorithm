package exp.backtacking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 求解组合问题
 * 输出自然数1~n中任取r个数的组合
 * @author zwx
 * @date 2022-06-08 10:47
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        int r = read.nextInt();
        backTracking(n, 1, r);
        System.out.println(res);
    }
    private static List<List<Integer>> res = new ArrayList<>();
    private static List<Integer> path = new ArrayList<>();
    private static void backTracking(int n, int index, int r) {
        if (path.size() == r) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i <= n; i++) {
            path.add(i);
            backTracking(n, i + 1, r);
            path.remove(path.size() - 1);
        }
    }
}
