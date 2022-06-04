package 剑指offer;

/**
 * 只出现一次的数字
 *
 * @author zwx
 * @date 2022-06-04 23:18
 */
public class Offer004 {
    public int singleNumber(int[] nums) {
        int[] count = new int[32];
        int res = 0;
        // 先对每一位进行处理
        for (int i = 0; i < 32; i++) {
            for (int x : nums) {
                count[i] += ((x >> i) & 1);
            }
            if ((count[i] % 3) != 0) {
                // 说明第i位上 出现k次那个数的比特不为0(即为1)
                res |= (1 << i);
            }
        }

        return res;
    }


    public int singleNumber2(int[] nums) {
        int[] count = new int[32];
        // 存储位的信息
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += ((x >> i) & 1);
            }
        }

        // 把count数组还原为对应的值
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((count[i] % 3) != 0) {
                // 说明i位上 出现k次那个数的二进制数不为0(即为1)
                res |= (1 << i); //就是将符合条件的位置上填1
            }
        }

        return res;
    }
}
