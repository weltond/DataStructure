## [111. Minimum Depth of binary tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

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
