## [24. Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/)

Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example:
```
Given 1->2->3->4, you should return the list as 2->1->4->3.
```

## Answer
### Method 2 - Iteration - :rocket: 0ms
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        dummy.next = head;
        
        ListNode cur = head, next = cur.next;
        
        while (cur != null && cur.next != null) {
            prev.next = next;   // dummy connect to 2
            cur.next = next.next;   // 1 connect to 3
            next.next = cur;    // 2 connect to 1
            
            prev = cur;
            cur = cur.next;
            next = cur == null ? null : cur.next;
        }
        
        return dummy.next;
    }
}
```
### Method 1 - Recursion - :rocket: 0ms
- Take [1,2] as current, [3,4] as recursion return which has been swapped to [4,3].
- Then we swap 1,2 and [4,3].
- Finally we return node 2 because it is the new head.

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        
        return n;
    }
}
```
