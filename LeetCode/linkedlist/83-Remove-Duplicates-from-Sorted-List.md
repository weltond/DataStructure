## [83. Remove Duplicates from Sorted List](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)

Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:
```
Input: 1->1->2
Output: 1->2
```
Example 2:
```
Input: 1->1->2->3->3
Output: 1->2->3
```
## Answer
### Method 2 - Recursion - :rocket: 0ms
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
    public ListNode deleteDuplicates(ListNode head) {
        return dfs(head);
    }
    
    private ListNode dfs(ListNode head) {
        if (head == null || head.next == null) return head;
        
        if (head.val != head.next.val) {
            head.next = dfs(head.next);
            return head;
        }
        
        ListNode cur = head.next;
        while (cur != null && cur.val == head.val) {
            cur = cur.next;
        }
        
        head.next = dfs(cur);
        
        return head;
    }
}
```
### Method 1 - Iteration - :rocket: 0ms
#### Approach 4 - Best Edition
Just like Leetcode 26. Remove Duplicate from array.

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        
        ListNode slow = head, fast = head;

        while (fast != null) {
            if (slow.val != fast.val) {
                // nums[slow] = nums[fast];
                slow.next = fast;
                // slow++;
                slow = slow.next;
            }

            // fast++;
            fast = fast.next;
        }

        // IMPORTANT! Disconnect rest duplicate
        slow.next = null;

        return head;
    }
}

```
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        
        return head;
    }
}
```
#### Approach 2 🐢 1ms (30.8%)
```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = head;
        ListNode cur = head;

        while (cur != null) {
            prev = cur;
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            // cur is the last occurence
            prev.next = cur.next;
            cur = cur.next;
        }

        return head;
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode prev = head, cur = head.next;
        
        while (cur != null) {
            while (cur != null && cur.val == prev.val) {
                cur = cur.next;
            }
            prev.next = cur;
            prev = cur;
        }
        
        return head;
    }
}
```
