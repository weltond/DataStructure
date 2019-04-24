// https://leetcode.com/problems/serialize-and-deserialize-bst/

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
        if (data == null || data.length() == 0) return null;
        String[] s = data.split(",");
        // TreeNode root = m1(s, 0, s.length - 1, s.length);
        //TreeNode root = m2(s, Integer.MIN_VALUE, Integer.MAX_VALUE, s.length);
        //TreeNode root = myMethod3(s, s.length);
        TreeNode root = m3(s, s.length);
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
