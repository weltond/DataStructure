## [257.Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/description/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.

Example 1:

```
Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]
```
Example 2:
```
Input: root = [1]
Output: ["1"]
```

**Constraints:**

- The number of nodes in the tree is in the range [1, 100].
- -100 <= Node.val <= 100

## Answer
### Method 2
```java
public List<String> binaryTreePaths(TreeNode root) {
    StringBuilder path = new StringBuilder();
    List<String> paths = new LinkedList<>();
    dfs(root, path, paths);
    return paths;
}
public void dfs(TreeNode p, StringBuilder path, List<String> paths) {
    if (p == null) {
        return;
    }
    path.append(p.val);
    // 当前节点是叶子节点
    if (p.left == null && p.right == null) {
         // 把路径加入结果
        paths.add(path.toString());
    } else {
        path.append("->");
        // 这里需要复制创建新的StringBuilder对象
        dfs(p.left, new StringBuilder(path), paths);
        dfs(p.right, new StringBuilder(path), paths);
    }
}
```

### Method 1

```java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();

        dfs(root, new ArrayList<Integer>(), res);

        return res;
    }

    private void dfs(TreeNode root, List<Integer> list, List<String> res) {
        if (root == null) return;

        list.add(root.val);

        // leaf node
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int k = 0, size = list.size(); k < size - 1; k++) {
                sb.append(list.get(k)).append("->");
            }

            sb.append(list.get(list.size() - 1));
            
            res.add(sb.toString());

            list.remove(list.size() - 1);
            return;
        }

        dfs(root.left, list, res);

        dfs(root.right, list, res);

        list.remove(list.size() - 1);
    }
}
```
