package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原 IP 地址
 *
 * @author novo
 * @date 2022/2/7-13:46
 */
public class RemoteIP {
    private List<String> res = new ArrayList<>();
    // 需要频繁修改字符串
    StringBuilder IP = new StringBuilder();

    public List<String> restoreIpAddresses(String s) {
        IP.append(s);
        backTracking(IP, 0, 0);
        return res;
    }

    public void backTracking(StringBuilder IP, int index, int pointNum) {
        // 最后一个"."添加完 判断最后一段是否合法
        if (pointNum == 3) {
            if (isValid(IP.substring(index, IP.length()))) {
                res.add(IP.toString());
            }
            return;
        }
        // 属于切割问题 给定字符串上的数字我们是不能修改的，我们只能添加"."，我们并没有用path 所以这个i是不会回退的
        for (int i = index; i < IP.length(); i++) {
            System.out.println("index:" + index + "  i+1:" + (i + 1));
            // 判断选定区间是否合法   substring 左闭右开 [ )
            if (!isValid(IP.substring(index, i + 1))) {
                break;
            }
            // 合法
            IP.insert(i + 1, ".");
            System.out.println(IP);
            backTracking(IP, i + 2, pointNum + 1);
            // 消除痕迹 回溯
            IP.deleteCharAt(i + 1);
        }
    }

    // 校验每一小段的IP是否合法
    public boolean isValid(String str) {
        System.out.println("小段区间：" + str);
        if (str == null || str.length() == 0) {
            return false;
        }
        // 校验是否含前导0
        if (str.charAt(0) == '0' && str.length() > 1) {
            return false;
        }
        // 校验每一位字符是否为0~9
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        // 校验是否属于0~255
        if (Long.parseLong(str) > 255 || Long.parseLong(str) < 0) {
            return false;
        }
        return true;
    }

    // for test
    public void print(List<String> res) {
        for (String str : res) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        new RemoteIP().print(new RemoteIP().restoreIpAddresses("12345678901234567890"));
        //new RemoteIP().print(new RemoteIP().restoreIpAddresses("0000"));
        ///new RemoteIP().print(new RemoteIP().restoreIpAddresses("1111"));
        //new RemoteIP().print(new RemoteIP().restoreIpAddresses("12345678"));
        // new RemoteIP().print(new RemoteIP().restoreIpAddresses("98676554433"));
        System.out.println(Long.MAX_VALUE);
    }
}
