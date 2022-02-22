package leetcode.basicDataStructure.sort;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName InsertionSort.java
 * @Description TODO
 * @createTime 2022年02月22日 10:15:00
 */
public class InsertionSort {

    private void sort(int[] nums) {
        // 假设[0,i)已排序
        for (int i = 1; i < nums.length; ++i) {
            int value = nums[i];
            int j = i -1;
            for (; j >= 0 ; --j) {
                if (nums[j] > value) {
                    nums[j+1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j+1] = value;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 4, 5};
        InsertionSort bubbleSort = new InsertionSort();
        bubbleSort.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

}
