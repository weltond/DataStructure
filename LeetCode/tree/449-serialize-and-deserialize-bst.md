## [449. Serialize and Deserialize BST](https://leetcode.com/problems/serialize-and-deserialize-bst/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a **binary search tree**. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

**The encoded string should be as compact as possible.**

- Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

## Answer
### Method 2 - BFS -

```java
public class Codec {
    private static final String SEP = ",";
    private static final String NULL = "null";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return NULL;
        //traverse it recursively if you want to, I am doing it iteratively here
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.empty()) {
            root = st.pop();
            sb.append(root.val).append(SEP);
            if (root.right != null) st.push(root.right);
            if (root.left != null) st.push(root.left);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    // pre-order traversal
    public TreeNode deserialize(String data) {
        if (data.equals(NULL)) return null;
        String[] strs = data.split(SEP);
        Queue<Integer> q = new LinkedList<>();
        for (String e : strs) {
            q.offer(Integer.parseInt(e));
        }
        return getNode(q);
    }
    
    // some notes:
    //   5
    //  3 6
    // 2   7
    private TreeNode getNode(Queue<Integer> q) { //q: 5,3,2,6,7
        if (q.isEmpty()) return null;
        TreeNode root = new TreeNode(q.poll());//root (5)
        Queue<Integer> samllerQueue = new LinkedList<>();
        while (!q.isEmpty() && q.peek() < root.val) {
            samllerQueue.offer(q.poll());
        }
        //smallerQueue : 3,2   storing elements smaller than 5 (root)
        root.left = getNode(samllerQueue);
        //q: 6,7   storing elements bigger than 5 (root)
        root.right = getNode(q);
        return root;
    }
}
```

### Method 1 - DFS - :rocket: 5ms (93.59%)
#### Approach 4

- Use `left` and `right` position of BST property.

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
 */

public class Solution {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        ser(root, sb);
        
        return sb.toString();
    }
    
    public void ser(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        
        sb.append(root.val).append(",");
        ser(root.left, sb);
        ser(root.right, sb);
    }

    
    public TreeNode deserialize(String data) {
       String[] strs = data.split(",");
       return der(strs, 0, strs.length - 1);
    }
    
    public TreeNode der(String[] data, int left, int right) {
        if (left > right) return null;
        
        int val = Integer.parseInt(data[left]);
        TreeNode root = new TreeNode(val);

        int split = left + 1;
        while (split < data.length && Integer.parseInt(data[split]) < val) {
            split++;
        }
        root.left = der(data, left + 1, split - 1);
        root.right = der(data, split, right);
        
        return root;
    }
}
```

#### Approach 3

- Fully use the property of BST

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
        serialize(root, sb);
        return sb.toString();
    }
    
    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public TreeNode deserialize(Queue<String> q, int lower, int upper) {
        if (q.isEmpty()) return null;
        String s = q.peek();
        int val = Integer.parseInt(s);
        if (val < lower || val > upper) return null;
        q.poll();
        TreeNode root = new TreeNode(val);
        root.left = deserialize(q, lower, val);
        root.right = deserialize(q, val, upper);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

#### Approach 2

- Use Queue to replace. It can be used for both BST and normal BT.

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
        serialize(root, sb);
        return sb.toString();
    }
    
    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
        } else {
            sb.append(root.val).append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q);
    }
    
    public TreeNode deserialize(Queue<String> q) {
        String s = q.poll();
        if (s.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = deserialize(q);
        root.right = deserialize(q);
        return root;
    }
    
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

#### Approach 1
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
