package leetcode.linkedList.middleNode_0876;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName SolutionDoublePointer.java
 * @Description TODO
 * @createTime 2022年02月16日 11:25:00
 */
public class SolutionDoublePointer {

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
