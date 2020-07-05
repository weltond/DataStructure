## [653. Two Sum IV - Input is a BST](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/)
![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:

```
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
```

Example 2:

```
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
```

## Answer
### Methdo 2 - DFS - 
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
    Set<Integer> set = new HashSet();
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, k);
    }
    
    public boolean dfs(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        
        return dfs(root.left, k) || dfs(root.right, k);
    }
}

```
### Method 1 - DFS + BS - :rocket: 2ms (95.04%)
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
    List<Integer> l = new ArrayList();
    public boolean findTarget(TreeNode root, int k) {
        dfs(root);
        int[] arr = new int[l.size()];
        for (int i = 0, len = l.size(); i < len; i++) {
            arr[i] = l.get(i);
        }
        
        return find(arr, k);
    }
    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        l.add(root.val);
        dfs(root.right);
    }
    
    private boolean find(int[] arr, int k) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == k) return true;
            else if (sum < k) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
```
