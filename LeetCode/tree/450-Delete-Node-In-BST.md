## [450. Delete Node in a BST](https://leetcode.com/problems/delete-node-in-a-bst/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

- Search for a node to remove.
- If the node is found, delete the node.

Note: Time complexity should be O(height of tree).

Example:

```
root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
```

## Answer
### Method 1 - Delete Directly - :rocket: 0ms

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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            // no right
            if (root.right == null) return root.left;
            // no left
            if (root.left == null) return root.right;
            
            // has two children
            TreeNode n = root, cur = root.right;
            while (cur.left != null) {
                n = cur;
                cur = cur.left;
            }
            
            // if right child has left child, if not, do not assign right.
            if (n != root) {
                n.left = cur.right;   // reassign 
                cur.right = root.right;
            } 

            cur.left = root.left;
            root.left = null;
            root.right = null;
            
            return cur;
        }
        
        return root;
    }
}
```

### Old Post

```java
package com.weltond.tree;

/**
 * @author weltond
 * @project LeetCode
 * @date 1/25/2019
 */

/*
    Idea:
        1. Find the target in the BST (recursion)
            a. cur.val > key, then go left
            b. cur.val < key, then go right
            c. cur.val = key, find the target
        2. Delete target from tree.
            a. current node doesn't have left and right child
            b. current node only has left or right child
            c. current node has both left and right child
 */
public class LC450DeleteNodeInBST {
    // replace the Node with the right most min node when the Node has both left and right child
    // Time = O(height), Space = O(height)
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    public TreeNode findMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int val) {
        this.val = val;
    }
}

/**
Implementation 2: we DONOT swap value, but swap NODE
*/
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        
        if (root.val > key) root.left = deleteNode(root.left, key);
        else if (root.val < key) root.right = deleteNode(root.right, key);
        else {
            // if no right and left 
            //if (root.right == null && root.left == null) return null;
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            
            // has two children
            TreeNode cur = root.right;
            TreeNode prev = root;
            while (cur.left != null) {
                prev = cur;
                cur = cur.left;
            }
            // we swap node instead of value!
            if (prev != root) {
                prev.left = cur.right;
                cur.right = root.right;
            }
            cur.left = root.left;
            
            
            return cur;
        }
        
        return root;
    }
}
```
