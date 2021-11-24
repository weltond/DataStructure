// https://leetcode.com/problems/reverse-linked-list-ii/

/**
Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 0ms
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;  // use dummy because head might change

        int step = right - left + 1;

        // find the "left" node
        while (head != null && left > 1) {
            prev.next = head;
            prev = head;

            head = head.next;
            left--;
        }

        ListNode newHead = reverse(head, step);

        // connect left's prev to new head
        prev.next = newHead;
        
        return dummy.next;
    }

    // reverse the window
    // return new head
    private ListNode reverse(ListNode cur, int step) {
        ListNode newEnd = cur;

        ListNode prev = null;
        while (cur != null && step > 0) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;

            cur = next;
            step--;
        }
        // cur is right's next
        newEnd.next = cur; // connect newEnd and right's next

        return prev;    // prev is new head
    }
}

// Like No.25 Reverse Nodes in K Group
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) return head;
        
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        dummy.next = head;
        
        int diff = n - m;
        while (m-- > 1) {
            prev = head;
            head = head.next;
        }

        while (diff-- > 0) {
            ListNode t = head.next.next;
            head.next.next = prev.next;
            prev.next = head.next;
            head.next = t;
        }
        
        return dummy.next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) return head;
        
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        pre.next = head;
        
        while (--m > 0) {
            pre = head;
            head = head.next;
            n--;
        }
        
        ListNode last = head, first = pre;
        while (n-- > 0) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        
        last.next = head;
        first.next = pre;
        
        return dummy.next;
        
//         ListNode tmp = head, prev = null;
        
//         while (--m > 0) {
//             prev = tmp;
//             tmp = tmp.next;
//             n--;
//         }
//         //System.out.println(n);
//         ListNode next = null, pre = null, last = tmp;
//         while (n-- > 0) {
//             //System.out.println(n+","+tmp.val);
//             next = tmp.next;
//             tmp.next = pre;
//             pre = tmp;
//             tmp = next;
            
//         }
//         //System.out.println(tmp.val+","+pre.val);
//         //System.out.println(prev.val);
//         last.next = tmp;
//         if (prev == null) return pre;
//         else {
//             prev.next = pre;
//             return head;
//         }
    }
}

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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        
        // Move the two pointers until they reach the proper starting point
        ListNode cur = head, prev = null;
        
        while (m-- > 1) {
            prev = cur;
            cur = cur.next;
            n--;
        }
        
        // Two pointers will fix the final connections
        ListNode con = prev, tail = cur;
        
        while (n-- > 0) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }

        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }
        tail.next = cur;
        
        return head;
    }
}
