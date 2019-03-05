## Solve Tree Problems Recursively

 - ["Top-Down" solution](#top-down-solution)
 - ["Bottom-Up" solution](#bottom-up-solution)
 - [Conclusion](#conclusion)
 
As we know, a tree can be defined recursively. Recursion is one of the nature features of a tree.

Therefore, many tree problems can be solved recursively. **For each level, we can only focus on the problem within one single node and call
the function recursively to solve its children.**

### Top Down Solution
"Top-down" means that in eacah recursion level, we will visit the node first to come up with some values, and pass these values to its children
when calling the function recursively.

So the "top-down" solution can be considered as kind of **preorder** traversal. To be specific, the recursion function `top_down(root, params)` works 
like this:

```
1. return specific value for null node
2. update the answer if needed                      // answer <-- params
3. left_ans = top_down(root.left, left_params)      // left_params <-- root.val, params
4. right_ans = top_down(root.right, right_params)   // right_params <-- root.val, params
5. return the answer if needed                      // answer <-- left_ans, right_ans
```

### Bottom Up Solution
### Conclusion
