## [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

Given a linked list, remove the n-th node from the end of list and return its head.

Example:
```
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
```
Note:

- Given n will always be valid.

Follow up:

- Could you do this in one pass?
## Answer
### Method 1 - :rocket: 0ms
#### Approach 3

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode h = head, tmp = head;
        
        while (tmp != null && n-- > 0) {
            tmp = tmp.next;
        }
        
        if (tmp == null) return head.next;
        
        while (tmp != null && tmp.next != null) {
            h = h.next;
            tmp = tmp.next;
        }
        
        h.next = h.next.next;
        
        return head;
    }
}
```

#### Approach 2
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, tmp = head, cur = head;
        while (n > 0 && tmp != null) {
            tmp = tmp.next;
            n--;
        }
        
        while (tmp != null) {
            tmp = tmp.next;
            prev = cur;
            cur = cur.next;
        }
        
        prev.next = cur.next;
        return dummy.next;
    }
}
```
#### Approach 1
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        
        ListNode tmp = head;
        while (--n > 0) {
            tmp = tmp.next;
        }
        
        ListNode cur = head, prev = null;
        
        while (tmp.next != null) {
            prev = cur;
            cur = cur.next;
            tmp = tmp.next;
        }
        
        if (prev == null) return head.next;
        
        prev.next = cur.next;
        cur = null;
        
        return head;
    }
}
```
