package day11;

import com.sun.xml.internal.txw2.output.DumpSerializer;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 * @author novo
 * @date 2022/2/7-17:00
 */
public class Permutations1 {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private boolean[] used ;
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        used = new boolean[nums.length];
        backTracking(nums, 0);
        return res;
    }

    public void backTracking(int[] nums, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if(!used[i]) {
                used[i] = true;
                path.add(nums[i]);
                backTracking(nums,i + 1);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
