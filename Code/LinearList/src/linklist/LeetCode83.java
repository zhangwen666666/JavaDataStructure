package linklist;

//给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
//链表中节点数目在范围 [0, 300] 内
//-100 <= Node.val <= 100
//题目数据保证链表已经按升序 排列
public class LeetCode83 {
    public static void main(String[] args) {
        ListNode list = ListNode.getList(new int[]{1, 1, 2, 3, 3});
//        list = null;
        ListNode deleteDuplicates = deleteDuplicates(list);
        System.out.println(deleteDuplicates);
    }

    //遍历顺序删除
    public static ListNode deleteDuplicates1(ListNode head) {
        ListNode newHead = new ListNode(200, head);
        ListNode cur = newHead;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return newHead.next;
    }

    public static ListNode deleteDuplicates(ListNode head){
        if(head == null||head.next == null)
            return head;
        if(head.val == head.next.val){
            return deleteDuplicates(head.next);
        }else {
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }
}
