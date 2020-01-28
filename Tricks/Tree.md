# Tree Related Problems

* BST
  - When dealing with BST, always remember to use `start` and `end` to make sure BST is validated.
  
### [95. Unique BST II](https://github.com/weltond/DataStructure/blob/master/LeetCode/tree/95-Unique-BST-II.md)
- **SHOULN'T** use boolean array as the solution because we need to take **BST** into consideration.
- Signature is `dfs(int start, int end)`. Base case is `start > end`.
