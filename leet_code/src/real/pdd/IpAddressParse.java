package real.pdd;

import java.util.Objects;

/**
 * @author Admin
 */
public class IpAddressParse {

    /**
     * ip地址段
     */
    private static final Integer IP_PART = 4;

    /**
     * ip分隔符
     */
    private static final String IDOL = ".";


    /**
     *  char 空格
     */
    private static final char SPACE = ' ';


    /**
     *  char 0
     */
    private static final char ZERO = '0';

    /**
     *  char 9
     */
    private static final char NINE = '9';

    /**
     *  int 255
     */
    private static final Integer TWO_FIVE_FIVE = 255;

    /**
     * 判断ip地址是否合法
     *
     * @param ipAddress
     * @return
     */
    private boolean isIllegal(String ipAddress) {
        // step1 空串 null  分隔符
        if (!baseCheck(ipAddress)) {
            return false;
        }
        return checkSegment(ipAddress);
    }


    /**
     * 基本判断  空串 或者拆分字符串不合理
     *
     * @param ipAddress
     * @return
     */
    private boolean baseCheck(String ipAddress) {
        if (Objects.isNull(ipAddress)) {
            return false;
        }
        if (ipAddress.split(IDOL).length != IP_PART) {
            return false;
        }
        return true;
    }


    /**
     * 校验每一段字符串
     *
     * @param ipAddress
     * @return
     */
    private boolean checkSegment(String ipAddress) {
        int n = ipAddress.length();
        int i = 0;
        while (i < n && ipAddress.charAt(i) == SPACE) {
            i++;
        }
        // 全空格
        if (i == n) {
            return false;
        }
        int digit = 0;
        while (i < n && ipAddress.charAt(i) != SPACE) {
            char c = ipAddress.charAt(i);
            // 含字母
            if (ZERO > c || NINE < c) {
                return false;
            }
            digit = digit * 10 + (c - ZERO);
            // ip超255
            if (digit > TWO_FIVE_FIVE) {
                return false;
            }
            i++;
        }
        while (i < n) {
            char c = ipAddress.charAt(i);
            if (c != SPACE) {
                return false;
            }
            i++;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println();
    }
}
