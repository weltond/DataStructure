## 🔒 [314. Binary Tree Vertical Order Traversal](https://leetcode.com/problems/binary-tree-vertical-order-traversal/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

 

Example 1:

![image](https://user-images.githubusercontent.com/9000286/150659864-cd8c46b1-752e-4365-b739-22737585403f.png)


```
Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
```

Example 2:

![image](https://user-images.githubusercontent.com/9000286/150659871-55b29f5f-7e1a-4153-8a13-81946753ccfc.png)

```
Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]
```

Example 3:

![image](https://user-images.githubusercontent.com/9000286/150659874-b8170909-3ae3-4d91-bceb-5da14d9dcca3.png)

```
Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]
``` 

**Constraints**:

- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100

## Answers
### Method 2 - BFS
#### Approach 2 - No sort

Time: O(N)

Space: O(N)

```java
class Solution {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> output = new ArrayList();
    if (root == null) {
      return output;
    }

    Map<Integer, ArrayList> columnTable = new HashMap();
    // Pair of node and its column offset
    Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
    int column = 0;
    queue.offer(new Pair(root, column));

    int minColumn = 0, maxColumn = 0;

    while (!queue.isEmpty()) {
      Pair<TreeNode, Integer> p = queue.poll();
      root = p.getKey();
      column = p.getValue();

      if (root != null) {
        if (!columnTable.containsKey(column)) {
          columnTable.put(column, new ArrayList<Integer>());
        }
        columnTable.get(column).add(root.val);
        minColumn = Math.min(minColumn, column);
        maxColumn = Math.max(maxColumn, column);

        queue.offer(new Pair(root.left, column - 1));
        queue.offer(new Pair(root.right, column + 1));
      }
    }

    for(int i = minColumn; i < maxColumn + 1; ++i) {
      output.add(columnTable.get(i));
    }

    return output;
  }
}
```

#### Appraoch 1 - Sort

Time: O(NlogN)

Space: O(N) where N is the number of nodes in the tree.

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
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> output = new ArrayList();
    if (root == null) {
      return output;
    }

    Map<Integer, ArrayList> columnTable = new HashMap();
    Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
    int column = 0;
    queue.offer(new Pair(root, column));

    while (!queue.isEmpty()) {
      Pair<TreeNode, Integer> p = queue.poll();
      root = p.getKey();
      column = p.getValue();

      if (root != null) {
        if (!columnTable.containsKey(column)) {
          columnTable.put(column, new ArrayList<Integer>());
        }
        columnTable.get(column).add(root.val);

        queue.offer(new Pair(root.left, column - 1));
        queue.offer(new Pair(root.right, column + 1));
      }
    }

    List<Integer> sortedKeys = new ArrayList<Integer>(columnTable.keySet());
    Collections.sort(sortedKeys);
    for(int k : sortedKeys) {
      output.add(columnTable.get(k));
    }

    return output;
  }
}
```
### Method 1 - DFS 

#### Appraoch 2

Time: O(W * HlogH) where W is the width of tree, H is the hight of the tree.

Space: O(N) where N is the number of nodes in the tree.

```java
class Solution {
  Map<Integer, ArrayList<Pair<Integer, Integer>>> columnTable = new HashMap();
  int minColumn = 0, maxColumn = 0;

  private void DFS(TreeNode node, Integer row, Integer column) {
    if (node == null)
      return;

    if (!columnTable.containsKey(column)) {
      this.columnTable.put(column, new ArrayList<Pair<Integer, Integer>>());
    }

    this.columnTable.get(column).add(new Pair<Integer, Integer>(row, node.val));
    this.minColumn = Math.min(minColumn, column);
    this.maxColumn = Math.max(maxColumn, column);
    // preorder DFS traversal
    this.DFS(node.left, row + 1, column - 1);
    this.DFS(node.right, row + 1, column + 1);
  }

  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> output = new ArrayList();
    if (root == null) {
      return output;
    }

    this.DFS(root, 0, 0);

    // Retrieve the resuts, by ordering by column and sorting by row
    for (int i = minColumn; i < maxColumn + 1; ++i) {

      Collections.sort(columnTable.get(i), new Comparator<Pair<Integer, Integer>>() {
        @Override
        public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
          return p1.getKey() - p2.getKey();
        }
      });

      List<Integer> sortedColumn = new ArrayList();
      for (Pair<Integer, Integer> p : columnTable.get(i)) {
        sortedColumn.add(p.getValue());
      }
      output.add(sortedColumn);
    }

    return output;
  }
}

```

#### Approach 1 - 4ms (56.16%)

Pretty much the same as [987. Vertical Ordr Traversal](https://github.com/weltond/DataStructure/blob/master/LeetCode/tree/987-Vertical-Order-Traversal.md) but the `compareTo()` method is a bit difference, as this one needs to sort by **idx (from left to right)**, while the other one needs to sort by **value**.

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
    List<Node> nodes;
    int idx = 0;
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        
        if (root == null) return res;
        
        nodes = new ArrayList();
        
        // traverse the tree
        inorder(root, 0, 0);
        
        // sort the collection nodes
        Collections.sort(nodes);
        
        // get result
        Node prev = nodes.get(0);
        res.add(new ArrayList());
        
        for (Node node : nodes) {
            if (node.x != prev.x) {
                prev = node;
                res.add(new ArrayList());
            }
            
            res.get(res.size() - 1).add(node.val);
        }
        
        
        return res;
    }
    
    private void inorder(TreeNode root, int x, int y) {
        if (root == null) return;
        
        inorder(root.left, x - 1, y + 1);
        
        nodes.add(new Node(root.val, x, y, idx++));
        
        inorder(root.right, x + 1, y + 1);
    }
}

class Node implements Comparable<Node> {
    int x, y, val, idx;

    public Node (int val, int x, int y, int idx) {
        this.x = x;
        this.y = y;
        this.val = val;
        this.idx = idx; // to indicate which comes first when two nodes in same x&y.
    }

    @Override
    public int compareTo(Node that) {
        if (this.x != that.x) return this.x - that.x;

        else if (this.y != that.y) return this.y - that.y;

        else return this.idx - that.idx;
    }
}


```
