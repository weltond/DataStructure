## [86. Binary Search Tree Iterator](https://www.lintcode.com/problem/binary-search-tree-iterator/description?_from=ladder&&fromId=130)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

Design an iterator over a binary search tree with the following rules:

- Elements are visited in ascending order (i.e. an in-order traversal)
- `next()` and `hasNext()` queries run in O(1) time in average.

Example 1

```
Input:  {10,1,11,#,6,#,12}
Output:  [1, 6, 10, 11, 12]
Explanation:
The BST is look like this:
  10
  /\
 1 11
  \  \
   6  12
You can return the inorder traversal of a BST [1, 6, 10, 11, 12]
```

Example 2

```
Input: {2,1,3}
Output: [1,2,3]
Explanation:
The BST is look like this:
  2
 / \
1   3
You can return the inorder traversal of a BST tree [1,2,3]
```

- Challenge: Extra memory usage O(h), h is the height of the tree.

- Super Star: Extra memory usage O(1)

Notice
- Notice that only the above abbreviations are valid abbreviations of the string word. Any other string is not a valid abbreviation of word.

## Answer
### Method 1 - Stack - :turtle: 2671ms (8.20%)

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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */


public class BSTIterator {
    Deque<TreeNode> stack;
    /*
    * @param root: The root of binary tree.
    */public BSTIterator(TreeNode root) {
        stack = new LinkedList();
        
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        if (stack.isEmpty()) return null;
        
        TreeNode ret = stack.pop();
        TreeNode tmp = ret.right;
        
        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.left;
        }
        
        return ret;
    }
}
```
