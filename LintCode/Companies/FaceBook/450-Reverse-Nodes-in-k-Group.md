## [450. Reverse Nodes in k-Group](https://www.lintcode.com/problem/reverse-nodes-in-k-group/description?_from=ladder&&fromId=130)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

Example
Example 1

```
Input:
list = 1->2->3->4->5->null
k = 2
Output:
2->1->4->3->5
```

Example 2

```
Input:
list = 1->2->3->4->5->null
k = 3
Output:
3->2->1->4->5
```

## Answer
### Method 1 - Recurssion - :rabbit: 244ms (67.40%)

- For more solution, see [here](https://github.com/weltond/DataStructure/blob/master/LeetCode/linkedlist/25-Reverse-Nodes-in-K-Group.md).

```java
/**
 * Definition for ListNode
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
    /**
     * @param head: a ListNode
     * @param k: An integer
     * @return: a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        
        // 1. reverse k, get its head
        ListNode cur = head;
        ListNode ret = reverse(cur, k);
        
        // 2. link next group
        head.next = reverseKGroup(head.next, k);
        
        return ret;
    }
    
    private ListNode reverse(ListNode head, int k) {
        ListNode tail = head;
        while (tail != null && k > 0) {
            tail = tail.next;
            k--;
        }
        
        if (k != 0) return head;
        
        // ListNode dummy = new ListNode(0);
        // ListNode r = dummy;
        ListNode cur = head;
        ListNode pre = tail;
        while (tail != cur) {
            ListNode next = cur.next;
            cur.next = pre;
            
            pre = cur;
            cur = next;
        }
        
        head.next = tail;
        
        return pre;
    }
}
```
