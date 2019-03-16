// https://leetcode.com/problems/palindrome-linked-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // ======== Method 2: Reverse while traverse========
    // 0ms
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        
        ListNode slow = head, fast = head, prev = null, tmp = null;
        // reverse the first half while traversing
        while (fast != null && fast.next != null) {
            // fast move 2 steps. MOVE FAST POINTER FIRST!
            fast = fast.next.next;
            
            // reverse and move 1 step
            tmp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = tmp;   
        }
        
        // after the following loop, slow will be the new head of first half
        // 1->2->3->2->1 => 1<-2 3->2->1 : slow at the '3'; fast at the last '1'
        // or 
        // 1->2->2->1 => 1<-2 2->1 : slow at the second '2'; fast at null
        
        // if fast not null, which means the length of linkedlist is odd. We can ignore the middle number (i.e. '3')
        if (fast != null) slow = slow.next;
        
        while (slow != null) {
            if (prev.val != slow.val) return false;
            
            prev = prev.next;
            slow = slow.next;
        }
        
        return true;
    }
    
    // ======== Method 1: Cut in half ========
    // Not Efficient: 1ms
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        
        //1. find medium
        ListNode tmp = head;
        ListNode middle = findMiddle(tmp);
        
        //2. cut off the linked list into 2 half
        ListNode secondHalf = middle.next;
        middle.next = null;
        
        //3. reverse the second half
        tmp = secondHalf;
        ListNode newHead = reverse(tmp);
        
        while (head != null && newHead != null) {
            if (head.val != newHead.val) return false;
            
            head = head.next;
            newHead = newHead.next;
        }
        
        // here, we can just ignore if linkedlist is like this 1->2->3->2->1
        // because 3 is also palindrome of himself
        
        return true;
    }
    
    // 1->2->3->2->1 will be splitted into: 1->2->3 & 2->1
    // 1->2->2->1 will be splitted into:    1->2 & 2->1
    private ListNode findMiddle(ListNode head) {
        ListNode fast = head, slow = head;
        
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode ret = reverse(head.next);
        
        head.next.next = head;
        
        head.next = null;
        
        return ret;
    }
}
