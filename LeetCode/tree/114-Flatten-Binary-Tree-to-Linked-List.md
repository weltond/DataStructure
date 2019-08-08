## [114. Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/)

Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:
```
    1
   / \
  2   5
 / \   \
3   4   6
```
The flattened tree should look like:
```
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```

## Answer
### Method 2 - Post order
```java

```
### Method 1 - Pre order
#### Approach 2
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // 1ms (53.28%)
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        if (root.left != null) flatten(root.left);
        if (root.right != null) flatten(root.right);
        
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        
        while (root.right != null) {
            root = root.right;
        }
        
        root.right = tmp;
        
    }
    
}
```
#### Approach 1
```java
class Solution {
    // ============ Method 1: Pre-order ===========
    // 0ms
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        helper(root);
    }
    
    private TreeNode helper(TreeNode root) {
        if (root == null) return null;
        
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        
        // Get left subtree's right most node
        TreeNode tmp = left, prev = null;
        while (tmp != null) {
            prev = tmp;
            tmp = tmp.right;
        }
        
        root.left = null;
        
        // if exist, make the right most node as root for root's right subtree
        if (prev != null) {
            root.right = left;
            prev.right = right;
        }
        // else {
        //     root.right = right;
        // }
            
        
        return root;
    }
}
```
