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

Example 3
<img width="155" alt="image" src="https://user-images.githubusercontent.com/9000286/154870776-f1fa68aa-814e-4e98-afbe-2b36a04367d8.png">

```
Input: root = [4,2,5,1,3], target = 3.714286
Output: 4
```

- Notice: Given target value is a floating point. You are guaranteed to have only one unique value in the BST that is closest to the target.

## Answer
### Method 2 - BFS 
Time: O(H), Space: O(1)

```java
class Solution {
  public int closestValue(TreeNode root, double target) {
    int val, closest = root.val;
    while (root != null) {
      val = root.val;
      closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;
      root =  target < root.val ? root.left : root.right;
    }
    return closest;
  }
}
```
### Method 1 - DFS - 0ms

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
    int closest ;
    public int closestValue(TreeNode root, double target) {
        if (root == null) return 0;
        
        closest = root.val;
        
        dfs(root, target);
        
        return closest;
    }
    
    private void dfs(TreeNode root, double target) {
        if (root == null) return;
        
        closest = Math.abs(closest - target) > Math.abs(target - root.val) ? root.val : closest;

        if (root.val > target) {
            dfs(root.left, target);
        } else if (root.val < target) {
            dfs(root.right, target);
        }
    }
    
}
```

### Method 0 - Iterative pre-order
Time: O(K) in average, Space: O(H).

```java
class Solution {
  public int closestValue(TreeNode root, double target) {
    LinkedList<TreeNode> stack = new LinkedList();
    long pred = Long.MIN_VALUE;

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.add(root);
        root = root.left;
      }
      root = stack.removeLast();

      if (pred <= target && target < root.val)
        return Math.abs(pred - target) < Math.abs(root.val - target) ? (int)pred : root.val;

      pred = root.val;
      root = root.right;
    }
    return (int)pred;
  }
}
```
## Old Post
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
