package leetcode.isPalindrome_0125;

import java.util.Locale;

/**
 * @author Admin
 */
public class Solution {

    private boolean isPalindrome(String s) {
        s = s.toLowerCase(Locale.ROOT);
        int n = s.length();
        if (n == 0 || s == null) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder(s.length());
        int i = 0;
        for (char c : s.toCharArray()) {
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString().equals(stringBuilder.reverse().toString());
    }


}
