package real.alibaba;

/**
 * @author Admin
 */
public class DrinkPeer {

    /**
     *  哈啤酒
     *  给定x瓶啤酒  每三个瓶子可兑换一瓶 每七个瓶盖可以兑换一瓶 问给x能喝几瓶
     * @param x
     * @return
     */
    private int solution(int x) {
        int count = x;
        int k1 = x;
        int k2 = x;
        while (k1 >= 3 || k2 >= 7) {
            while (k1 >= 3) {
                int change = k1 / 3;
                count += change;
                k1 %= 3;
                k1 += change;
                k2 += change;
            }
            while (k2 >= 7) {
                int change = k2 / 7;
                count += change;
                k2 %= 7;
                k1 += change;
                k2 += change;
            }
        }
        return count;
    }

}
