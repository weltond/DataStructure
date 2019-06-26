// https://leetcode.com/problems/partition-list/

/**
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
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
    // Approach 2: 0ms
    public ListNode partition(ListNode head, int x) {
        ListNode minH = new ListNode(0);
        ListNode min = minH;    // dummy min
        
        ListNode maxH = new ListNode(0);
        ListNode max = maxH;    // dummy max
        
        while (head != null) {
            if (head.val < x) {
                min.next = head;
                min = min.next;
            } else {
                max.next = head;
                max = max.next;
            }
            
            head = head.next;
        }
        
        max.next = null;    // end of the reformed list
        
        min.next = maxH.next;
        
        return minH.next;
    }
    
    // Approach 1: 0ms 
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(x - 1);
        dummy.next = head;
        ListNode cur = head, last = dummy, prev = dummy;
        
        while (cur != null) {
            if (cur.val >= x) {
                prev = cur;
                cur = cur.next;
            } else {
                if (prev == last) {
                    prev = cur;
                    last = cur;
                    cur = cur.next;
                } else {
                    prev.next = cur.next;
                    
                    cur.next = last.next;
                    last.next = cur;
                    last = cur;
                    
                    cur = prev.next;    
                }
            }
        }
        
        return dummy.next;
    }
}
