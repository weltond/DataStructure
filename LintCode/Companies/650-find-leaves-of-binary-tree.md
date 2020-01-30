## [650. Find Leaves of Binary Tree](https://www.lintcode.com/problem/find-leaves-of-binary-tree/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example1

```
Input: {1,2,3,4,5}
Output: [[4, 5, 3], [2], [1]].
Explanation:

    1
   / \
  2   3
 / \     
4   5    
```

Example2

```
Input: {1,2,3,4}
Output: [[4, 3], [2], [1]].
Explanation:

    1
   / \
  2   3
 /
4 
```

## Answer
### Method 2 - DFS - :rabbit: 2468ms (33.40%)

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
    /*
     * @param root: the root of binary tree
     * @return: collect and remove all leaves
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        // write your code here
        List<List<Integer>> res = new LinkedList();
        
        getDepth(root, res);
        
        return res;
    }
    
    private int getDepth(TreeNode root, List<List<Integer>> res) {
        if (root == null) return 0;
        
        int idx = Math.max(getDepth(root.left, res), getDepth(root.right, res));
        
        if (idx == res.size()) {
            res.add(new ArrayList());    
        }
        
        res.get(idx).add(root.val);
        
        return idx + 1;
    }
}
```

### Method 1 - DFS + HashMap - :turtle: 3309ms (6.20%)

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
    /*
     * @param root: the root of binary tree
     * @return: collect and remove all leaves
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        // write your code here
        Map<Integer, List<Integer>> map = new HashMap();
        
        getDepth(root, map);
        
        List<List<Integer>> res = new LinkedList();
        
        for (int key : map.keySet()) {
            res.add(new ArrayList(map.get(key)));
        }
        
        return res;
    }
    
    private int getDepth(TreeNode root, Map<Integer, List<Integer>> map) {
        if (root == null) return 0;
        
        int depth = 1 + Math.max(getDepth(root.left, map), getDepth(root.right, map));
        
        map.computeIfAbsent(depth, o -> new ArrayList()).add(root.val);
        
        return depth;
    }
}
```
