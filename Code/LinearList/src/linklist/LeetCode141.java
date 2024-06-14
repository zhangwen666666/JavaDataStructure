package linklist;
//给你一个链表的头节点 head ，判断链表中是否有环。
public class LeetCode141 {
    public static boolean hasCycle(ListNode head){
        ListNode fast = head;
        ListNode low = head;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            low = low.next;
            if(fast == low){
                return true;
            }
        }
        return false;
    }
}
