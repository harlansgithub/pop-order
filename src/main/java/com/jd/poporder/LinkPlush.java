package com.jd.poporder;

/**
 * 链表两数相加
 * 时间复杂度O(n)
 * 空间复杂度O(n)
 */
public class LinkPlush {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 进位值
        int forwardNum = 0;
        // 存储结果的链表
        ListNode resultListNode = new ListNode();
        // 设置一个头结点，用于记录链表的头部，用于打印链表
        ListNode headNode = resultListNode;
        // 设置临界值，防止无限循环
        while(l1 != null || l2 != null){
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + forwardNum;
            resultListNode.val = sum % 10;
            forwardNum = sum / 10;// 求进位


            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

            if (l1 != null || l2 != null){
                resultListNode.next = new ListNode();
                resultListNode = resultListNode.next;
            }
        }
        return headNode;
    }

    public static void main(String[] args) {
        // 2 -> 4 -> 3
        ListNode l1_1 = new ListNode(3,null);
        ListNode l1_2 = new ListNode(4,l1_1);
        ListNode l1_3 = new ListNode(2,l1_2);

        // 5 -> 6 -> 4
        ListNode l2_1 = new ListNode(4,null);
        ListNode l2_2 = new ListNode(6,l2_1);
        ListNode l2_3 = new ListNode(5,l2_2);

        LinkPlush linkPlush = new LinkPlush();
        ListNode result = linkPlush.addTwoNumbers(l1_3, l2_3);

        // 打印结果
        while (true){
            System.out.println(result.val);
            if (result.next == null){
                break;
            }else {
                result = result.next;
            }
        }
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
