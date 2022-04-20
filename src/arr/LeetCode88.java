package arr;

/**
 * @author novo
 * @date 2022/4/17-22:43
 */
public class LeetCode88 {
    // 传统归并 从前往后合并
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] mergeArr = new int[m];
        int i = 0;
        int p1 = 0;
        int p2 = 0;
        while (p1 < m && p2 < n) {
            mergeArr[i++] = (nums1[p1] <= nums2[p2] ? nums1[p1++] : nums2[p2++]);
        }
        while (p1 < m) {
            mergeArr[i++] = nums1[p1++];
        }
        while (p2 < n) {
            mergeArr[i++] = nums2[p2++];
        }
        // 将合并后的数组复制回nums1
        System.arraycopy(mergeArr, 0, nums1, 0, m);
    }

    // 因为题目给的nums1的特殊性，我们可以从后往前合并 就不用额外的空间 直接在num1上进行合并
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int tail = m + n - 1; // nums1数组的尾指针
        while (p1 >= 0 && p2 >= 0) {
            nums1[tail--] = (nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--]);
        }
        while (p1 >= 0) {
            nums1[tail--] = nums1[p1--];
        }
        while (p2 >= 0) {
            nums1[tail--] = nums2[p2--];
        }
    }
}
