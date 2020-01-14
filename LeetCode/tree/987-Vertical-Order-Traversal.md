## [651. Binary Tree Vertical Order Traversal](https://www.lintcode.com/problem/binary-tree-vertical-order-traversal/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

 

Example 1:



Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
Example 2:



Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 

Note:

The tree will have between 1 and 1000 nodes.
Each node's value will be between 0 and 1000.

## Answer
### Method 2 - BFS

#### Appraoch 1 - Hash Map - :turtle: 330ms (10.80%)

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
    /**
     * @param root: the root of tree
     * @return: the vertical order traversal
     */
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> res = new LinkedList();
        if (root == null) return res;
        
        Map<Integer, List<Integer>> map = new HashMap();
        
        Queue<Integer> qCol = new LinkedList();
        Queue<TreeNode> qNode = new LinkedList();
        qCol.offer(0);
        qNode.offer(root);
        
        int minCol = 0, maxCol = 0;
        
        while (!qCol.isEmpty()) {
            int col = qCol.poll();
            TreeNode node = qNode.poll();
            
            map.computeIfAbsent(col, o -> new ArrayList()).add(node.val);
            
            if (node.left != null) {
                qCol.offer(col - 1);
                qNode.offer(node.left);
                minCol = Math.min(minCol, col - 1);
            }
            if (node.right != null) {
                qCol.offer(col + 1);
                qNode.offer(node.right);
                maxCol = Math.max(maxCol, col + 1);
            }
        }
        
        for (int i = minCol; i <= maxCol; i++) {
            res.add(new ArrayList(map.get(i)));
        }
        
        return res;
    }
}


```

### Method 1 - DFS - 
#### Approach 1 :rocket: 2ms (99.10%)
    
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
    List<Location> locations;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        locations = new ArrayList();
        dfs(root, 0, 0);
        
        Collections.sort(locations);
        
        List<List<Integer>> res = new ArrayList();
        res.add(new ArrayList());
        
        int prev = locations.get(0).x;
        
        for (Location loc : locations) {
            if (loc.x != prev) {
                prev = loc.x;
                res.add(new ArrayList());
            }
            
            res.get(res.size() - 1).add(loc.val);
        }
        
        return res;
    }
    
    private void dfs(TreeNode root, int x, int y) {
        if (root == null) return;
        
        locations.add(new Location(x, y, root.val));
        
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }
}

class Location implements Comparable<Location> {
    int x, y, val;
    
    Location(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    
    @Override
    public int compareTo(Location that) {
        if (this.x != that.x) {
            return Integer.compare(this.x, that.x);
        } else if (this.y != that.y) {
            return Integer.compare(this.y, that.y);
        } else {
            return Integer.compare(this.val, that.val);
        }
        
        
    }
}
```

#### Approach 0
- This approach is for same x and y but first seen first out.
    
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
    /**
     * @param root: the root of tree
     * @return: the vertical order traversal
     */
    int offset, time;
    PriorityQueue<Element> pq;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> res = new LinkedList();
        if (root == null) return res;
        
        offset = 0;
        time = 0;
        pq = new PriorityQueue<Element>((o1, o2) -> o1.x == o2.x ? (o1.y == o2.y ? o1.timestamp - o2.timestamp: (o1.y - o2.y)) : o1.x - o2.x);
        
        dfs(root, 0, 0);
        
        while (!pq.isEmpty()) {
            Element e = pq.poll();
            int idx = e.x - offset;
            if (res.size() == idx) {
                res.add(new ArrayList());
            }
            res.get(idx).add(e.val);
        }
        
        return res;
    }
    
    private void dfs(TreeNode root, int x, int y) {
        if (root == null) return;
        
        offset = Math.min(offset, x);
        pq.offer(new Element(x, y, time++, root.val));
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }
}

class Element {
    int x;
    int y;
    int timestamp;
    int val;
    public Element(int x, int y, int t, int v) {
        this.x = x;
        this.y = y;
        timestamp = t;
        val = v;
    }
}
```


### Wrong Solution
- **WRONG** because the vertical order is not garunteed.

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
    /**
     * @param root: the root of tree
     * @return: the vertical order traversal
     */
    TreeMap<Integer, List<Integer>> map;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> res = new LinkedList();
        if (root == null) return res;
        
        map = new TreeMap();
        
        dfs(root, 0);
        
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        
        return res;
    }
    
    private void dfs(TreeNode root, int idx) {
        if (root == null) return;
        
        map.computeIfAbsent(idx, o -> new ArrayList()).add(root.val);
        dfs(root.left, idx - 1);
        dfs(root.right, idx + 1);
    }
}
```

