## [687. Longest Univalue Path](https://leetcode.com/problems/longest-univalue-path/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

The length of path between two nodes is represented by the number of edges between them.

 

Example 1:

```
Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output: 2
```
 

Example 2:

```
Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output: 2
```
 

- Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.

## Answer
### Method 1 - Post Order - :rocket: 3ms (95.33%)

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
    int res = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        
        dfs(root, root.val);
        return res;
    }
    
    private int dfs(TreeNode root, int parVal) {
        if (root == null) return 0;
        
        int l = dfs(root.left, root.val);
        int r = dfs(root.right, root.val);
        
        res = Math.max(res, l + r);
        if (root.val == parVal) {
            return Math.max(l, r) + 1;
        } else {
            return 0;
        }
    }
}
```
