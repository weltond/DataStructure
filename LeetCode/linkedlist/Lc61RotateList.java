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
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        
        int len = 0;
        ListNode tmp = head;
        while (tmp != null) {
            tmp = tmp.next;
            len++;
        }
        k %= len;
        
        tmp = head;
        
        while (k-- > 0) {
            tmp = tmp.next;
        }
        
        ListNode cur = head;
        //System.out.println(len+","+tmp.val);
        while (tmp.next != null) {
            cur = cur.next;
            tmp = tmp.next;
        }
        
        // [1] 1
        if (cur.next == null) return head;
        
        ListNode newH = cur.next;
        cur.next = null;
        tmp.next = head;
        
        return newH;
        
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode cur = head;
        int len = getLen(head);
        if (len == 0 || k == 0) return head;
        int step = k % len;
        while (step > 0 && cur != null) {
            cur = cur.next;
            step--;
        }
        
        // k is larger than length
        if (cur == null) {
            // not gonna happen
            return null;
        } 
        // k is smaller than length
        else {
            // nothing to change
            if (cur == head) {
                return head;
            }
            // Go to the right k node
            ListNode newHead = head;
            ListNode prev = null;
            while (cur != null) {
                prev = newHead;
                cur = cur.next;
                newHead = newHead.next;
            }
            // disconnect prev to make it as tail
            prev.next = null;
            // go to old tail and connect to old head
            ListNode node = newHead;
            while (node != null && node.next != null) {
                node = node.next;
            }
            node.next = head;

            // return new head
            return newHead;
        }
    }

    private int getLen(ListNode node) {
        int n = 0;
        while (node != null) {
            n++;
            node = node.next;
        }

        return n;

    }
}

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
