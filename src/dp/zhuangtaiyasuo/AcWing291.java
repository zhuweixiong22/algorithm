package dp.zhuangtaiyasuo;

import java.util.Scanner;

/**
 * 291. 蒙德里安的梦想
 * @author novo
 * @date 2022/4/4-15:58
 */
public class AcWing291 {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        while (read.hasNext()) {
            int n = read.nextInt();
            int m = read.nextInt();
            if (n == 0 && m == 0) {
                break;
            }

            boolean[] isValid = new boolean[1 << n];
            long[][] dp = new long[m + 1][1 << n];
            dp[0][0] = 1;

            // 预处理 记录合并列中的非法状态
            // 枚举列中所有的状态 比如一共有四行 就要枚举 0000~1111共16个状态
            for (int i = 0; i < (1 << n); i++) {
                isValid[i] = true;
                int zeroNum = 0; // 记录连续0的个数
                // 枚举每一个状态里面的位
                for (int j = 0; j < n; j++) {
                    // 低位开始枚举
                    if ((i >> j & 1) == 1) { // 如果第j位为1
                        // 判断之前是否存在奇数个连续个零
                        if ((zeroNum & 1) == 1) {
                            // 位运算判断奇偶数
                            isValid[i] = false;
                            break;
                        }
                    } else {
                        zeroNum++;
                    }
                }
                // 因为内循环里遇到1时才会进行0的检查，循环如果以高位为0枚举退出时，会漏了高位为0的检查
                if ((zeroNum & 1) == 1) {
                    isValid[i] = false;
                }
            }

            // 状态转移
            // 枚举列
            for (int i = 1; i <= m; i++) {
                // 枚举当前列中的状态
                for (int j = 0; j < (1 << n); j++) {
                    // 枚举前一列的状态
                    for (int k = 0; k < (1 << n); k++) {
                        // 当前列和前一列不能有重叠，且列状态都合法，才允许转移
                        if ((j & k) == 0 && isValid[j | k]) {
                            dp[i][j] += dp[i - 1][k];
                            //System.out.println("dp[" + i +" "+ j+"]=" + dp[i][j] + " " + "dp[" + (i-1) +" "+ k+"]=" + dp[i - 1][k]);
                        }
                    }
                }
            }
        }
    }
}
