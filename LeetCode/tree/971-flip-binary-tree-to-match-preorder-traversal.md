## [971. Flip Binary Tree To Match Preorder Traversal](https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a binary tree with N nodes, each node has a different value from {1, ..., N}.

A node in this binary tree can be flipped by swapping the left child and the right child of that node.

Consider the sequence of N values reported by a preorder traversal starting from the root.  Call such a sequence of N values the voyage of the tree.

(Recall that a preorder traversal of a node means we report the current node's value, then preorder-traverse the left child, then preorder-traverse the right child.)

Our goal is to flip the **least number** of nodes in the tree so that the voyage of the tree matches the voyage we are given.

If we can do so, then return a list of the values of all nodes flipped.  You may return the answer in any order.

If we cannot do so, then return the list [-1].

 

Example 1:

```
Input: root = [1,2], voyage = [2,1]
Output: [-1]
```

Example 2:

```
Input: root = [1,2,3], voyage = [1,3,2]
Output: [1]
```

Example 3:

```
Input: root = [1,2,3], voyage = [1,2,3]
Output: []
``` 

- Note: `1 <= N <= 100`

## Answer
### Method 1 - DFS - :rocket: 0ms

- **Lease number of nodes** is just a **MISLEAD**.
- Make use of the property that voyage is pre-order. So we can use a global IDX to refer to current tree position.
- At any time the root value is not equal to current voyage IDX value, we are sure that it cannot be flipped.
- Then we make use of the next IDX is root's left, verify if root's left value equals voyage element. If not, we know we should flip root's left and right, **traverse right first then left**. Otherwise, just normal pre-order traverse.

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
    int idx = 0;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new LinkedList();
        if (root == null || voyage == null) return res;
        
        dfs(root, res, voyage);
        
        if (!res.isEmpty() && res.get(0) == -1) {
            res.clear();
            res.add(-1);
        }
        
        return res;
    }
    
    private void dfs(TreeNode root, List<Integer> res, int[] voyage) {
        if (root == null) return;
        
        if (root.val != voyage[idx++]) {
            res.clear();
            res.add(-1);
            return;
        }
        
        // use the property that voyage is pre-order
        if (root.left != null && root.left.val != voyage[idx]) {
            res.add(root.val);
            dfs(root.right, res, voyage);
            dfs(root.left, res, voyage);
        } else {
            dfs(root.left, res, voyage);
            dfs(root.right, res, voyage);
        }
    }
}
```
