package leetcode.linkedList.removeElements_0203;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName SolutionVirtualHead.java
 * @Description TODO
 * @createTime 2022年02月16日 10:43:00
 */
public class SolutionVirtualHead {
    public ListNode removeElements(ListNode head, int val) {
        ListNode listNode = new ListNode();
        listNode.next = head;
        ListNode prev = listNode;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return listNode.next;
    }
}