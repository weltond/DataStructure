## [449. Serialize and Deserialize BST](https://leetcode.com/problems/serialize-and-deserialize-bst/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a **binary search tree**. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

**The encoded string should be as compact as possible.**

- Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

## Answer
### Method 1 - DFS - :rocket: 5ms (93.59%)

- Not a ideal solution since I used global variables here.

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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        
        serUtil(root, sb);
        
        // for [2,1,3], string is like: 2,1,/,/,3,/,/
        return sb.toString();
    }

    private void serUtil(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("/").append(",");
            return;
        }
        
        sb.append(root.val).append(",");
        
        serUtil(root.left, sb);
        serUtil(root.right, sb);
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        
        return deUtil(strs);
    }
    
    int lvl = 0;
    private TreeNode deUtil(String[] strs) {
        if (lvl >= strs.length) return null;
        
        TreeNode root = null;
        String s = strs[lvl++];
        
        // leaf node
        if (s.equals("/")) return null;

        root = new TreeNode(Integer.parseInt(s));
        
        root.left = deUtil(strs);
        root.right = deUtil(strs);
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

## Old Post
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
public class Codec {
    
    StringBuilder sb = new StringBuilder();
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return sb.toString();
        
        sb.append(root.val).append(",");
        serialize(root.left);
        serialize(root.right);
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //System.out.println(data);
        if (data == null || data.length() == 0) return null;
        String[] s = data.split(",");
        // TreeNode root = m1(s, 0, s.length - 1, s.length);
        //TreeNode root = m2(s, Integer.MIN_VALUE, Integer.MAX_VALUE, s.length);
        //TreeNode root = myMethod3(s, s.length);
        //TreeNode root = m3(s, s.length);
        TreeNode root = bfs(s);

        return root;
    }
    private TreeNode bfs(String[] data) {
        int rootVal = Integer.valueOf(data[0]);
        TreeNode root = new TreeNode(rootVal);
        Deque<TreeNode> s = new LinkedList();
        s.push(root);
        
        int idx = 1;
        while (idx < data.length) {
            int v = Integer.valueOf(data[idx++]);
            
            TreeNode tmp = null;
            while (!s.isEmpty() && s.peek().val < v) {
                tmp = s.pop();
            }
            
            if (tmp == null) {
                tmp = s.peek();
                tmp.left = new TreeNode(v);
                s.push(tmp.left);
            } else {
                tmp.right = new TreeNode(v);
                s.push(tmp.right);
            }
        }
        
        return root;
    }
    // Time = O(n) space = O(height)
    public TreeNode m3(String[] data, int size) {
        int key = Integer.valueOf(data[0]);
        TreeNode root = new TreeNode(key);
        Stack<TreeNode> s = new Stack();
        s.push(root);
        
        for (int i = 1; i < size; i++) {
            TreeNode tmp = null;
            int cVal = Integer.valueOf(data[i]);
            
            while (!s.isEmpty() && cVal > s.peek().val) {
                tmp = s.pop();
            }
            
            if (tmp != null) {
                tmp.right = new TreeNode(cVal);
                s.push(tmp.right);
            } else {
                tmp = s.peek();
                tmp.left = new TreeNode(cVal);
                s.push(tmp.left);
            }
        }
        
        return root;
    }
    public TreeNode myMethod3(String[] data, int size) {
        int key = Integer.valueOf(data[0]);
        TreeNode root = new TreeNode(key);
        Stack<TreeNode> s = new Stack();
        s.push(root);
        
        for (int i = 1; i < size; i++) {
            TreeNode tmp = null;
            int cVal = Integer.valueOf(data[i]);
            
            if (cVal < s.peek().val) {
                tmp = s.peek();
                tmp.left = new TreeNode(cVal);
                s.push(tmp.left);
            } else {
                while(!s.isEmpty() && cVal > s.peek().val) {
                    tmp = s.pop();
                }
                tmp.right = new TreeNode(cVal);
                s.push(tmp.right);
            }      
        }  
        return root;
    }
    
    
    int idx = 0;
    // Time = O(n)
    public TreeNode m2(String[] data, int min, int max, int size) {
        if (idx >= size) return null;
        
        TreeNode root = null;
        
        int key = Integer.valueOf(data[idx]);
        if (key > min && key < max) {
            root = new TreeNode(key);
            idx++;
            
            if (idx < size) {
                root.left = m2(data, min, key, size);
                root.right = m2(data, key, max, size);
            }
        }
        
        return root;
    }
    
    // Time = O(n^2)
    
    public TreeNode m1(String[] data, int start, int end, int size) {
        if (start > end || idx > size) return null;
        int v = Integer.valueOf(data[idx]);
        
        TreeNode root = new TreeNode(v);
        idx++;
        
        if(start == end) return root;
        
        // search for the first element greater than root
        int i;
        for (i = start; i <= end; i++) {
            if (Integer.valueOf(data[i])> root.val) break;
        }
        
        root.left = m1(data, idx, i - 1, size);
        root.right = m1(data, i, end, size);
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```
