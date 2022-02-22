package leetcode.basicDataStructure.sort;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName SelectionSort.java
 * @Description TODO
 * @createTime 2022年02月22日 09:26:00
 */
public class SelectionSort {

    private void sort(int[] nums) {
        for (int i = 0; i < nums.length -1; i++) {
            int minPos = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j]< nums[minPos]) {
                    minPos = j;
                }
            }
            int tmp = nums[i];
            nums[i] = nums[minPos];
            nums[minPos] = tmp;
        }
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 4, 5};
        SelectionSort bubbleSort = new SelectionSort();
        bubbleSort.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

}
