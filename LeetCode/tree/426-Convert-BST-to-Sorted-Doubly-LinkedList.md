## [426. Convert Binary Search Tree to Sorted Doubly Linked List](https://www.cnblogs.com/grandyang/p/9615871.html)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:



We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.



Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.




Example 1:

```
Input: {4,2,5,1,3}
        4
       /  \
      2   5
     / \
    1   3
Output: "left:1->5->4->3->2  right:1->2->3->4->5"
Explanation:
Left: reverse output
Right: positive sequence output
```

Example 2:

```
Input: {2,1,3}
        2
       /  \
      1   3
Output: "left:1->3->2  right:1->2->3"
```

## Answer

### Method 2 - Divide and Conquer :rabbit: 201ms (49.60%)

- Remeber to make self (root) also as a DLL before merge.

```java
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        // Write your code here.
        if (root == null) return null;
        
        TreeNode leftHead = treeToDoublyList(root.left);
        TreeNode rightHead = treeToDoublyList(root.right);
        
        root.left = root;
        root.right = root;
        
        return merge(merge(leftHead, root), rightHead);
    }
    
    private TreeNode merge(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null) return n1 == null ? n2 : n1;
        
        TreeNode tail1 = n1.left, tail2 = n2.left;
        tail1.right = n2;
        n2.left = tail1;
        
        tail2.right = n1;
        n1.left = tail2;
        
        return n1;
    }
}
```

### Method 1 - Inorder - :rabbit: 201ms (49.60%)

```java
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    TreeNode dummy = new TreeNode(0);
    TreeNode pre = dummy;
    /**
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        // Write your code here.
        if (root == null) return null;
        
        connect(root);
        
        // now dummy's right is 1. pre is 5 when tree is [4,2,5,1,3]
        pre.right = dummy.right;
        dummy.right.left = pre;
        
        return pre.right;
    }
    
    private void connect(TreeNode root) {
        if (root == null) return;
        
        connect(root.left);
        
        root.left = pre;
        pre.right = root;
        pre = root;
        
        connect(root.right);
    }
}
```
