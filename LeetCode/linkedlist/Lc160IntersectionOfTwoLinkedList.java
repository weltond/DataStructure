// https://leetcode.com/problems/intersection-of-two-linked-lists/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode h1 = headA, h2 = headB;

        while (h1 != null || h2 != null) {
            if (h1 == h2) return h1;
            h1 = h1 == null ? headB : h1.next;
            h2 = h2 == null ? headA : h2.next;
        }

        return null;
    }
}

public class Solution {
    
    // ===== Method 2: Two Pointers ======
    // 1ms
    // Time = O(m+n) Space = O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode node = null;
        ListNode tmpA = headA, tmpB = headB;
        while (tmpA != tmpB) {
            /** NOT ELEGANT!!!!
            if (tmpA == null && tmpB == null) return null;
            if (tmpA == null) tmpA = headB;
            if (tmpB == null) tmpB = headA;
            if (tmpA == tmpB && tmpA != null) return tmpA;
            
            tmpA = tmpA.next;
            tmpB = tmpB.next;
            */
            
            tmpA = tmpA == null ? headB : tmpA.next;
            tmpB = tmpB == null ? headA : tmpB.next;
        }
        
        return tmpA;
    }
    
    
    // ===== Method 1: Hash Table ======
    // 8ms
    // Time = O(m + n) Space = O(m) or O(n)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        
        Set<ListNode> set = new HashSet();
        
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        
        return null;
    }
}
