## [661. Convert BST to Greater Tree](https://www.lintcode.com/problem/convert-bst-to-greater-tree/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.


Example 1:

```
Input : {5,2,13}
              5
            /   \
           2     13
Output : {18,20,13}
             18
            /   \
          20     13
```

Example 2:

```
Input : {5,3,15}
              5
            /   \
           3     15
Output : {20,23,15}
             20
            /   \
          23     15
```

## Answer
### Method 1 - In-order (right to left) - :rocket: 156ms (100%)

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
     * @return: the new root
     */
     int pre = 0;
    public TreeNode convertBST(TreeNode root) {
        // write your code here
        dfs(root);
        
        return root;
    }
    
    private void dfs(TreeNode root) {
        if (root == null) return;
        
        dfs(root.right);
        
        root.val += pre;
        
        pre = root.val;
        
        dfs(root.left);
        
    }
}
```
