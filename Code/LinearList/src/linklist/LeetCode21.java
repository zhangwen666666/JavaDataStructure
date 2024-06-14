package linklist;

//将两个升序链表合并为一个新的 升序 链表并返回。
// 新链表是通过拼接给定的两个链表的所有节点组成的
//两个链表的节点数目范围是 [0, 50]
//-100 <= Node.val <= 100
//l1 和 l2 均按 非递减顺序 排列
public class LeetCode21 {
    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode l1 = list1;
        ListNode l2 = list2;
        ListNode newHead = new ListNode(0, null);
        ListNode tail = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = (l1 == null) ? l2 : l1;
        return newHead.next;
    }


    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else {
            if (list1.val < list2.val) {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            } else {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }
    }

    public static void main(String[] args) {
        ListNode list1 = ListNode.getList(new int[]{1, 2, 5, 7, 8});
        System.out.println(list1);
        ListNode list2 = ListNode.getList(new int[]{1, 3, 4});
        ListNode lists = mergeTwoLists(list1, list2);
        System.out.println(lists);
    }
}
