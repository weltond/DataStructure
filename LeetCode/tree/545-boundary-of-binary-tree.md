## [545. Boundary of Binary Tree]()

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.


Example 1:

···
Input: {1,#,2,3,4}
Output: [1,3,4,2]
Explanation: 
  1
   \
    2
   / \
  3   4
  The root doesn't have left subtree, so the root itself is left boundary.
  The leaves are node 3 and 4.
  The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
  So order them in anti-clockwise without duplicates and we have [1,3,4,2].
···

Example 2:

··
Input: {1,2,3,4,5,6,#,#,#,7,8,9,10}
Output: [1,2,4,7,8,9,10,6,3]
Explanation: 
          1
     /          \
    2            3
   / \          / 
  4   5        6   
     / \      / \
    7   8    9  10  
  The left boundary are node 1,2,4. (4 is the left-most node according to definition)
  The leaves are node 4,7,8,9,10.
  The right boundary are node 1,3,6,10. (10 is the right-most node).
  So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
···

## Answer
### Method 1 

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
     * @param root: a TreeNode
     * @return: a list of integer
     */
     
    List<Integer> res = new ArrayList();
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        // write your code here
        if (root == null) return res;

        res.add(root.val);
        
        // add left
        leftView(root.left);
        
        // add leaf
        leaf(root);
        
        // add right
        rightView(root.right);

        return res;
    }
    
    private void dfs(TreeNode root) {
        if (root == null) return;
    }
    private void leftView(TreeNode root) {
        if (root == null) return;
        
        // if left is not null
        if (root.left != null) {
            res.add(root.val);
            leftView(root.left);
        } // left is null but right is not null
        else if (root.right != null) {
            res.add(root.val);
            leftView(root.right);
        }
        // leafnode, do nothing!
    }
    private void rightView(TreeNode root) {
        if (root == null) return;
        
        // if right not null
        if (root.right != null) {
            rightView(root.right);
            // add after recursion
            res.add(root.val);
        }// if right is null but left is not null
        else if (root.left != null) {
            rightView(root.left);
            res.add(root.val);
        }
    }
    
    private void leaf(TreeNode root) {
        if (root == null) return;
        
        if (root.left == null && root.right == null) {
            res.add(root.val);
        }
        
        leaf(root.left);
        leaf(root.right);
    }
    
}
```
