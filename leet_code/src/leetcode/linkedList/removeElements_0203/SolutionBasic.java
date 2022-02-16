package leetcode.linkedList.removeElements_0203;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName SoultionBasic.java
 * @Description TODO
 * @createTime 2022年02月16日 10:38:00
 */
public class SolutionBasic {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        if (head.val == val) {
            head = head.next;
        }
        return head;
    }
}
