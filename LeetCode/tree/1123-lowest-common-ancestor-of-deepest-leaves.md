## [1123. Lowest Common Ancestor of Deepest Leaves](https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

- The node of a binary tree is a leaf if and only if it has no children
- The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
- The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.
 

Example 1:

```
Input: root = [1,2,3]
Output: [1,2,3]
Explanation: 
The deepest leaves are the nodes with values 2 and 3.
The lowest common ancestor of these leaves is the node with value 1.
The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
```

Example 2:

```
Input: root = [1,2,3,4]
Output: [4]
```

Example 3:

```
Input: root = [1,2,3,4,5]
Output: [2,4,5]
```

Constraints:

- The given tree will have between 1 and 1000 nodes.
- Each node of the tree will have a distinct value between 1 and 1000.

## [Answer](https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/discuss/334577/JavaC%2B%2BPython-Two-Recursive-Solution)
### Method 1 - DFS - :turtle: 2ms (22.87%)

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
    int depth;
    TreeNode res;
    Map<Integer, List<TreeNode>> map = new HashMap();
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        res = null;
        dfs(root, 1);
        
        List<TreeNode> list = map.get(depth);
        
        int size = list.size();
        
        // if only one deepest leaf, simply return it.
        if (size == 1) return list.get(0);
        
        // otherwise, iterate each two nodes to find LCA
        res = list.get(0);
        for (int i = 1; i < size; i++) { 
            res = lca(root, res, list.get(i));
        }
        
        return res;
    }
    
    // find LCA
    private TreeNode lca(TreeNode root, TreeNode r1, TreeNode r2) {
        if (root == null) return null;

        if (root.val == r1.val || root.val == r2.val) return root == r1 ? r1 : r2;
        
        TreeNode l = lca(root.left, r1, r2);
        TreeNode r = lca(root.right, r1, r2);
        
        if (l != null && r != null) {
            return root;}
        
        return l == null ? r : l;
    }
    
    // get depth and store them into a map.
    private void dfs(TreeNode root, int height) {
        if (root == null) return;
        
        map.computeIfAbsent(height, o -> new ArrayList()).add(root);
        
        if (height > depth) {
            depth = height;
        }
        
        dfs(root.left, height + 1);
        dfs(root.right, height + 1);
    }
}
```
