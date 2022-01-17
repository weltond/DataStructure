## [426. Convert Binary Search Tree to Sorted Doubly Linked List](https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.

 

Example 1:


```
Input: root = [4,2,5,1,3]


Output: [1,2,3,4,5]

Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
```
![image](https://user-images.githubusercontent.com/9000286/149713664-d2cf0235-8a98-43e0-b0d6-3949d30d6601.png)

Example 2:

```
Input: root = [2,1,3]
Output: [1,2,3]
```

**Constraints**:

- The number of nodes in the tree is in the range [0, 2000].
- -1000 <= Node.val <= 1000
- All the values of the tree are unique.

## Answers

Use in-order since this is BST.

Use `prev` Node to keep track of nodes in sorted ascending. After traversing, `prev` will be the **tail** node.

Use `dummy` node to keep track of the **head**.

Once traversal completes, connect `head` and `tail`.

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
    Node prev;
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        
        Node dummy = new Node(0);
        prev = dummy;
        
        // connect them
        dfs(root);
        
        // handle head and tail
        Node head = dummy.right, tail = prev;
        
        head.left = tail;
        tail.right = head;
        
        return dummy.right;
    }
    
    // in-order.
    // use prev to keep track of BST in sorted
    private void dfs(Node root) {
        if (root == null) return;
        
        dfs(root.left);
        
        root.left = prev;
        prev.right = root;
        prev = root;
        
        dfs(root.right);
    }
}
```
