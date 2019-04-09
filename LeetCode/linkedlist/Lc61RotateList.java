// https://leetcode.com/problems/rotate-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // ============ Method 2 ===============
    // 0ms
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode tmp = head;
        int length = 0;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        int distance = k % length;
        
        // corner case
        if (distance == 0) return head;
        
        ListNode t1 = head, t2 = head;
        while (distance-- > 0) {
            t1 = t1.next;
        }
        // t2.next is newHead, t1 is the old tail
        while (t1.next != null) {
            t1 = t1.next;
            t2 = t2.next;
        }
        
        ListNode newHead = t2.next;
        t2.next = null;
        t1.next = head;
        
        return newHead;
    }
    
    // ============ Method 1 ===============
    // 1ms (88.74%)
//     public ListNode rotateRight(ListNode head, int k) {
//         if (head == null || head.next == null) return head;
//         ListNode tmp = head;
//         int length = 0;
//         while (tmp != null) {
//             length++;
//             tmp = tmp.next;
//         }
//         while ((k--) % length > 0) {
//             head = change(head);
//         }
        
//         return head;
//     }
    
//     private ListNode change(ListNode head) {
//         ListNode prev = null, cur = head;
//         while (cur.next != null) {
//             prev = cur;
//             cur = cur.next;
//         }
        
//         prev.next = null;
//         cur.next = head;
//         return cur;
//     }
}
