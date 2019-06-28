## [99. Recover Binary Search Tree](https://leetcode.com/problems/recover-binary-search-tree/)

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:
```
Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
```
Example 2:
```
Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
```
Follow up:

- A solution using O(n) space is pretty straight forward.
- Could you devise a constant space solution?

## Answer
### Method 1 - Inorder :turtle: 11ms (9.38%) 
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
    // ===== Method 1: Inorder ======
    // 11ms (9.38%)
    TreeNode first = null, second = null, prev = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        dfs(root);
        
        if (first == null) return;
        System.out.println(first.val + ","+second.val);
        swap(first, second);
    }
    
    private void dfs(TreeNode root) {
        if (root == null) return;
        
        dfs(root.left);
        
        // if first not found, assign it to prev
        if (first == null && root.val < prev.val) { // CANNOT be root.val <= prev.val because that root.val may also be Integer.MIN_VALUE
            first = prev;
        }
        // if first is found, assign the second to root
        if (first != null && root.val < prev.val) {
            second = root;
        }
        
        prev = root;
        
        dfs(root.right);
    }
    
    private void swap(TreeNode first, TreeNode second) {
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
}
```
