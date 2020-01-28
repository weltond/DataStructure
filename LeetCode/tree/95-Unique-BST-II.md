## [95. Unique Binary Search Trees II](https://leetcode.com/problems/unique-binary-search-trees-ii/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

```
Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```

## Answer
### Method 1 - Two Pointer - :rabbit: 330ms (70%)

### Wrong Solution

- **Wrong** because it doesn't take the BST property into consideration.
- The results of the following codes are: `[[1,null,2,null,3],[1,null,3,2],[2,1,null,null,3],[2,null,3,1],[3,1,null,null,2],[3,2,null,1]]`, which has two wrong solution `[2,null,3,1],[3,1,null,null,2]` while it should be `[2,1,3]`.

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
    boolean[] used;
    public List<TreeNode> generateTrees(int n) {
        used = new boolean[n + 1];
        
        return dfs(n,0);
    }
    
    private List<TreeNode> dfs(int n, int lvl) {
        List<TreeNode> res = new ArrayList();
        for (int i = 1; i <= n; i++) {
            if (used[i]) continue;
            used[i] = true;
            
            
            List<TreeNode> ret = dfs(n, lvl + 1);
            if (ret.size() == 0) {
                res.add(new TreeNode(i));
                used[i] = false;
                continue;
            }
            for (TreeNode node : ret) {
                TreeNode root = new TreeNode(i);
                if (node.val < i) {
                    root.left = node;
                } else {
                    root.right = node;
                }
                res.add(root);
            }
            System.out.println("==");
            used[i] = false;
        }
        
        return res;
    }
}
```
### Old Post

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
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        return gen(1, n);
    }
    
    public List<TreeNode> gen(int start, int end) {
        List<TreeNode> all = new LinkedList<>();
        
        if (start > end) {
            all.add(null);
            return all;
        }
        
        // pick a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is choosen to be a root
            List<TreeNode> left = gen(start, i - 1);
            // all possible right subtrees
            List<TreeNode> right = gen(i + 1, end);
            
            // connect left and right trees to the root i
            for (TreeNode l : left) {
                for (TreeNode r: right) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = l;
                    cur.right = r;
                    all.add(cur);
                }
            }
        }
        
        return all;
    }
}
```
