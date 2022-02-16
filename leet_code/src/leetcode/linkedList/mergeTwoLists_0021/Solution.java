package leetcode.linkedList.mergeTwoLists_0021;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName Solution.java
 * @Description TODO
 * @createTime 2022年02月16日 13:30:00
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode head = new ListNode();
        ListNode tail = head;
        while(p1 != null && p2 != null) {
            if(p1.val <= p2.val) {
                tail.next = p1;
                tail = p1;
                p1 = p1.next;
            } else {
                tail.next = p2;
                tail = p2;
                p2 = p2.next;
            }
        }
        if(p1 != null) {tail.next = p1;}
        if(p2 != null) {tail.next = p2;}
        return head.next;
    }
}
