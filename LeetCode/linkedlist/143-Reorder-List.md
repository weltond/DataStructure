## [Question](https://leetcode.com/problems/reorder-list/)

Given a singly linked list L: L0→L1→…→Ln-1→Ln,

reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may **not** modify the values in the list's nodes, only nodes itself may be changed.

Example 1:
```
Given 1->2->3->4, reorder it to 1->4->2->3.
```
Example 2:
```
Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
```

## Answer
### Method 1 - Create a new reversed LinkedList :turtle: 33ms (21.37%) 
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
    // =========== Method 1: Naive ==========
    // 3ms (21.37%)
    int cnt;    public void reorderList(ListNode head) {
        cnt = 0;
        ListNode nHead = head;
        ListNode rHead = reverse(head); // create a new linkedlist with reverse order
        
        ListNode tmp = nHead;
        
        if (cnt <= 2) return;
        
        int loop = (cnt - 1) / 2;   // if cnt is 4, do loop once; if cnt is 5, do loop twice

        /**
        e.g.
            1->2->3->4->5
            5->4->3->2->1
        */
        while (loop-- > 0) {
            ListNode nNext = tmp.next;
            ListNode rNext = rHead.next;
            tmp.next = rHead;
            rHead.next = nNext;
            
            tmp = nNext;
            rHead = rNext;
        }
        
        if (cnt % 2 == 0) {
            tmp.next = rHead;
            rHead.next = null;
        } else {
            tmp.next = null;
        }
    }
    
    private ListNode reverse(ListNode head) {
        ListNode cur = head, prev = null, n = null;
        while (cur != null) {
            n = new ListNode(cur.val);
            n.next = prev;
            
            cur = cur.next;
            prev = n;
            cnt++;
        }
        
        return prev;
    }
}
```
