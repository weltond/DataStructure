## [662. Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/description/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.

Example 1:
<img width="360" alt="image" src="https://user-images.githubusercontent.com/9000286/207775329-26c00890-a4a7-4e22-822e-cfa722dce292.png">

```
Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
```

Example 2:
<img width="443" alt="image" src="https://user-images.githubusercontent.com/9000286/207775373-8896909b-9de2-4735-a954-2c3082462964.png">

```
Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
```

Example 3:
<img width="287" alt="image" src="https://user-images.githubusercontent.com/9000286/207775290-854ff006-61ce-467d-85f3-35a7c3db21a6.png">

```
Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).
``` 

**Constraints**:

- The number of nodes in the tree is in the range [1, 3000].
- -100 <= Node.val <= 100

## Answer
### Method 1 - DFS
#### Approach 1 :rabbit: 3ms (68.5%)
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
    // <lvl, min>
    Map<Integer, Integer> lvlMinMap = new HashMap();  

    int res = Integer.MIN_VALUE; 
    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 0, 0);

        return res;
    }

    private void dfs(TreeNode root, int pos, int lvl) {
        if (root == null) return;
        
        // pre order -> meaning we will always get left most node for each level first
        lvlMinMap.computeIfAbsent(lvl, k -> pos);

        res = Math.max(res, pos - lvlMinMap.get(lvl) + 1);

        dfs(root.left, pos * 2, lvl + 1);
        dfs(root.right, pos * 2 + 1, lvl + 1);
    }
}
```

#### Approach 2 - 🚀 1ms (100%)
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
    int res = 1;    // initialization to 1 to include case where only 1 node
    Map<Integer, Integer> map = new HashMap();  // <lvl, min val>
    public int widthOfBinaryTree(TreeNode root) {
        // corner case is needed here
        if (root == null) return 0;
        
        dfs(root, 0, 0);

        return res;
    }

    private void dfs(TreeNode root, int idx, int lvl) {
        if (root== null) return;

        if (!map.containsKey(lvl)) {
            map.put(lvl, idx);
        } else {
            res = Math.max(idx - map.get(lvl) + 1, res);
        }

        dfs(root.left, idx * 2, lvl + 1);
        dfs(root.right, idx * 2 + 1, lvl + 1);
    }
}
```
