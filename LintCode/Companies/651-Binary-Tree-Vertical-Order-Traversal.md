## [651. Binary Tree Vertical Order Traversal](https://www.lintcode.com/problem/binary-tree-vertical-order-traversal/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Example1

```
Inpurt:  {3,9,20,#,#,15,7}
Output: [[9],[3,15],[20],[7]]
Explanation:
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
```

Example2

```
Input: {3,9,8,4,0,1,7}
Output: [[4],[9],[3,0,1],[8],[7]]
Explanation:
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
```

## Answer
### Method 1 - DFS

### Wrong Solution
- **WRONG** because the vertical order is not garunteed.
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
     * @param root: the root of tree
     * @return: the vertical order traversal
     */
    TreeMap<Integer, List<Integer>> map;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> res = new LinkedList();
        if (root == null) return res;
        
        map = new TreeMap();
        
        dfs(root, 0);
        
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        
        return res;
    }
    
    private void dfs(TreeNode root, int idx) {
        if (root == null) return;
        
        map.computeIfAbsent(idx, o -> new ArrayList()).add(root.val);
        dfs(root.left, idx - 1);
        dfs(root.right, idx + 1);
    }
}
```
