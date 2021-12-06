## [173. Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling `next()` will return the next smallest number in the BST.

 

Example:

```
BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
```

Note:

- `next()` and `hasNext()` should run in average **O(1)** time and uses **O(h)** memory, where h is the height of the tree.
- You may assume that `next()` call will always be valid, that is, there will be at least a next smallest number in the BST when `next()` is called.

## Answer
### Method 1 - Stack - :rabbit: 16ms (71.90%)
- Given the hint: "**average** **O(1)** time and **O(h)** space", meaning we can sometimes use O(n) or O(h) time to get next node. We all know that, once you get a Node, in order to get the smallest, you need to get its left-most node. So we can have a pointer points to the left-most node. But how do we find its parent? ===> **STACK**.
- Use stack to store nodes.
- At first, push all the left nodes into stack.
- Then, after popping top (which is current smallest one), go to its right, and store all of its left children if exists.

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
class BSTIterator {
    Deque<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new LinkedList();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode n = stack.pop();
        int ret = n.val;
        
        n = n.right;
        
        while (n != null) {
            stack.push(n);
            n = n.left;
        }
        
        return ret;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```
