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
### Method 2 - Morris - :turtle: 3023ms (5.20%)

```java
public class BSTIterator {
    TreeNode pre;
    TreeNode current;
    /*
     * @param root: The root of binary tree.
     */
    public BSTIterator(TreeNode root) {
        current = root;
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        // write your code here
        return current != null;
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        TreeNode res = null;
        while (current != null) {
            if (current.left == null) {     // current has reached the left most node
            	res = current;              // find the required node
            	current = current.right;    // update current and break
            	break;
            } else {
            	pre = current.left;
            	while (pre.right != null && pre.right != current) {
            		pre = pre.right;
            	}
            	if (pre.right == null) {    // pre.right has not yet linked to its successor
            		pre.right = current;    // link to current
            		current = current.left; // update current and continue;
            	} else {
            		pre.right = null;       // pre.right = current, therefore, set it to null to restore the tree
    	        	res = current;          // find the required node.
    	        	current = current.right;    // update the current and break;
    	        	break;
            	}
            }
        }
        return res;
    }
}
```

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
