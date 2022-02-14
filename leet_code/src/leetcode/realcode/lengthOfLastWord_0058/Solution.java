package leetcode.realcode.lengthOfLastWord_0058;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName Solution.java
 * @Description TODO
 * @createTime 2022年02月14日 16:06:00
 */
public class Solution {

    private int lengthOfLastWord(String s) {
        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                length++;
            } else if (length != 0) {
                return length;
            }
        }
        return length;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLastWord("   fly me   to   the moon  "));
    }
}
