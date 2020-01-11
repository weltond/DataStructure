## [651. Binary Tree Vertical Order Traversal](https://www.lintcode.com/problem/binary-tree-vertical-order-traversal/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Example1

```
Inpurt:  {3,9,20,#,#,15,7}
Output: [[9],[3,15],[20],[7]]
Explanation:
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
```

Example2

```
Input: {3,9,8,4,0,1,7}
Output: [[4],[9],[3,0,1],[8],[7]]
Explanation:
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
```

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

### Method 1 - DFS - :turtle: 402ms (6.40%)

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
