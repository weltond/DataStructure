// https://leetcode.com/problems/add-two-numbers/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.
    */
    // 2ms (99.85%)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
        
        int factor = 0;
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while (l1 != null || l2 != null) {
            int a1 = l1 == null ? 0 : l1.val;
            int a2 = l2 == null ? 0 : l2.val;
            
            int tmp = a1 + a2 + factor;
            
            // if (tmp >= 10) factor = 1;
            // else factor = 0;
            factor = tmp / 10;  // val could be greater than 10 on each node

            cur.next = new ListNode(tmp % 10);

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            cur = cur.next;
        }
        
        if (factor == 1) cur.next = new ListNode(1);
        
        return dummy.next;
    }
}
