package DataStructure.BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zwx
 * @date 2022/4/28-17:18
 */
public class Test {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        /*int n = 1000;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(100000));
        }
        List<Integer> list = new ArrayList<>();
        while (!bst.isEmpty()) {
            list.add(bst.removeMax());
        }

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(i - 1)) {
                System.out.println("fail");
                break;
            }
        }
        System.out.println("success");*/

        int[] nums = new int[]{7,2,3,5,9};
        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }
        System.out.println(bst.size());
        bst.remove(3);
        System.out.println(bst.size());
        System.out.println(bst.floor(5));
        System.out.println(bst.ceil(5));
        System.out.println(bst);
    }
}
