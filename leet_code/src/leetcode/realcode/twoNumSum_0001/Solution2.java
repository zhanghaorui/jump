package leetcode.realcode.twoNumSum_0001;

import java.util.HashMap;

/**
 * @author Admin
 */
public class Solution2 {

    private int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target-nums[i];
            if (hashMap.containsKey(temp)) {
                result[1] = i;
                result[0] = hashMap.get(temp);
            }
            hashMap.put(nums[i],i);
        }
        return result;
    }
}
