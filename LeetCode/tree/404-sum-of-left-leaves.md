## [404. Sum of Left Leaves](https://leetcode.com/problems/sum-of-left-leaves/description/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given the root of a binary tree, return the sum of all left leaves.

 

Example 1:

```
Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
```
Example 2:
```
Input: root = [1]
Output: 0
``` 

**Constraints:**

- The number of nodes in the tree is in the range [1, 1000].
- -1000 <= Node.val <= 1000

## Answer
## Approach 2
```java
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, true);
    }

    private int dfs(TreeNode root, boolean isRight) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) {
            return isRight ? 0 : root.val;
        }

        return dfs(root.left, false) + dfs(root.right, true);
    }
}
```

## Approach 1
```java
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;  // if only root, should return 0.
        
        return dfs(root);
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        
        int left = dfs(root.left);
        // return 0 if 1) root.right is null or 2) root.right is leaf node.
        int right = root.right == null ? 0 : root.right.left == null && root.right.right == null ? 0 : dfs(root.right);
        
        return left + right;
    }
}
```
