## [107. Binary Tree Level Order Traversal II](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).

 

Example 1:
```

Input: root = [3,9,20,null,null,15,7]
Output: [[15,7],[9,20],[3]]
```
Example 2:
```
Input: root = [1]
Output: [[1]]
```
Example 3:
```
Input: root = []
Output: []
```

**Constraints:**
- The number of nodes in the tree is in the range [0, 2000].
- -1000 <= Node.val <= 1000

## Answer
### Method 1 - Recursion 🚀 0ms
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        dfs(root, 0, res);

        return res;

    }

    private void dfs(TreeNode root, int lvl, List<List<Integer>> res) {
        if (root == null) return;

        if (res.size() == lvl) {
            res.add(0, new ArrayList<>());
        }

        res.get(res.size() - 1 - lvl).add(root.val);

        dfs(root.left, lvl + 1, res);
        dfs(root.right, lvl + 1, res);
    }
}
```
