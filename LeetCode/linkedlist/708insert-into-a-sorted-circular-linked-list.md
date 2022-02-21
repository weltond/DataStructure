## [708. Insert into a Sorted Circular Linked List](https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a Circular Linked List node, which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.

If the list is empty (i.e., the given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the originally given node.

 

Example 1:

<img width="325" alt="image" src="https://user-images.githubusercontent.com/9000286/154909395-8d9e09a2-6f7e-494f-b47b-3ccb010e0ce7.png">


 ```
Input: head = [3,4,1], insertVal = 2
Output: [3,4,1,2]
Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.
```
<img width="328" alt="image" src="https://user-images.githubusercontent.com/9000286/154909449-f831844a-2da8-46c0-b2a3-da664eb1b275.png">


Example 2:

```
Input: head = [], insertVal = 1
Output: [1]
Explanation: The list is empty (given head is null). We create a new single circular list and return the reference to that single node.
```

Example 3:

```
Input: head = [1], insertVal = 0
Output: [1,0]
``` 

**Constraints**:

- The number of nodes in the list is in the range [0, 5 * 104].
- -106 <= Node.val, insertVal <= 106

## Answers

### Method 1 - 0ms 

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    /**
    Case 1: Empty list -> We just make the node points to itself.
    Case 2: insertVal is greater than maxVal or smaller than minVal.
    Case 3: insertVal is smaller than maxVal and greater than minVal
    */
    public Node insert(Node head, int insertVal) {
        // 1. empty head
        Node node = new Node(insertVal);
        
        if (head == null) {
            node.next = node;
            
            return node;
        }
        
        Node prev = head, cur = head.next;
        
        while (cur != head) {   // NOT cur != null in case only one node [1]
            // Case 3
            if (prev.val <= insertVal && insertVal <= cur.val) break;
            
            // Case 2
            if (prev.val > cur.val && (insertVal >= prev.val || insertVal <= cur.val)) break;
            
            prev = cur;
            cur = cur.next;
        }

        prev.next = node;
        node.next = cur;
        
        return head;
    }
}
```
