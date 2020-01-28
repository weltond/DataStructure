## [445. Add Two Numbers II](https://leetcode.com/problems/add-two-numbers-ii/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

**Follow up:**
- What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

```
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
```

## Answer
### Method 2 - Recursion - :rocket: 2ms (99.63%)

- Get each length first
- Recursion and add values when the length diff is `0`.

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
    int carry = 0;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int len1 = getLen(l1), len2 = getLen(l2);
        ListNode res = null;
        
        // always use the smaller size as reference
        if (len1 < len2) {
            res = add(l1, l2, len2 - len1);
        } else {
            res = add(l2, l1, len1 - len2);
        }
        
        if (carry != 0) {
            ListNode old = res;
            res = new ListNode(carry);
            res.next = old;
        }
        return res;
    }
    
    // recursion based on diff. l1 is always less than or equal to l2 
    private ListNode add(ListNode l1, ListNode l2, int diff) {
        if (l1.next == null && l2.next == null) {
            int sum = l1.val + l2.val;
            carry = sum / 10;
            return new ListNode(sum % 10);
        }
        
        ListNode res, next;
        int sum = 0;
        if (diff == 0) {
            next = add(l1.next, l2.next, 0);
            sum = carry + l1.val + l2.val;  // sum should be added after recursion.
        } else {
            next = add(l1, l2.next, diff - 1);
            sum = carry + l2.val;
        }
        
        res = new ListNode(sum % 10);
        carry = sum / 10;
        res.next = next;
        
        return res;
    }
    
    private int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}
```

### Method 1 - Two Stacks - :rabbit: 3ms

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> s1 = new LinkedList();
        Deque<Integer> s2 = new LinkedList();
        
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        int sum = 0;
        ListNode n = null, last = null;
        
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) sum += s1.pop();
            if (!s2.isEmpty()) sum += s2.pop();
            
            n = new ListNode(sum % 10);
            n.next = last;
            last = n;
            
            sum /= 10;
        }
        if (sum != 0) {
            n = new ListNode(sum);
            n.next = last;
        }
        return n;
    }
}
```
