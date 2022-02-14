package leetcode.realcode.invalidateIpAddress_1108;


/**
 * @author Admin
 */
public class Solution2 {

    private String defangIPaddr(String ipAddress) {
        char[] chars = ipAddress.toCharArray();
        char[] newChars = new char[ipAddress.length() + 2 * 3];
        int k = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '.') {
                newChars[k] = chars[i];
                k++;
            } else {
                newChars[k++] = '[';
                newChars[k++] = '.';
                newChars[k++] = ']';
            }
        }
        return new String(newChars);
    }

}
