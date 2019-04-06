// https://leetcode.com/problems/insertion-sort-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // ============= Method 2: Recursion (Like Merge Sort) =============
    // 1ms (100%)
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        // find middle
        ListNode slow = head, fast = head, tmp = null;
        
        while (fast != null && fast.next != null) {
            tmp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        tmp.next = null;
        // new head 1 is head; new head 2 is slow
        ListNode l1 = insertionSortList(head);
        ListNode l2 = insertionSortList(slow);
        
        return merge(l1, l2);
        
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
        
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
    // ============= Method 1: Iteration ===============
    // 30ms (49.72%)
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        ListNode next = cur.next;
        ListNode prev = dummy;
        
        while (cur != null) {
            next = cur.next;
            while (prev.next != null && prev.next.val < cur.val) {
                prev = prev.next;   // find the right pos to insert
            }
            
            cur.next = prev.next;   // insert cur node between prev and prev.next
            prev.next = cur;
            
            prev = dummy;   // reset prev
            cur = next;
        }
        
        return dummy.next;
    }
}
