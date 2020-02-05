## [637. Valid Word Abbreviation](https://www.lintcode.com/problem/valid-word-abbreviation/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a non-empty string word and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as `"word"` contains only the following valid abbreviations:

`["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]`

Example
Example 1:

```
Input : s = "internationalization", abbr = "i12iz4n"
Output : true
```

Example 2:

```
Input : s = "apple", abbr = "a2e"
Output : false
```

Notice
- Notice that only the above abbreviations are valid abbreviations of the string word. Any other string is not a valid abbreviation of word.

## Answer
### Method 1 - Two Pointer - :rabbit: 330ms (70%)

```java
public class Solution {
    /**
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        // write your code here
        if (word == null || word.length() == 0) return false;
        
        int i = 0, j = 0;
        int lenw = word.length(), lena = abbr.length();
        
        while (i < lena) {
            char c = abbr.charAt(i);
            if (c > '0' && c <= '9') {     // ignore num start with `0`
                int res = 0;
                while (i < lena && Character.isDigit(abbr.charAt(i))) {
                    res = res * 10 + abbr.charAt(i) - '0';
                    i++;
                }
                j = j + res;
            } else {
                if (j >= lenw || c != word.charAt(j++)) {
                    //System.out.println(i+","+j);
                    return false;
                }
                i++;
            }
        }
        
        return i == lena && j == lenw;
    }
}
```

package com.weltond.tree;

/**
 * @author weltond
 * @project LeetCode
 * @date 1/25/2019
 */

/*
    Idea:
        1. Find the target in the BST (recursion)
            a. cur.val > key, then go left
            b. cur.val < key, then go right
            c. cur.val = key, find the target
        2. Delete target from tree.
            a. current node doesn't have left and right child
            b. current node only has left or right child
            c. current node has both left and right child
 */
public class LC450DeleteNodeInBST {
    // replace the Node with the right most min node when the Node has both left and right child
    // Time = O(height), Space = O(height)
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    public TreeNode findMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int val) {
        this.val = val;
    }
}

/**
Implementation 2: we DONOT swap value, but swap NODE
*/
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        
        if (root.val > key) root.left = deleteNode(root.left, key);
        else if (root.val < key) root.right = deleteNode(root.right, key);
        else {
            // if no right and left 
            //if (root.right == null && root.left == null) return null;
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            
            // has two children
            TreeNode cur = root.right;
            TreeNode prev = root;
            while (cur.left != null) {
                prev = cur;
                cur = cur.left;
            }
            // we swap node instead of value!
            if (prev != root) {
                prev.left = cur.right;
                cur.right = root.right;
            }
            cur.left = root.left;
            
            
            return cur;
        }
        
        return root;
    }
}
