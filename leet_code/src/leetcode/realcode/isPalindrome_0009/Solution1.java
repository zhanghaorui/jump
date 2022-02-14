package leetcode.realcode.isPalindrome_0009;


/**
 * @author Admin
 */
public class Solution1 {

    private boolean isPalindrome(int num) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(num));
        return stringBuilder.toString().equals(stringBuilder.reverse().toString());
    }

}
