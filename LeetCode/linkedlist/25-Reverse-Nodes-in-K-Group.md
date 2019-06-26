## [Question](https://leetcode.com/problems/reverse-nodes-in-k-group/)

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:
```
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
```
Note:

- Only constant extra memory is allowed.
- You may not alter the values in the list's nodes, only nodes itself may be changed.

## Answer
### Method 1 - Recursion
:rocket: 0ms (100%)
```
Example: 
e.g. 1->2->3->4->5->6->7->8, k = 3

1->2->3->   4->5->6->             7->8
            |     |               |
          head   newH/tail/tmp    tmp/head
```
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
    // ============ Method 1: Recursion ===========
    // 0ms 
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        
        ListNode tail = head;

        // get the tail of each group
        int cnt = 1;
        while (tail.next != null && cnt < k) {
            tail = tail.next;
            cnt++;
        }

        if (cnt != k) return head;
        
        ListNode tmp = reverseKGroup(tail.next, k);
        ListNode newH = reverse(head, tail);
        head.next = tmp;
        
        return newH;
    }
    
    // reverse the k-group
    private ListNode reverse(ListNode head, ListNode tail) {
        if (head == tail) return head;
        
        ListNode newH = reverse(head.next, tail);
        
        head.next.next = head;
        
        head.next = null;
        
        return newH;
    }
}
```
