## [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree `1,2,2,3,4,4,3]` is symmetric:
```
    1
   / \
  2   2
 / \ / \
3  4 4  3
``` 

But the following `[1,2,2,null,3,null,3]` is not:
```
    1
   / \
  2   2
   \   \
   3    3
``` 

Note:
Bonus points if you could solve it both recursively and iteratively.

## Answer
### Methdo 2 - Iteration - :rabbit: 1ms (36.98%)
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
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q=  new LinkedList();
        
        q.offer(root);
        q.offer(root);
        
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll(), t2 = q.poll();
            
            if (t1 == null && t2 == null) continue;
            else if (t1 == null || t2 == null) return false;
            
            if (t1.val != t2.val) return false;
            
            q.offer(t1.left);
            q.offer(t2.right);
            q.offer(t1.right);
            q.offer(t2.left);
        }
        
        return true;
    }
}
```
### Method 1 - Recursive - :rocket: 0ms
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
    public boolean isSymmetric(TreeNode root) {
        return dfs(root, root);
    }
    
    private boolean dfs(TreeNode r1, TreeNode r2) {
        if (r1 == null || r2 == null) return r1 == r2;
        
        return r1.val == r2.val && dfs(r1.left, r2.right) && dfs(r1.right, r2.left);
    }
}
```
