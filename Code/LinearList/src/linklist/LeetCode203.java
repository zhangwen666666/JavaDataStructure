package linklist;

/**
 * 给你一个链表的头节点 head 和一个整数 val ，
 * 请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */
public class LeetCode203 {
    //顺序遍历
    public static ListNode removeElements1(ListNode head, int val) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            if (cur.val == val) {
                if (prev == null) {
                    head = cur.next;
                } else {
                    prev.next = cur.next;
                }
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    //递归方法
    public static ListNode removeElements2(ListNode head, int val){
        if(head == null)
            return null;
        head.next = removeElements2(head.next,val);
        return head.val == val? head.next:head;
    }

    public static void main(String[] args) {
        ListNode list1 = ListNode.getList(10);
        ListNode.print(list1);
        ListNode.print(removeElements1(list1,7));

        list1 = ListNode.getList(10);
        ListNode.print(list1);
        ListNode.print(removeElements2(list1,1));
    }
}
