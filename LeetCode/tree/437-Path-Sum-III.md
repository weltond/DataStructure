## [437. Path Sum III](https://leetcode.com/problems/path-sum-iii/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

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
### Approach 4 - Prefix Sum - :rocket: 3ms (100%)

An obvious hint is **must go downwards**, clearly we can take advantage of pre sum algo and apply it to tree.

Notice we need to **reset PreSum hashmap** once return back to parent.

```java
class Solution {
    // sum of nodes might greater than Integer.MAX_VALUE
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> map = new HashMap(); // <sum, cnt>
        map.put(0L, 1);
        return dfs(root, targetSum, 0, map);
    }

    private int dfs(TreeNode root, int target, long sum, Map<Long, Integer> map) {
        if (root == null) return 0;

        long cur = sum + root.val;

        int res = map.getOrDefault(cur - target, 0);

        int count = map.getOrDefault(cur, 0);
        map.put(cur, count + 1);

        res += dfs(root.left, target, cur, map) + dfs(root.right, target, cur, map);

        count = map.getOrDefault(cur, 0);
        map.put(cur, count - 1);

        return res;
    }
}
```

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
    // prefix sum
    // 3ms (100%)
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap();  // <sum, cnt>
        
        map.put(0, 1);  // for the first matching.
        
        return dfs(root, sum, 0, map);
    }
    
    private int dfs(TreeNode root, int target, int curSum, Map<Integer, Integer> preSum) {
        if (root == null) return 0;
        
        curSum += root.val;

        int res = preSum.getOrDefault(curSum - target, 0);  // get before put

        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);

        res += dfs(root.left, target, curSum, preSum) + 
            dfs(root.right, target, curSum, preSum);

        preSum.put(curSum, preSum.getOrDefault(curSum, 0) - 1);

        return res;
    }
}
```
### Approach 3 - :turtle: 14ms (11.90%)
- **Top-down**
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
    // 14ms (11.90%)
    int res = 0;
    public int pathSum(TreeNode root, int sum) {
        List<TreeNode> list = new ArrayList();
        
        dfs(root, 0, sum, list);
        
        return res;
    }
    
    private void dfs(TreeNode root, int cur, int sum, List<TreeNode> list) {
        if (root == null) return;
        
        list.add(root);
        
        cur += root.val;
        if (sum == cur) res++;
        
        int tmp = cur;
        for (int i = 0, len = list.size() - 1; i < len; i++) { // Be careful of the len. because if sum is 0, the tmp will also lead to 1 answer.
            tmp -= list.get(i).val;
            if (tmp == sum) res++;
        }
        
        dfs(root.left, cur, sum, list);
        dfs(root.right, cur, sum, list);
        
        list.remove(list.size() - 1);
    }
}
```
### Approach 2 - :rabbit: 11ms (45.99%)
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
### Approach 1 :turtle: 52ms (5.05%)
- **Bottom-Up**
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
