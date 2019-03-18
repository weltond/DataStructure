// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/


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
    /**
    1
   / \
  2   3       ====> 1',2',6,/,3',4,5
 /   / \
6   4   5
    */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // null: /
        if (root == null) return "/";
        
        // leaf: its value
        if (root.left == null && root.right == null) return String.valueOf(root.val);
        
        // otherwise: value + ''' (i.e. 7')
        String left = serialize(root.left);
        String right = serialize(root.right);
        
        return root.val + "'" + "," + left + "," + right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        List<String> list = new LinkedList();
        for (String str : strs) {
            list.add(str);
        }
        
        return helper(list);
    }
    
    private TreeNode helper(List<String> list) {
        String cur = list.get(0);
        list.remove(0);
        
        // null
        if (cur.equals("/")) {
            return null;
        }
        
        // leaf
        if (!cur.endsWith("'")) {
            return new TreeNode(Integer.parseInt(cur));
        }
        
        String rootVal = cur.split("'")[0];
        TreeNode root = new TreeNode(Integer.parseInt(rootVal));
        root.left = helper(list);
        root.right = helper(list);
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
