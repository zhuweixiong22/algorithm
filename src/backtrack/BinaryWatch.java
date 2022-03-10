package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入0 输出["0:00"]
 *
 * @author novo
 * @date 2022/2/9-15:29
 */
public class BinaryWatch {
    private String[] time = {
            /*"1:00", // 0
            "2:00", // 1
            "4:00", // 2
            "8:00", // 3
            "0:01", // 4
            "0:02",
            "0:04",
            "0:08",
            "0:16",
            "0:32"*/
    };
    // 比较巧妙的是这两个hours和minutes数组，相当于将所有灯的选择都合并在一起，
    // for横向遍历的时候都选择两个数组的元素，实际上只选择了一个，因为另一个是0
    int[] hours = new int[]{1, 2, 4, 8, 0, 0, 0, 0, 0, 0};
    int[] minutes = new int[]{0, 0, 0, 0, 1, 2, 4, 8, 16, 32};
    List<String> res = new ArrayList<>();

    public List<String> readBinaryWatch(int turnedOn) {
        backTracking(turnedOn, 0, 0, 0);
        return res;
    }

    public void backTracking(int turnedOn, int index, int hour, int minute) {
        //4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
        if (hour > 11 || minute > 59) {
            return;
        }
        if (turnedOn == 0) {
            StringBuilder path = new StringBuilder();
            path.append(hour).append(":");
            if (minute < 10) {
                path.append("0");
            }
            path.append(minute);
            res.add(path.toString());
            return;
        }
        for (int i = index; i < 9; i++) {
            backTracking(turnedOn - 1, i + 1, hour + hours[i], minute + minutes[i]);
            // 回溯体现在hour + hours[i]、minute + minutes[i]这个操作只是做了参数传递，退出来后hour已经还原了
        }
    }

    public static void main(String[] args) {
        List<String> lists = new BinaryWatch().readBinaryWatch(3);
        System.out.println(lists);
    }
}
