## [938. Range Sum of BST](https://leetcode.com/problems/range-sum-of-bst/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

 

Example 1:

<img width="205" alt="image" src="https://user-images.githubusercontent.com/9000286/154623031-42660144-60ba-4406-889b-58dc78f7c695.png">

```
Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
```

Example 2:

```
Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
``` 

**Constraints**:

- The number of nodes in the tree is in the range [1, 2 * 104].
- 1 <= Node.val <= 105
- 1 <= low <= high <= 105
- All Node.val are unique.

## Answers
### Method 2 - BFS

```java
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (L <= node.val && node.val <= R)
                    ans += node.val;
                if (L < node.val)
                    stack.push(node.left);
                if (node.val < R)
                    stack.push(node.right);
            }
        }
        return ans;
    }
}
```

### Method 1 - DFS

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
public class Solution {
    /**
     * @param root: the root node
     * @param L: an integer
     * @param R: an integer
     * @return: the sum
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        // write your code here.
    
        if (root == null) return 0;
        

        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        } else if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        } else {
            return rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R) + root.val;
        }
    }
}

class Solution {
    // ========== Method 1: DFS =============
    // Approach 1: 0ms
    public int rangeSumBST(TreeNode root, int l, int r) {
        if (root == null) return 0;
        
        int res = 0;
        
        if (l <= root.val) {
            res += root.val + rangeSumBST(root.left, l, r);
        }
        if (r >= root.val) {
            res += root.val + rangeSumBST(root.right, l, r);
        }
        
        return res - root.val;
    }
    
    // 0ms
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        
        
        if (low <= root.val && root.val <= high) {
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        }
        
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        
        return -1;
    }
    
    // Approach 2: 1ms(61.11%)
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val <= R && root.val >= L) {
            return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);
        }
        return rangeSumBST(root.right, L, R)+rangeSumBST(root.left, L, R);
    }
    
    // Approach 3
    int ans;
    public int rangeSumBST(TreeNode root, int L, int R) {
        ans = 0;
        dfs(root, L, R);
        return ans;
    }

    public void dfs(TreeNode node, int L, int R) {
        if (node != null) {
            if (L <= node.val && node.val <= R)
                ans += node.val;
            if (L < node.val)
                dfs(node.left, L, R);
            if (node.val < R)
                dfs(node.right, L, R);
        }
    }
}
```
