// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

/**
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // 0ms
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        
        ListNode tmp = head;
        while (--n > 0) {
            tmp = tmp.next;
        }
        
        ListNode cur = head, prev = null;
        
        while (tmp.next != null) {
            prev = cur;
            cur = cur.next;
            tmp = tmp.next;
        }
        
        if (prev == null) return head.next;
        
        prev.next = cur.next;
        cur = null;
        
        return head;
    }
}
