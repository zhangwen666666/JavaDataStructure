package linklist;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
 * 例如：
 * 1 --> 2 --> 3 --> 4 --> 5  反转后  5 --> 4 --> 3 --> 2 --> 1
 * null 反转后 null
 */
public class Leetcode206 {

    public static ListNode reverseList1(ListNode head) {
        if(head==null)
            return null;
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode = head.next;
        while(nextNode!=null){
            preNode = curNode;
            curNode = nextNode;
            nextNode = nextNode.next;
            curNode.next = preNode;
        }
        head.next = null;
        return curNode;
    }

    public static ListNode reverseList2(ListNode head) {
        ListNode newHead = null;
        ListNode cur = head;
        while(head!=null){
            head = head.next;
            cur.next = newHead;
            newHead = cur;
            cur = head;
        }
        return newHead;
    }

    public static ListNode reverseList3(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode newHead = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static ListNode reverseList4(ListNode head){
        if(head == null)
            return null;
        ListNode newHead = new ListNode(head.val,null);
        while(head.next!=null){
            newHead= new ListNode(head.next.val,newHead);
            head = head.next;
        }
        return newHead;
    }
    public static ListNode reverseList5(ListNode head){
        ListNode cur = head;
        ListNode prev = null;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5,null);
        head.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next =listNode3;
        listNode3.next = listNode4;
        print(head);
        print(reverseList4(head));

        ListNode emptyList = null;
        print(emptyList);
        print(reverseList4(emptyList));
    }

    private static void print(ListNode head) {
        if(head == null)
            System.out.print("null");
        while(head!=null){
            System.out.printf("%d ",head.val);
            head = head.next;
        }
        System.out.println();
    }
}
