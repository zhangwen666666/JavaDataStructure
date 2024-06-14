package linklist;

import java.util.Random;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * @param num 链表的长度
     * @return 返回一个链表
     */
    public static ListNode getList(int num) {
        if (num <= 0)
            return null;
        Random random = new Random();
        ListNode head = new ListNode(random.nextInt(10),null);
        ListNode tail = head;
        for (int i = 1; i < num; i++) {
            tail.next = new ListNode(random.nextInt(10),null);
            tail = tail.next;
        }
        return head;
    }

    public static void print(ListNode head) {
        if(head == null)
            System.out.print("null");
        while(head!=null){
            System.out.printf("%d ",head.val);
            head = head.next;
        }
        System.out.println();
    }

    public void print(){
        ListNode cur = this;
        while(cur!=null){
            System.out.printf("%d ",cur.val);
            cur = cur.next;
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        ListNode cur = this;
        s.append("[");
        while(cur!=null){
            s.append(cur.val + " ");
            cur = cur.next;
        }
        s.deleteCharAt(s.length()-1);
        s.append("]");
        return s.toString();
    }

    public static ListNode getList(int[] nums) {
        if(nums.length == 0)
            return null;
        ListNode head = new ListNode(nums[0],null);
        ListNode tail = head;
        for(int i = 1;i < nums.length;i++){
             tail.next = new ListNode(nums[i],null);
             tail = tail.next;
        }
        return head;
    }
}
