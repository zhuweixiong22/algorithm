package backtrack;

import java.util.*;

/**
 * 306. 累加数
 *
 * @author zwx
 * @date 2022-05-06 22:08
 */
public class LeetCode306 {
    /*// 当栈
    private Deque<Long> path = new LinkedList<>();
    //private List<List<Integer>> res = new ArrayList<>();

    private boolean flag = false;
    public boolean isAdditiveNumber(String num) {
        dfs(num, 0);
        return flag;
    }

    private void dfs(String num, int index) {
        if (index == num.length()) {
            if (path.size() >= 3) {
                flag = true;
            }
            //res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 1; index + i <= num.length();i++) {
            // 面向测试用例编程 防止溢出 num长度最多为35
            if (i > 11 && path.size() <= 2) {
                break;
            }
            // 数字不能含有前导0 0可以单独使用
            if (num.charAt(index) == '0' && i > 1) {
                break;
            }
            long n = Long.parseLong(num.substring(index, index + i));
            // 剪枝 弹出栈顶两个元素，n 是否为这两个元素和，否则跳过
            if (path.size() >= 2) {
                long a1 = path.pop();
                long a2 = path.pop();
                if (n != a1 + a2) {
                    path.push(a2);
                    path.push(a1);
                    if (n > a1 + a2) {
                        break;
                    }
                    continue;
                } else {
                    path.push(a2);
                    path.push(a1);
                }
            }
            path.push(n);
            dfs(num, index + i);
            path.pop();
        }
    }*/

    private Deque<List<Integer>> path = new LinkedList<>();
    private boolean flag = false;

    public boolean isAdditiveNumber(String num) {
        dfs(num, 0);
        return flag;
    }

    private void dfs(String num, int index) {
        if (index == num.length()) {
            if (path.size() >= 3) {
                flag = true;
            }
            return;
        }
        for (int i = 1; index + i <= num.length(); i++) {
            // 数字不能含有前导0 0可以单独使用
            if (num.charAt(index) == '0' && i > 1) {
                break;
            }
            List<Integer> n = getNum(num, index, index + i);
            // 剪枝 弹出栈顶两个元素，n 是否为这两个元素和，否则跳过
            if (path.size() >= 2) {
                List<Integer> a1 = path.pop();
                List<Integer> a2 = path.pop();
                List<Integer> add = add(a1, a2);
                if (!isEqual(n, add)) { // 这里判断可以细分到 n > a1 + a1的话可以break
                    path.push(a2);
                    path.push(a1);
                    continue;
                } else {
                    path.push(a2);
                    path.push(a1);
                }
            }
            path.push(n);
            dfs(num, index + i);
            path.pop();
        }
    }


    // 高精度substring
    private List<Integer> getNum(String num, int l, int r) {
        List<Integer> list = new ArrayList<>();
        for (int i = r - 1; i >= l; i--) {
            list.add(num.charAt(i) - '0');
        }
        return list;
    }

    // 高精度加法
    private List<Integer> add(List<Integer> a, List<Integer> b) {
        List<Integer> c = new ArrayList<>();
        int carry = 0;
        for (int i = 0; i < a.size() || i < b.size(); i++) {
            if (i < a.size()) carry += a.get(i);
            if (i < b.size()) carry += b.get(i);
            c.add(carry % 10);
            carry /= 10;
        }
        if (carry > 0) c.add(carry);

        return c;
    }

    // 判断两个数相等
    private boolean isEqual(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (!Objects.equals(a.get(i), b.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode306 leetCode306 = new LeetCode306();
        /*boolean fff = leetCode306.isAdditiveNumber("199100199");
        boolean additiveNumber = leetCode306.isAdditiveNumber("11235813213455890144");
        System.out.println(fff);*/
        leetCode306.getNum("199", 0, 3);
        System.out.println(leetCode306.isAdditiveNumber("321312"));

    }
}
