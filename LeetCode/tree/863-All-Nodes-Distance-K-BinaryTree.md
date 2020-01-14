## [863. All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 

Example 1:

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.

Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
```

Note:

- The given tree is non-empty.
- Each node in the tree has unique values `0 <= node.val <= 500`.
- The target node is a node in the tree.
- `0 <= K <= 1000`.

## Answer
### Method 2 - DFS -

```java
class Solution {
    List<Integer> ans;
    TreeNode target;
    int K;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ans = new LinkedList();
        this.target = target;
        this.K = K;
        dfs(root);
        return ans;
    }

    // Return vertex distance from node to target if exists, else -1
    // Vertex distance: the number of vertices on the path from node to target
    public int dfs(TreeNode node) {
        if (node == null)
            return -1;
        else if (node == target) {
            subtree_add(node, 0);
            return 1;
        } else {
            int L = dfs(node.left), R = dfs(node.right);
            if (L != -1) {
                if (L == K) ans.add(node.val);
                subtree_add(node.right, L + 1);
                return L + 1;
            } else if (R != -1) {
                if (R == K) ans.add(node.val);
                subtree_add(node.left, R + 1);
                return R + 1;
            } else {
                return -1;
            }
        }
    }

    // Add all nodes 'K - dist' from the node to answer.
    public void subtree_add(TreeNode node, int dist) {
        if (node == null) return;
        if (dist == K)
            ans.add(node.val);
        else {
            subtree_add(node.left, dist + 1);
            subtree_add(node.right, dist + 1);
        }
    }
}
```

### Method 1 - Annotate Parent (DFS + BFS) - :rabbit: 3ms  (77.71%)

#### Approach 2

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
class Solution {
    Map<TreeNode, TreeNode> map = new HashMap();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new LinkedList();
        if (root == null || target == null) return res;
        
        getParent(root, null);
        
        Queue<TreeNode> q = new LinkedList();
        q.offer(target);
        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        
        while (!q.isEmpty() && K-- > 0) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                
                if (n.left != null && !seen.contains(n.left)) {
                    seen.add(n.left);
                    q.offer(n.left);
                }
                if (n.right != null && !seen.contains(n.right)) {
                    seen.add(n.right);
                    q.offer(n.right);
                }
                TreeNode par = map.get(n);
                if (par != null && !seen.contains(par)) {
                    seen.add(par);
                    q.offer(par);
                }
            }
        }
        
        while (!q.isEmpty()) {
            res.add(q.poll().val);
        }
        
        return res;
    }
    
    private void getParent(TreeNode root, TreeNode par) {
        if (root == null) return;
        
        map.put(root, par);
        
        getParent(root.left, root);
        
        getParent(root.right, root);
    }
}
```

#### Approach 1
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
class Solution {
    // ============= Method 2: Percolate Distance(DFS) ============
    // TO DO...
    
    // ============= Method 1: Annotate Parent (DFS + BFS) ===============
    // 3ms (60%)
    // Approach 2:
    Map<TreeNode, TreeNode> parents;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parents = new HashMap();
        dfs(root, null);
        
        Queue<TreeNode> q = new LinkedList();
        q.offer(target);
        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        
        int dist = 0;
        List<Integer> res = new LinkedList();
        
        if (K == 0) {   // don't forget to include it
            res.add(target.val);
            return res;
        }
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null && !seen.contains(node.left)) {
                    q.offer(node.left);
                    seen.add(node.left);
                }
                if (node.right != null && !seen.contains(node.right)) {
                    q.offer(node.right);
                    seen.add(node.right);
                }
                TreeNode par = parents.get(node);
                if (par != null && !seen.contains(par)) {
                    q.offer(par);
                    seen.add(par);
                }
            }
            dist++;
            if (dist == K) {
                while (!q.isEmpty()) {
                    res.add(q.poll().val);
                }
                return res;
            }
        }
        
        return res;        
    }
    
    // Approach 1:
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parents = new HashMap();
        
        dfs(root, null);
        
        Queue<TreeNode> q = new LinkedList();
        q.offer(null);  // as start of each distance
        q.offer(target);
        
        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null);
        
        int dist = 0;
        List<Integer> res = new ArrayList();
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            
            if (n == null) {
                if (dist == K) {
                    
                    while (!q.isEmpty()) {
                        res.add(q.poll().val);
                    }
                    break;
                }
                dist++;
                q.offer(null);
            } else {
                if (!seen.contains(n.left)) {
                    seen.add(n.left);
                    q.offer(n.left);
                }
                if (!seen.contains(n.right)) {
                    seen.add(n.right);
                    q.offer(n.right);
                }
                TreeNode par = parents.get(n);
                if (!seen.contains(par)) {
                    seen.add(par);
                    q.offer(par);
                }
            }
        }
        return res;
    }
    
    private void dfs(TreeNode root, TreeNode parent) {
        if (root == null) return;
        
        parents.put(root, parent);
        dfs(root.left, root);
        dfs(root.right, root);
    }
}

```

