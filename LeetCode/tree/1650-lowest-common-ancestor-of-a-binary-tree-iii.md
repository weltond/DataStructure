## [1650. Lowest Common Ancestor of a Binary Tree III](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:
```
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
```

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."

 

Example 1:

![image](https://user-images.githubusercontent.com/9000286/149817343-25d43c02-e5f5-487f-812c-3d9f80df7e12.png)

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```

Example 2:

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
```

Example 3:

```
Input: root = [1,2], p = 1, q = 2
Output: 1
``` 

Constraints:

- The number of nodes in the tree is in the range [2, 105].
- -109 <= Node.val <= 109
- All Node.val are unique.
- p != q
- p and q exist in the tree.

## Answers

### Method 2 
Like [Intersection of Two LinkedList](https://github.com/weltond/DataStructure/blob/master/LeetCode/linkedlist/Lc160IntersectionOfTwoLinkedList.java). `parent` is next node. And out goal is to find the intersection.

```java
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        
        while (a != null || b != null) {
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
            
            if (a == b) return a;
        }
        
        return null;
    }
}

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        
        while (a != b) {
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }
        
        return a;
    }
}
```

### Method 1 
1. Store the path from p to the root
2. Traverse the path from q to the root, the first common point of the two paths is the LCA.

```java
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Map<Node,Node> map = new HashMap();
        
        Node a = p;
        while (a != null) {
            map.put(a, a.parent);
            a = a.parent;
        }
        
        Node b = q;
        while (b != null) {
            if (map.containsKey(b)) return b;
            b = b.parent;
        }
        
        return null;
    }
}
```
