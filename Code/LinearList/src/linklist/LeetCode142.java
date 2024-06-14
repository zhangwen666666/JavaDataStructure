package linklist;
// 给定一个链表的头节点  head ，
// 返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
public class LeetCode142 {
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode low = head;
        boolean hasCycle = false;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            low = low.next;
            if(fast == low){
                hasCycle = true;
                break;
            }
        }
        if(hasCycle){
            low = head;
            while(low!=fast){
                low = low.next;
                fast = fast.next;
            }
            return low;
        }
        return null;
    }

}
