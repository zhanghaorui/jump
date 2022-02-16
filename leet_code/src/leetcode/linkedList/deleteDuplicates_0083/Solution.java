package leetcode.linkedList.deleteDuplicates_0083;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName Solution.java
 * @Description TODO
 * @createTime 2022年02月16日 13:34:00
 */
public class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = head;
        while (p.next != null) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }
}
