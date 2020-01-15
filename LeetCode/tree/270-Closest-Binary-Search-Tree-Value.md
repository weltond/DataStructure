## [270. Closest Binary Search Tree Value](https://www.cnblogs.com/grandyang/p/5237170.html)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Example1

```
Input: root = {5,4,9,2,#,8,10} and target = 6.124780
Output: 5
Explanation：
Binary tree {5,4,9,2,#,8,10},  denote the following structure:
        5
       / \
     4    9
    /    / \
   2    8  10
```

Example2

```
Input: root = {3,2,4,1} and target = 4.142857
Output: 4
Explanation：
Binary tree {3,2,4,1},  denote the following structure:
     3
    / \
  2    4
 /
1
```

- Notice: Given target value is a floating point. You are guaranteed to have only one unique value in the BST that is closest to the target.

## Answer

### Method 2 - DFS - :turtle: 302ms (39.80%)
- O(n)
```java
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
     double res = Integer.MAX_VALUE;
     int ret = 0;
    public int closestValue(TreeNode root, double target) {
        // write your code here
        dfs(root, target);
        
        return ret;
    }
    
    private void dfs(TreeNode root, double target) {
        if (root == null) return;
        
        if (res > Math.abs(root.val - target)) {
            res = Math.abs(root.val - target);
            ret = root.val;
        }
        
        if (target > root.val) {
            dfs(root.right, target);
        } else if (target < root.val) {
            dfs(root.left, target);
        }
    }
}
```

### Method 1 - DFS - :rocket: 277ms (87.20%)
- O(height)
```java
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        if (root == null) {
            return 0;
        }
        
        TreeNode lowerNode = lower(root, target);
        TreeNode upperNode = upper(root, target);
        
        if (lowerNode == null) {
            return upperNode.val;
        }
        
        if (upperNode == null) {
            return lowerNode.val;
        }
        
        if (target - lowerNode.val > upperNode.val - target) {
            return upperNode.val;
        }
        
        return lowerNode.val;
    }
    
    private TreeNode lower(TreeNode root, double target) {
        if (root == null) return null;
        
        if (root.val > target) {
            return lower(root.left, target);
        }
        
        // val < target
        TreeNode ret = lower(root.right, target);
        
        if (ret != null) {
            return ret;
        }
        
        return root;
    } 
    
    private TreeNode upper(TreeNode root, double target) {
        if (root == null) return null;
        
        if (root.val < target) {
            return upper(root.right, target);
        }
        
        // val > target
        TreeNode ret = upper(root.left, target);
        
        if (ret != null) {
            return ret;
        }
        
        return root;
    } 
}
```
