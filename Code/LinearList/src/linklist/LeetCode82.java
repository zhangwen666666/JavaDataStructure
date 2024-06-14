package linklist;

//给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
//链表中节点数目在范围 [0, 300] 内
//-100 <= Node.val <= 100
//题目数据保证链表已经按升序 排列
public class LeetCode82 {
    //顺序遍历的方法
    public static ListNode deleteDuplicates1(ListNode head) {
        ListNode newHead = new ListNode(200, head);//哨兵节点
        ListNode tail = newHead;//指向当前最后一个保留的节点
        ListNode cur = newHead.next;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                ListNode next = cur.next.next;
                while (next != null && next.val == cur.val) {
                    next = next.next;
                }
                tail.next = next;
                cur = next;
            } else {
                tail = cur;
                cur = cur.next;
            }
        }
        return newHead.next;
    }

    public static ListNode deleteDuplicates(ListNode head){
        if(head==null || head.next==null)
            return head;
        //如果当前节点和下一个节点值相等，则一直向后找，直到为空或找到第一个不相等的节点
        //从这个不相等的节点开始继续递归去重
        if(head.val == head.next.val){
            ListNode next = head.next.next;
            while(next!=null&&next.val == head.val){
                next = next.next;
            }
            return deleteDuplicates(next);
        }

        //走到这里说明，当前节点和下一个节点值不相等
        //保留当前节点，从下一个节点开始去重，并让当前节点的指向去重后的链表
        head.next = deleteDuplicates(head.next);
        return head;
    }

    public static void main(String[] args) {
        //ListNode list = ListNode.getList(new int[]{1, 2, 3, 3, 4, 4, 5});
        ListNode list = ListNode.getList(new int[]{1, 1, 1, 2, 3,4,4,5,7,7});
        ListNode deletedList = deleteDuplicates(list);
        System.out.println(deletedList);
    }
}
