## [430. Flatten a Multilevel Doubly Linked List](https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.

 

Example 1:

```
Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]
```

Example 2:

```
Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation:

The input multilevel linked list is as follows:

  1---2---NULL
  |
  3---NULL
```

Example 3:

```
Input: head = []
Output: []
```

** How multilevel linked list is represented in test case:**

We use the multilevel linked list from Example 1 above:

```
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
```

The serialization of each level is as follows:

```
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
```

To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:

```
[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
```

Merging the serialization of each level and removing trailing nulls we obtain:

```
[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
```

Constraints:

- Number of Nodes will not exceed 1000.
- `1 <= Node.val <= 10^5`

## Answer
### Method 1 - Recursion -

#### Approach 2 :turtle: 1ms (21.24%)

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
class Solution {
    public Node flatten(Node head) {
        Node cur = head;
        while (cur != null) {
            if (cur.child == null) {
                cur = cur.next;
            } else {
                Node ret = flatten(cur.child);
                cur.child = null;
                
                Node next = cur.next;
                cur.next = ret;
                if (ret != null) ret.prev = cur;
                
                Node pre = null;
                while (ret != null) {
                    pre = ret;
                    ret = ret.next;
                }
                
                if (pre != null) {
                    pre.next = next;
                }
                if (next != null) {
                    next.prev = pre;
                }
                
                cur = next;
            }
        }
        
        return head;
    }
}

```

#### Approach 1 :rocket: 0ms 

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    // =========== Method 1 ===========
    // 0ms
    public Node flatten(Node head) {
        Node cur = head;
        helper(head);
        return head;
    }
    
    private Node helper(Node head) {
        Node cur = head;
        Node prev = null;
        while (cur != null) {
            prev = cur;
            if (cur.child != null) {
                Node next = cur.next;
                cur.next = cur.child;
                cur.child.prev = cur;   // connect cur's child's prev to cur
                
                Node last = helper(cur.child);  // get child's last node
                
                // find the last node of new linkedlist.
                while (last.next != null) {
                    last = last.next;
                }
                
                last.next = next;   // connect child's last to cur's next
                
                if (next != null) next.prev = last;   // connect cur's prev to child's last
                cur.child = null;   // set child to null
                cur = next;
            } else {
                cur = cur.next;
            }
        }
        return prev;
    }
}
```
