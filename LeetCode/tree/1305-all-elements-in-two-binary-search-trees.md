## [1305. All Elements in Two Binary Search Trees](https://leetcode.com/problems/all-elements-in-two-binary-search-trees/)

Given two binary search trees root1 and root2.

Return a list containing all the integers from both trees sorted in ascending order.

Example 1:

```
Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]
```

Example 2:

```
Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
Output: [-10,0,0,1,2,5,7,10]
```

Example 3:

```
Input: root1 = [], root2 = [5,1,7,0,2]
Output: [0,1,2,5,7]
```

Example 4:

```
Input: root1 = [0,-10,10], root2 = []
Output: [-10,0,10]
```

Example 5:

```
Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]
```

Constraints:

- Each tree has at most 5000 nodes.
- Each node's value is between [-10^5, 10^5].

## Answer
### Method 1 - DFS + Two pointers - :turtle: 766ms (5.20%)

- See other posts at 01/02/2020 have the same approach. Need to verify the reason my approach takes much time than others.

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
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new LinkedList();
        
        List<Integer> l1 = new LinkedList(), l2 = new LinkedList();
        
        dfs(root1, l1);
        dfs(root2, l2);
        
        int i = 0, j = 0, size1 = l1.size(), size2 = l2.size();
        while (i < size1 || j < size2) {
            if (i == size1) res.add(l2.get(j++));
            else if (j == size2) res.add(l1.get(i++));
            else {
                if (l1.get(i) <= l2.get(j)) {
                    res.add(l1.get(i++));
                } else {
                    res.add(l2.get(j++));
                }
            }
        }
        
        return res;
        
    }
    
    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }
}
```
