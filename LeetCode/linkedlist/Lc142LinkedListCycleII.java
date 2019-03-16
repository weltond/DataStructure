// https://leetcode.com/problems/linked-list-cycle-ii/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next;
            
            if (fast != null)
                fast = fast.next;
            
            if (slow == fast) break;
        }
        
        if (fast == null || fast.next == null) return null;  // no cycle
        
        // otherwise, detect the cycle node.
        // reset slow to head and fast to where it last stoped
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
        
    }
}
