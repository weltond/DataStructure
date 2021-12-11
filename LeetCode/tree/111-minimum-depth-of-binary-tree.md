## [111. Minimum Depth of binary tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

 

Example 1:

```
Input: root = [3,9,20,null,null,15,7]
Output: 2
```
Example 2:
```
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5
``` 

**Constraints:**

- The number of nodes in the tree is in the range [0, 105].
- -1000 <= Node.val <= 1000

## Answers
### Method 2 - BFS 🚀 (0ms)
```java
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            res++;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left == null && node.right == null) {
                    return res;
                }

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }

        return res;
    }
}
```
### Method 1 - DFS 🐰 5ms (66.6%)

#### Approach 2
```java
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;
       
    }
}
```

#### Approach 1

**Note: if return MAX for null node, you should worry about null tree.**

```java
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;

        if (root.left == null && root.right == null) return 1;
        
        return Math.min(dfs(root.left), dfs(root.right)) + 1;
    }
}
```
