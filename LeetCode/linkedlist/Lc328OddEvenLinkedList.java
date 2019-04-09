// https://leetcode.com/problems/odd-even-linked-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
*/
class Solution {
    // 0ms
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode odd = head, even = head.next;
        ListNode evenHead = even;
        
        while (odd != null && even != null) {
            odd.next = odd.next == null ? null : odd.next.next;
            even.next = even.next == null ? null : even.next.next;
            
            odd = odd.next == null ? odd : odd.next;    // keep the last odd node reference
            even = even.next;
        }
        
        odd.next = evenHead;
        
        return head;

    }
}
