import java.util.Scanner;

/**
 * @author novo
 * @date 2022/3/20-19:48
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            // n 个 农场
            int n = sc.nextInt();
            int num_n = n;
            int k = sc.nextInt();
            // 有几个洒水器
            boolean[] visited = new boolean[n + 1];
            int[] arr = new int[k + 1];
            for (int j = 1; j <= k; j++) {
                visited[j] = true;
                arr[j] = sc.nextInt();
            }
            int res = 1;
            while (n != 0) {
               for (int m = 1; m < arr.length; m++) {
                   int index = arr[m];
                   if ((index - 1) > 0 && visited[i - 1]) {
                       visited[i - 1] = true;
                       n--;
                   }
                   if ((index + 1) <= num_n && visited[i + 1]) {
                       visited[i + 1] = true;
                       n--;
                   }
               }
               res++;
            }
            System.out.println(res);
        }
    }
}
