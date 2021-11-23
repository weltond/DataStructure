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

class Solution {
    // 1->2->3->4->5, n = 2 ===> 1->2->3->5
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = getLen(head);
        if (len < n || head == null) return head;

        int step = len - n;

        ListNode dummy = new ListNode(0);
        dummy.next = head;  // use dummy head because head might be also removed.  dummy -> 1->2->3->4->5

        ListNode prev = dummy, cur = head;
        while (step > 0) {
            step--;
            prev = cur;
            cur = cur.next;
        }
        // cur is 4. prev is 3

        prev.next = prev.next.next;

        return dummy.next;
    }

    // get total lenth
    public int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        return len;
    }
}
