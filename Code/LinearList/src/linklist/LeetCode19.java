package linklist;

public class LeetCode19 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        if(head.next == null)
            return null;
        for(int i = 1;i <= n;i++){
            fast = fast.next;
        }
        ListNode low = head;
        while(fast!=null&&fast.next != null){
            fast = fast.next;
            low = low.next;
        }
        low.next = low.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.getList(1);
        list.print();
        removeNthFromEnd(list,1).print();
    }
}
