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

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String s = dfs(root);   // [1,2,3,null,null,4,5] ==> 1',2,3',4,5

        return s;
    }
    
    private String dfs(TreeNode root) {
        if (root == null) return "/";
        
        if (root.left == null && root.right == null) return root.val + "";
        
        
        return root.val + "'," + dfs(root.left) + "," + dfs(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        
        return helper(strs);
    }
    
    int idx = 0;
    private TreeNode helper(String[] data) {
        String val = data[idx++];
        if (val.equals("/")) return null;
        
        // leaf
        if (!val.endsWith("'")) {
            return new TreeNode(Integer.parseInt(val));
        }
        
        String rootStr = val.split("'")[0];
        TreeNode root = new TreeNode(Integer.parseInt(rootStr));
        
        root.left = helper(data);
        root.right = helper(data);
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 5ms (100%)
public class Codec {
    // input: [1,2,3,null,null,4,5]
    
    StringBuilder sb = new StringBuilder();
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        dfs(root);
        //System.out.println(sb.toString());    // ===> 1,2,null,null,3,4,null,null,5,null,null,
        return sb.toString();
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            sb.append("null").append(",");
            return;
        }

        sb.append(root.val).append(",");

        dfs(root.left);
        dfs(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] list = data.split(",");

        return dfs(list);
    }

    int idx = 0;
    private TreeNode dfs(String[] list) {
        if (list[idx].equals("null")) {
            idx++;
            return null;
        }

        int val = Integer.valueOf(list[idx++]);
        TreeNode root = new TreeNode(val);
        root.left = dfs(list);
        root.right = dfs(list);

        return root;
    }
}

// 18ms (45.47%)
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String s = dfs(root);

        return s;
    }

    private String dfs(TreeNode root) {
        if (root == null) {
            return "/";
        }

        if (root.left == null && root.right == null) return root.val + "";

        String left = dfs(root.left);
        String right = dfs(root.right);

        return root.val + "'," + left + "," + right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] list = data.split(",");

        return dfs(list);
    }

    int idx = 0;
    private TreeNode dfs(String[] list) {
        // null
        if (list[idx].equals("/")) {
            idx++;
            return null;
        }

        // leaf
        if (!list[idx].endsWith("'")) {
            int val = Integer.valueOf(list[idx++]);
            return new TreeNode(val);
        }

        // non-leaf
        int val = Integer.valueOf(list[idx++].split("'")[0]);
        TreeNode root = new TreeNode(val);
        root.left = dfs(list);
        root.right = dfs(list);

        return root;
    }
}

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
