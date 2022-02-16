package leetcode.realcode.removeDuplicates_0026;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName Solution.java
 * @Description TODO
 * @createTime 2022年02月14日 16:50:00
 */
public class Solution {
    private int removeDuplicates(int[] nums) {
        int n = nums.length;
        int k = 0;//[0,k]栈中元素
        for (int i = 1; i < n; ++i) {
            if (nums[i] != nums[k]) {//放⼊栈
                k++;
            }
        }
        return k + 1;
    }
}
