package leetcode.invalidateIpAddress_1108;


/**
 * @author Admin
 * @Comment 简单粗暴 JavaApi
 */
public class Solution1 {

    private String defangIPaddr(String ipAddress) {
        return ipAddress.replace(".", "[.]");
    }

}
