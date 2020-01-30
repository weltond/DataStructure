## [448. Inorder Successor in BST](https://www.lintcode.com/problem/inorder-successor-in-bst/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in the BST.

If the given node has no in-order successor in the tree, return null.

Example 1:

```
Input: {1,#,2}, node with value 1
Output: 2
Explanation:
  1
   \
    2
```

Example 2:

```
Input: {2,1,3}, node with value 1
Output: 2
Explanation: 
    2
   / \
  1   3
Binary Tree Representation
```

Challenge
- O(h), where h is the height of the BST.

Notice
- It's guaranteed `p` is one node in the given tree. (You can directly compare the memory address to find `p`)

## Answer
### Method 1 - BST - :rocket: 1811ms (99.60%)

- O(h)
- cur > p -> go right
- cur < p -> go left, update pre.
- cur = p -> find it's right's left most node

#### Appraoch 2

- We can keep going right when we find the node. Because all the nodes on its right is larger then it. So it will always update the `pre` node.

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


public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
     
     TreeNode pre = new TreeNode(Integer.MAX_VALUE);
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // 1. cur > p -> go right
        // 2. cur < p -> go left, update pre.
        // 3. cur = p -> find it's right's left most node
        dfs(root, p);
        
        return pre.val == Integer.MAX_VALUE ? null : pre;
    }
    
    private void dfs(TreeNode root, TreeNode p) {
        if (root == null) return;
        if (root.val > p.val) {
            pre = root; // should assign before recursion
            dfs(root.left, p);
        } else {
            dfs(root.right, p);
        }
    }
}
```

#### Approach 1

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


public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
     
     TreeNode pre = new TreeNode(Integer.MAX_VALUE);
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // 1. cur > p -> go right
        // 2. cur < p -> go left, update pre.
        // 3. cur = p -> find it's right's left most node
        if (root == null) return null;
        dfs(root, p);
        
        return pre.val == Integer.MAX_VALUE ? null : pre;
    }
    
    private void dfs(TreeNode root, TreeNode p) {
        if (root.val < p.val) {
            dfs(root.right, p);
        } else if (root.val > p.val) {
            pre = root; // should assign before recursion
            dfs(root.left, p);
        } else {
            TreeNode tmp = root.right;
                
            while (tmp != null) {
                pre = tmp;
                tmp = tmp.left;
            }
        }
    }
}
```
