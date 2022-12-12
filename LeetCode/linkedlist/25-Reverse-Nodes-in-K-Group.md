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
### Method 2 - Iteration
:rabbit: 1ms (44.67%)
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
    // ========= Method 2: Iterative ==========
    // 1ms (44.67%)
    public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        for (ListNode i = head; i != null; n++, i = i.next);    // find total nodes
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        for (ListNode prev = dummy, tail = head; n >= k; n -= k) {  // connect each group
            for (int i = 1; i < k; i++) {   // reverse each group
                ListNode tmp = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = tmp;
            }
            
            prev = tail;
            tail = tail.next;
        }
        
        return dummy.next;
    }
}
```
### Method 1 - Recursion
#### Approach 5 - 🚀 0ms

```java
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 0 || head == null) return head;

        // get length
        ListNode cur = head;
        int size = 0;
        while(cur != null) {
            cur = cur.next;
            size++;
        }

        int chunks = size / k;
        return reverseK(head, chunks, k);
    }

    /**
        Recursive method to reverse each chunk and connect them.
        head: Head of each chunk
        chunk: Number of chunks that needs to be reversed
        k: k nodes to reverse
     */
    private ListNode reverseK(ListNode head, int chunk, int k) {
        // do not reverse if not enough nodes in chunk
        if (chunk == 0) {
            return head;   
        }

        ListNode[] pair = reverse(head, k);
        ListNode newHead = pair[0], startNode = pair[1];

        head.next = reverseK(startNode, chunk - 1, k);

        return newHead;
    }

    private ListNode[] reverse(ListNode node, int k) {
        ListNode prev = null;

        while(node != null && k > 0) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
            k--;
        }

        // prev is the new head
        // node is next node to start reverse
        return new ListNode[]{prev, node};
    }
}
```

#### Approach 4
- Notice we should use `k` as stop reversing indicator instead of using `head == end`.

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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        
        ListNode end = getLast(head, k);
        if (end == null) return head;
        ListNode nh = reverseKGroup(end.next, k);
        //System.out.println(nh.val);
        while (k-- > 0) {
            ListNode next = head.next;
            head.next = nh;
            nh = head;
            head = next;
        }
        return end;
    }
    
    private ListNode getLast(ListNode head, int k) {
        while (--k > 0) {
            if (head == null ) return null;
            head = head.next;
        }
        
        return head;
    }
}
```

#### Approach 3
:rocket: 0ms 
- like **Approach 1**
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        
        ListNode end = findEnd(head, k);
        if (end == null) return head;
        
        ListNode th = head, te = end;
        
        ListNode tmp = reverseKGroup(te.next, k);
        
        ListNode ret = reverse(th, te);
        th.next = tmp;
        
        return ret;
    }
    
    private ListNode reverse(ListNode head, ListNode end) {
        if (head == end) return head;
        
        ListNode newH = reverse(head.next, end);
        head.next.next = head;
        head.next = null;
        
        return newH;
    }
    
    private ListNode findEnd(ListNode head, int k) {
        ListNode tmp = head;
        while (--k > 0) {
            if (tmp == null || tmp.next == null) return null;
            tmp = tmp.next;
        }
        return tmp;
    }
}
```
#### Approach 2
:rocket: 0ms (100%)
```
Example: 
e.g. 1->2->3->4->5->6->7->8, k = 3

1->2->3->   4->5->6->             7->8
            |     |               |
          head   cur              cur
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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int cnt = 0;
        
        // find the k+1 node
        while (cur != null && cnt != k) {
            cur = cur.next;
            cnt++;
        }
        
        if (cnt != k) return head;
        
        cur = reverseKGroup(cur, k);    // reverse list with k+1 node as head
        // head - head to direct part
        // cur  - head to reversed part
        while (cnt-- > 0) { // reverse current k-group
            ListNode tmp = head.next;   // tmp - next head in direct part
            head.next = cur;
            
            cur = head; // move head of reversed part to a new node
            head = tmp; // move 'direct' head to the next node in direct part
        }
        
        return cur;
    }
}
```
#### Approach 1
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
