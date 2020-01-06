## [95. Validate Binary Search Tree](https://www.lintcode.com/problem/validate-binary-search-tree/description?_from=ladder&&fromId=130)

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

- The left subtree of a node contains only nodes with keys less than the node's key.
- The right subtree of a node contains only nodes with keys greater than the node's key.
- Both the left and right subtrees must also be binary search trees.
- A single node tree is a BST

Example 1:

```
Input:  {-1}
Output：true
Explanation：
For the following binary tree（only one node）:
	      -1
This is a binary search tree.
```

Example 2:

```
Input:  {2,1,4,#,#,3,5}
Output: true
For the following binary tree:
	  2
	 / \
	1   4
	   / \
	  3   5
This is a binary search tree.
```

## Answer
### Method 1 - DFS - :rabbit: 333ms (97.00%)

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
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here
        return dfs(root, Long.MAX_VALUE, Long.MIN_VALUE);
        
    }
    
    private boolean dfs(TreeNode root, long maxV, long minV) {
        if (root == null) return true;
        
        if ((long)root.val < maxV && (long)root.val > minV) {
            if (dfs(root.left, root.val, minV) && dfs(root.right, maxV, root.val)) {
                return true;
            }
        }
        
        return false;
    }
}
```
