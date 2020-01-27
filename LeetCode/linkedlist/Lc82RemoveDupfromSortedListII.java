// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
Input: 1->2->3->3->4->4->5
Output: 1->2->5
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        
        while (head != null) {
            ListNode tmp = head;
            int val = tmp.val;
            int cnt = 0;
            while (tmp.next != null && tmp.next.val == val) {
                cnt++;
                tmp = tmp.next;
            }
            
            if (cnt == 0) {
                //System.out.println(head.val+","+cnt);
                pre.next = head;
                pre = head;
            }
            
            tmp = tmp.next;
            head.next = null;   //[1,2,2]
            head = tmp;
        }
        
        return dummy.next;
    }
}

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        dummy.next = head;
        
        ListNode cur = head;
        int value = head.val;
        
        while (cur != null) {
            int cnt = 0;
            ListNode tmp = cur;
            while (cur != null && cur.val == value) {
                cnt++;
                cur = cur.next;
            }
            
            if (cnt > 1) {
                prev.next = cur;
            } else {
                prev = tmp;
            }
            
            if (cur != null)
                value = cur.val;
        }
        
        return dummy.next;
    }
}

class Solution {
    // ================ Method 2: Recursion =================
    // 1ms (36.98%)
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = helper(head);
        
        return dummy.next;
    }
    
    private ListNode helper(ListNode head) {
        if (head == null || head.next == null) return head;

        if (head.val != head.next.val) {
            head.next = helper(head.next);
            return head;
        }
        
        int dupVal = head.val;
        // this is garunteed that there would be duplicate values
        while (head != null && head.val == dupVal) {
            head = head.next;
        }
        
        return helper(head);
    }
    
    // ================ Method 1: Iteration =================
    // 1ms (36.98%)
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        ListNode next = null;   // a helper node to determine if exists duplicate
        while (head != null) {
            // if found duplicate, simply move head node.
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
                next = head;
            }
            // if helper node is null which means no duplicate. 
            if (next == null) {
                cur.next = head;
                head = head.next;
                cur = cur.next;
            } else {
            // simply move head node, reset helper node, and !!set cur.next to null!!
                head = head.next;
                next = null;    // reset helper node
                cur.next = null;
            }
        }
        
        return dummy.next;
        
    }
}
