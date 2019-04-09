// https://leetcode.com/problems/odd-even-linked-list/

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
