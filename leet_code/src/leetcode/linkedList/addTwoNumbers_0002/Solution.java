package leetcode.linkedList.addTwoNumbers_0002;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName Solution.java
 * @Description TODO
 * @createTime 2022年02月16日 13:44:00
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2, head = new ListNode(), tail = head;
        int carry = 0;
        int sum = 0;
        while (p1.next !=null || p2.next != null || carry != 0) {
            ListNode tep = new ListNode();
            tep.val = (p1.val+ p2.val)%10;
            head.next = tep;
            p1 = p1.next;
            p2 = p2.next;
        }
        return head.next;
    }
}
