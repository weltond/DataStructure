## [649. Binary Tree Upside Down](https://www.lintcode.com/problem/binary-tree-upside-down/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

Example1

```
Input: {1,2,3,4,5}
Output: {4,5,2,#,#,3,1}
Explanation:
The input is
    1
   / \
  2   3
 / \
4   5
and the output is
   4
  / \
 5   2
    / \
   3   1
```

Example2

```
Input: {1,2,3,4}
Output: {4,#,2,3,1}
Explanation:
The input is
    1
   / \
  2   3
 /
4
and the output is
   4
    \
     2
    / \
   3   1
```

## Answer
### Method 1 - DFS - :rabbit: 186ms (60.20%)

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
     * @param root: the root of binary tree
     * @return: new root
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        // write your code here
        return dfs(root);
    }
    
    private TreeNode dfs(TreeNode root) {
        if (root == null) return null;
        
        TreeNode l = dfs(root.left); //r = dfs(root.right);
        
        if (l == null) return root;
        
        root.left.right = root;
        root.left.left = root.right;
        root.left = null;
        root.right = null;
        

        // following is to also flip the right subtree.
        // TreeNode tmp = l;
        // while (tmp.right != null) {
        //     tmp = tmp.right;
        // }
        // tmp.right = root;
        // tmp.left = r;
        
        return l;
    }
}
```
