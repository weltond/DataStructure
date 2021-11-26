## [143. Reorder List](https://leetcode.com/problems/reorder-list/)

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
### Method 2 - 3 steps and no extra space :rocket: 1ms (100%)
#### Approach 2
**Different operation on Step 2 and Step 3.**
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
    // ========= Method 2 =========
    // Approach 2: 1ms (100%)
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        // e.g. 1->2->3->4->5->6
        // 1. find middle
        ListNode h1 = head, h2 = head;
        while (h2.next != null && h2.next.next != null) {
            h1 = h1.next;
            h2 = h2.next.next;
        }
        // preMid -> 3, preCur -> 4
        ListNode preMid = h1;
        ListNode preCur = h1.next;

        // 1->2->3->  4->5->6
        // 2. reverse the second half to 1->2->3->6->5->4
        while (preCur.next != null) {
            ListNode tmp = preCur.next;
            preCur.next = tmp.next;
            tmp.next = preMid.next;
            preMid.next = tmp;
        }

        // 3. Start reorder one by one
        h1 = head;          // 1
        h2 = preMid.next;   // 6, preMid = 3
        while (h1 != preMid) {
            preMid.next = h2.next;  
            h2.next = h1.next;     
            h1.next = h2;           
            
            h1 = h2.next;           
            h2 = preMid.next;       
        }
        
    }
}
```

- 3ms 🐢(30.28%)
```java
class Solution {
    // 1->2->3->4->5
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // go to mid
        ListNode slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse after mid to get a new linked list
        // slow is 3. tmp is 4
        ListNode tmp = slow.next;
        slow.next = null;   // disconnect mid and rest
        ListNode prev = null;
        while (tmp != null) {
            ListNode next = tmp.next;
            tmp.next = prev;
            prev = tmp;
            tmp = next;
        }   // 1->2->3->null
            // 5->4->null

        slow = prev;

        ListNode cur = head;
        // now slow is new head. 
        // merge two linked lists
        while (slow != null) {
            ListNode next1 = cur.next;
            ListNode next2 = slow.next;

            cur.next = slow;
            slow.next = next1;

            cur = next1;
            slow = next2;
        }
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
    // ========= Method 2 =========
    // Approach 1: 1ms (100%)
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        // e.g. 1->2->3->4->5->6
        // 1. find middle
        ListNode h1 = head, h2 = head;
        while (h2.next != null && h2.next.next != null) {
            h1 = h1.next;
            h2 = h2.next.next;
        }
        // preMid -> 3, preCur -> 4
        ListNode preMid = h1;
        ListNode preCur = h1.next;

        // 2. reverse the second half to 1->2->3->6->5->4
        ListNode prev = null;
        while (preCur != null) {
            ListNode tmp = preCur.next;
            preCur.next = prev;
            prev = preCur;
            preCur = tmp;
        }
        preMid.next = prev;
        
        // 3. Start reorder one by one
        h1 = head;
        h2 = preMid.next;
        while (h1 != preMid) {
            ListNode n1 = h1.next;
            ListNode n2 = h2.next;
            h1.next = h2;
            h2.next = n1;
            h1 = n1;
            h2 = n2;
        }
        h1.next = h2;
    }
}
```
### Method 1 - Create a new reversed LinkedList :turtle: 3ms (21.37%) 
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
