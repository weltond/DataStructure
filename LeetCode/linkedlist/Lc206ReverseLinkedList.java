// https://leetcode.com/problems/reverse-linked-list/solution/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// ===== Recursion 2 ======
// Recursion func return each node that needed to be reversed
class Solution {
    private ListNode newHead;

    public ListNode reverseList(ListNode head) {
        recursion(head);

        return newHead;
    }

    private ListNode recursion(ListNode head) {
        if (head == null || head.next == null) {
            newHead = head;
            return head;
        }
        
        ListNode prev = recursion(head.next);  
        
        prev.next = head;
        head.next = null;
        return head;
    }
}

class Solution {
    // ====== Method 2: Iterative =======
    // 0ms
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        
        return prev;
    }
    
    // ====== Method 1: recursion =======
    // 0ms
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode ret = reverseList(head.next);
        
        head.next.next = head;
        
        head.next = null;
        
        return ret;
    }
}
