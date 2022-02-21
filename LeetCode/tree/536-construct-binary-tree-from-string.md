## [536. Construct Binary Tree from String](https://leetcode.com/problems/construct-binary-tree-from-string/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

 

Example 1:

<img width="393" alt="image" src="https://user-images.githubusercontent.com/9000286/155030370-efe30838-b3e7-4911-bf49-1bd68a5f04fb.png">

```
Input: s = "4(2(3)(1))(6(5))"
Output: [4,2,6,3,1,5]
```

Example 2:

```
Input: s = "4(2(3)(1))(6(5)(7))"
Output: [4,2,6,3,1,5,7]
```

Example 3:

```
Input: s = "-4(2(3)(1))(6(5)(7))"
Output: [-4,2,6,3,1,5,7]
``` 

**Constraints**:

- 0 <= s.length <= 3 * 104
- s consists of digits, '(', ')', and '-' only.

## Answers
### Method 2 - Stack

```java
public class Solution {
    public TreeNode str2tree(String s) {
        Stack<TreeNode> stack = new Stack<>();
        for(int i = 0, j = i; i < s.length(); i++, j = i){
            char c = s.charAt(i);
            if(c == ')')    stack.pop();
            else if(c >= '0' && c <= '9' || c == '-'){
                while(i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
                TreeNode currentNode = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
                if(!stack.isEmpty()){
                    TreeNode parent = stack.peek();
                    if(parent.left != null)    parent.right = currentNode;
                    else parent.left = currentNode;
                }
                stack.push(currentNode);
            }
        }
        return stack.isEmpty() ? null : stack.peek();
    }
}
```

### Method 1 - Recursion - 3ms (92.84%)
#### Approach 2
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
    public int idx = 0, len = 0;
    public TreeNode str2tree(String s) {
        len = s.length();
        
        return dfs(s);
    }
    
    private TreeNode dfs(String s) {
        if (len == idx) return null;
        
        // get value
        int sign = 1;
        if (s.charAt(idx) == '-') {
            sign = -1;
            idx++;
        } 
        
        int val = 0;
        while (idx < len && Character.isDigit(s.charAt(idx))) {
            val = val * 10 + (s.charAt(idx++) - '0');
        }
        
        TreeNode root = new TreeNode(val * sign);
        
        // construct left node
        if (idx < len && s.charAt(idx) == '(') {
            idx++;
            root.left = dfs(s);
        }
        
        // construct right node
        if (root.left != null && idx < len && s.charAt(idx) == '(') {
            idx++;
            root.right = dfs(s);
        }
        
        // don't forget to increase index when seeing ')'
        if (idx < len && s.charAt(idx) == ')') {
            idx++;
        }
        
        return root;
    }
}
```
#### Approach 1

```java
public class Solution {
    
    int i = 0;
    
    public TreeNode str2tree(String s) 
    {
        if (s == null || s.length() == 0) { return null; }
        return helper(s.toCharArray());
    }
    
    private TreeNode helper(char[] s)
    {
        // done
        if (i == s.length) { return null; }
        
        // extract number
        StringBuilder num = new StringBuilder();
        while (i < s.length && s[i] != '(' && s[i] != ')') { num.append(s[i]); i++; }
        
        // create new node
        TreeNode n = null;
        if (num.length() != 0)
        {
            n = new TreeNode(Integer.parseInt(num.toString()));
        }
        // check parenthesis type
        if (i < s.length && s[i] == '(')
        {
            // create left child node
            i++;
            n.left = helper(s);
            i++;
            
            if (i < s.length && s[i] == '(')
            {
                // create right child node
                i++;
                n.right = helper(s);
                i++;
            }
        }
        // if meets ')', return to parent node
        return n;
    }
}
```
