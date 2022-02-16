package leetcode.realcode.basicDataStructure.linkedList.singleLinkedList;

/**
 * @author 张浩锐
 * @version 1.0.0
 * @ClassName LinkedListReal.java
 * @Description TODO
 * @createTime 2022年02月16日 09:32:00
 */
public class LinkedListReal {

    private Node head = new Node(0, null);

    private Node tail = head;

    /**
     * 遍历
     *
     * @param head
     */
    private void traverse(Node head) {
        Node newNode = head;
        while (newNode.next != null) {
            System.out.println(newNode.value);
            newNode = newNode.next;
        }
    }


    /**
     * 头结点插入
     *
     * @param value
     */
    private void addAtHead(int value) {
        Node newNode = new Node(value, null);
        newNode.next = head;
        head = newNode;
    }

    /**
     * 效率较低的尾结点插入
     *
     * @param value
     */
    private void addAtLastSimple(int value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = newNode;
        }
    }


    /**
     * 通过tail指针 指向尾结点
     *
     * @param value
     */
    private void addAtLastByTail(int value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }


    /**
     * 虚拟头结点
     *
     * @param value
     */
    private void addAtLastByVirtualHead(int value) {
        Node newNode = new Node(value, null);
        tail.next = newNode;
        tail = newNode;
    }


    private void addAtAfter(Node p, int value) {
        if (p == null) {
            return;
        }
        Node newNode = new Node(value,null);
        newNode.next = p.next;
        p.next = newNode;
    }


    /**
     * 查找
     *
     * @param value
     * @return
     */
    private Node find(int value) {
        Node p = head;
        while (p.next != null) {
            if (p.value == value) {
                return p;
            }
            p = p.next;
        }
        return null;
    }


    /**
     * 删除指定结点之后的节点
     *
     * @param p
     */
    private void deleteNextNode(Node p) {
        if (p == null || p.next == null) {
            return;
        }
        p.next = p.next.next;
    }


    /**
     * 删除指定结点
     *
     * @param p
     */
    private void deleteThisNode(Node p) {
        if (head == null || p == null) {
            return;
        }
        Node prev = null;
        Node q = head;
        while (q != null) {
            if (q == p) {
                break;
            }
            prev = q;
            q = q.next;
        }
        if (q == null) {
            return;
        }
        if (prev == null) {
            head = head.next;
        } else {
            prev.next = prev.next.next;
        }
    }


}
