## [109. Convert Sorted List to BST](https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given the root of a binary tree, return the postorder traversal of its nodes' values.

Example 1:

```
Input: root = [1,null,2,3]
Output: [3,2,1]
```

Example 2:

```
Input: root = []
Output: []
```
Example 3:
```
Input: root = [1]
Output: [1]
```
Example 4:
```
Input: root = [1,2]
Output: [2,1]
```
Example 5:

```
Input: root = [1,null,2]
Output: [2,1]
```

**Constraints**:

- The number of the nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100

## Answer
Recursion is trivial. Here is the solution for iteration.

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        Deque<TreeNode> stack = new LinkedList<>();
        // Indicate if right node has been popped/visited.
        TreeNode lastVisit = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            
            // Don't pop first
            TreeNode node = stack.peek();
            
            // add to res if node has no right, 
            // or right has been visited in previous run.
            if (node.right == null || node.right == lastVisit) {
                stack.pop();
                
                // mark as node is visited
                lastVisit = node;
                res.add(node.val);
            } else {
                root = node.right;
            }
        }
        return res;
    }
}
```
