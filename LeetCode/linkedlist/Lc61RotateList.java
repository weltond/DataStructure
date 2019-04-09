// https://leetcode.com/problems/rotate-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
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
