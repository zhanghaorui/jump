package leetcode.realcode.reverseString_0344;

/**
 * @author Admin
 * @Comment 双指针？
 */
public class Solution {

    private void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            //构造 a ^ b 的结果，并放在 a 中
            s[l] ^= s[r];
            //将 a ^ b 这一结果再 ^ b ，存入b中，此时 b = a, a = a ^ b
            s[r] ^= s[l];
            //a ^ b 的结果再 ^ a ，存入 a 中，此时 b = a, a = b 完成交换
            s[l] ^= s[r];
            l++;
            r--;
        }
    }

}
