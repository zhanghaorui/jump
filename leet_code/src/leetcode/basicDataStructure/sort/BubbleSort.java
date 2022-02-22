package leetcode.basicDataStructure.sort;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName BubbleSort.java
 * @Description TODO
 * @createTime 2022年02月22日 09:14:00
 */
public class BubbleSort {

    private void sort(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }

    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 4, 5};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    private void swap(int a, int b) {
        int temp = b;
        b = a;
        a = temp;
    }
}
