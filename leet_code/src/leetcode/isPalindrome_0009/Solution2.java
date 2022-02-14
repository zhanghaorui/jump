package leetcode.isPalindrome_0009;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName Solution2.java
 * @Description TODO
 * @createTime 2022年02月14日 11:01:00
 */
public class Solution2 {

    private boolean isPalindrome(int num) {
        if (num == 0) {
            return true;
        }
        if (num < 0 || num % 100 == 0) {
            return false;
        }
        int reversed = 0;
        while (num > reversed) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return num == reversed || num == reversed / 10;

    }
}
