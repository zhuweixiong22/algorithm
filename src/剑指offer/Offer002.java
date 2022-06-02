package 剑指offer;


/**
 * 二进制加法
 *
 * @author zwx
 * @date 2022-06-02 21:33
 */
public class Offer002 {
    public String addBinary(String a, String b) {
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();
        int carry = 0;
        StringBuilder str = new StringBuilder();
        for (int i = charA.length - 1, j = charB.length - 1; i >= 0 || j >= 0; ) {
            int numA = (i >= 0) ? charA[i--] - '0' : 0;
            int numB = (j >= 0) ? charB[j--] - '0' : 0;
            int sum = (numA + numB + carry) % 2;
            carry = (numA + numB + carry) / 2;
            str.insert(0, sum);
        }
        // 检查最后的进位
        if (carry == 1) {
            str.insert(0, 1);
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Offer002 offer002 = new Offer002();
        System.out.println(offer002.addBinary("1", "111"));
        System.out.println(offer002.addBinary("1010", "1011"));
    }
}
