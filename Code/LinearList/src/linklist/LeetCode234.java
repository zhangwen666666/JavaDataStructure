package linklist;

public class LeetCode234 {
    public static void main(String[] args) {
        ListNode list = ListNode.getList(new int[]{1,0,0});
        list.print();
        boolean palindrome = isPalindrome(list);
        System.out.println(palindrome);
    }

    public static boolean isPalindrome(ListNode head){
        if(head.next==null)
            return true;
        ListNode fast = head;
        ListNode low = head;
        ListNode prev = null;
        ListNode next = low.next;
        while(fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            next = low.next;
            low.next = prev;
            prev = low;
            low = next;
        }


        low = prev;
        boolean result = true;
        while(next!=null){
            if(next.val!=low.val){
                result = false;
                break;
            }
            next = next.next;
            low = low.next;
        }
        return result;
    }
}
