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

For instance, consider: find maximum depth of a binary tree.

We know that depth of the root node is `1`. For each node, if we know the depth of the node, we will know the depth of the children. 
Therefore, if we pass the depth of the node as a parameter when calling the function recursively, all the nodes know the depth of themselves. And for leaf nodes, we can use the depth to update the final answer.

```java
private in answer;           // don't forget to initialize answer before call maximum_depth
private void maximum_depth(TreeNode root, int depth) {
    if (root == null) return;
    
    if (root.left == null && root.right == null) {
        answer = Math.max(answer, depth);
    }
    
    maximum_depth(root.left, depth + 1);
    maximum_depth(root.right, depth + 1);
}
```

### Bottom Up Solution
"Bottom-up" is another recursion solution. In each recursion level, we will firstly call the functions recursively for all the children nodes and then come up with the answer according to the return values and the value of the root node itself.

This process can be regarded as kind of **postorder** traversal. Typically, a 'bottom-up' recursion function `bottom_up(root)` will be:

```
1. return specific value for null node
2. left_ans = bottom_up(root.left)           // call function recursively for left child
3. right_ans = bottom_up(root.right)        // call function recursively for right child
4. return answers                           // answer <-- left_ans, right_ans, root.val
```
Let's go on the previous question but using a different way of thinking: *for a single node of the tree, what will be the maximum depth of the subtree rooted at itself?*

If we know the maximum depth `l` of the subtree rooted at its left child and the maximum depth `r` of the subtree rooted at its right child, we can choose the maximum between them and plus 1 to get the maximum depth of the subtree rooted at the selected node. That is `x = max(l, r) + 1`.

It means that for each node, we can get the answer after solving the problem of its children. Therefore, we can solve this problem using **"bottom-up"** solution.

```java
public int maximum_depth(TreeNode root) {
    if (root == null) return 0;
    
    int left = maximum_depth(root.left);
    int right = maximum_depth(root.right);
    
    return Math.max(left, right) + 1;
}
```

### Conclusion

- When you meet a tree problem, ask yourself two questions:
  - Can you determine some parameters to help the node know the answer of itself?
  - Can you use these parameters and value of the node itself to determine what should be the parameters parsing to its children?
  
  If the answers are both yes, try to solve this problem using a **'top-down'** recursion solution.

- Or you can think the problem in this way: 
  - for a node in tree, if you know the answer of its children, can you calculate the answer of the node?
  
  If the answer is yes, solving the problem recursively from **"bottom-up"** might be a good way.
  

