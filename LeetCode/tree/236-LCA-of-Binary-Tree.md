## [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  `root = [3,5,1,6,2,0,8,null,null,7,4]`


Example 1:

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```

Example 2:

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
``` 

Note:

- All of the nodes' values will be unique.
- `p` and `q` are different and both values will exist in the binary tree.

## Answer
### Method 2 - BFS - :turtle: 12ms (15.81%)
- See notes in original post.

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList();
        Map<TreeNode, TreeNode> map = new HashMap();
        
        map.put(root, null);
        queue.offer(root);
        
        while (!map.containsKey(p) || !map.containsKey(q)) {
            TreeNode n = queue.poll();
            
            if (n.left != null) {
                map.put(n.left, n);
                queue.offer(n.left);
            }
            if (n.right != null) {
                map.put(n.right, n);
                queue.offer(n.right);
            }
        }
        
        Set<TreeNode> set = new HashSet();
        
        while (p != null) {
            set.add(p);
            p = map.get(p);
        }
        
        while (!set.contains(q)) {
            q = map.get(q);
        }
        
        return q;
    }
}
```

### Method 1 - DFS - :rockt: 5ms (99.88%)

- Only return `p` or `q` or their `root` if `root`'s left and right are not `null`.

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }
    
    private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        
        if (root == p || root == q) return root;
        
        TreeNode l = dfs(root.left, p, q);
        TreeNode r = dfs(root.right, p, q);
        
        if (l != null && r != null) return root;
        
        return l == null ? r : l;
    }
}
```

```java
/* Original Post*/

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
    
    // =========== Method 2: Iterative ==============
    // Time = O(n), Space = O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        // Hashmap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap();
        
        parent.put(root, null);
        stack.push(root);
        
        // Iterate until we find both the nodes p and q 
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            
            // while traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        System.out.println(parent);
        // ancestors set() for node p
        Set<TreeNode> ancestors = new HashSet();
        
        // process all ancestors for node p using parent pointers
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        
        // the first ancestor of q which appears in p's ancestor set() is res
        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        
        return q;
    }
    
    // =========== Method 1: Recursion ==============
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (root == q || root == p) return root;
        
        // here, root != p && q
        if (left == null && right == null) return null;
        else if (left != null && right != null) {
            //System.out.println(left.val + ", " + right.val);
            return root;
        }
        else {
            //System.out.println(root.val + " -> its left is null? : " + (left == null));
            return left == null ? right : left;
            
        }
    }
}
```
