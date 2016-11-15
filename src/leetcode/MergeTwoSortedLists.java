package leetcode;

/**
 * Created by zhengxiao on 15/11/2016.
 */
public class MergeTwoSortedLists {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode l = null;
        while (true) {
            if (l1 == null && l2 == null) {
                break;
            }

            if (l2 == null || l1 != null && l1.val <= l2.val) {
                // 使用左值
                if (l == null) {
                    l = new ListNode(l1.val);
                } else {
                    l.next = new ListNode(l1.val);
                    l = l.next;
                }
                l1 = l1.next;

            } else { //  if (l1 == null || l2 != null && l2.val < l1.val){
                // 使用右值
                if (l == null) {
                    l = new ListNode(l2.val);
                } else {
                    l.next = new ListNode(l2.val);
                    l = l.next;
                }
                l2 = l2.next;

            }

            if (head == null) {
                head = l;
            }
        }
        return head;
    }

    /**
     * 设计了一个假的头，简化了很多空值判断。并且这个并没有拷贝值
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next; }
        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;
        return dummyHead.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        ListNode l = new ListNode(2);
        l.next = new ListNode(3);
        l.next.next = new ListNode(4);
        ListNode r = new ListNode(1);
        r.next = new ListNode(2);
        ListNode m = new MergeTwoSortedLists().mergeTwoLists2(l, r);
        while (m != null) {
            System.out.println(m.val);
            m = m.next;
        }
    }
}
