## [203. Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements/)

Remove all elements from a linked list of integers that have value val.

Example:
```
Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
```
## Answer
### Method 1 - LinkedList - :rocket: 1ms (98.31%)
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
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
    
        
        while (head != null) {
            if (head.val == val) {
                head = head.next;
                continue;
            }
            
            prev.next = head;
            head = head.next;
            prev = prev.next;
        }
        
        prev.next = null;
        return dummy.next;
    }
}
```
