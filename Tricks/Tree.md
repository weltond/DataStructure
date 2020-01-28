# Tree Related Problems

* BST
  - When dealing with BST, always remember to use `start` and `end` to make sure BST is validated.
  
### [95. Unique BST II](https://github.com/weltond/DataStructure/blob/master/LeetCode/tree/95-Unique-BST-II.md)
- **SHOULN'T** use boolean array as the solution because we need to take **BST** into consideration.
- Signature is `dfs(int start, int end)`. Base case is `start > end`.

### [98. Validate BST](https://github.com/weltond/DataStructure/blob/master/LeetCode/tree/Lc98ValidateBST.java) 
#### In-order
- Use `pre` as `Long.MIN_VALUE` and inorder traverse.
#### Min, Max
- Use `min` and `max` to validate current root value.

### [99. Recover BST](https://github.com/weltond/DataStructure/blob/master/LeetCode/tree/99-Recover-Binary-Search-Tree.md) 
#### In-order
#### Morris Traversal
