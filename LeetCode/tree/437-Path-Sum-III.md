## [437. Path Sum III](https://leetcode.com/problems/path-sum-iii/)

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:
```
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
```

## Answer
#### Approach 2 - :rabbit: 11ms (45.99%)
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
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int dfs(TreeNode root, int sum) {
        if (root == null) return 0;
        
        return (root.val == sum ? 1 : 0) + dfs(root.left, sum - root.val) + dfs(root.right, sum - root.val);
    }
}
```
#### Approach 1 - :rocket: 1ms （100%）
```java
class Solution {
    // ======= Method 2: DP =======
    // 1ms (100%)
    public int minSwap(int[] a, int[] b) {
        int[] swap = new int[a.length];
        int[] keep = new int[a.length];
        Arrays.fill(swap, Integer.MAX_VALUE);
        Arrays.fill(keep, Integer.MAX_VALUE);
        swap[0] = 1;
        keep[0] = 0;
        
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1] && b[i] > b[i - 1]) {
                swap[i] = swap[i - 1] + 1;
                keep[i] = keep[i - 1];
            }
            
            if (a[i] > b[i - 1] && b[i] > a[i - 1]) {
                swap[i] = Math.min(swap[i], keep[i - 1] + 1);
                keep[i] = Math.min(keep[i], swap[i - 1]);
            }
        }
        
        return Math.min(swap[a.length - 1], keep[a.length - 1]);
    }
}
```
### Approach 1 :turtle: 52ms (5.05%)
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
    // ========== Approach 1: 52ms (5.05%) =========
    int res = 0;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        
        dfs(root, sum);
        
        return res;
    }
    
    private List<Integer> dfs(TreeNode root, int sum) {
        List<Integer> list = new ArrayList();
        if (root == null) return list;
        
        List<Integer> l = dfs(root.left, sum);
        List<Integer> r = dfs(root.right, sum);
        
        int tmp = root.val;
        
        if (tmp == sum) {
            res++;
        }
        list.add(tmp);
        
        for (int i : l) {
            if (tmp + i == sum) {
                res++;
            }
            list.add(tmp + i);
        }
        for (int i : r) {
            if (tmp + i == sum) {
                res++;
            }
            list.add(tmp + i);
        }
        
        
        return list;
    }
}
```
