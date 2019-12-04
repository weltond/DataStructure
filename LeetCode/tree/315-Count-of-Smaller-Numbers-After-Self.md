## [315. Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/)

You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

```
Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
```

## Answer
### Method 2 - BST - :rocket: 5ms (90.30%)

- Idea from [here](https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76580/9ms-short-Java-BST-solution-get-answer-when-building-BST).

```java
class Solution {
    class Node {
        Node left, right;
        int val, smaller, duplicate = 1;
        public Node(int v, int s) {
            smaller = s;
            val = v;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        int[] ans = new int[nums.length];
        
        Node root = null;
        
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(nums[i], root, ans, i, 0);
        }
        
        List<Integer> res = new LinkedList();
        
        for (int i = 0; i < ans.length; i++) {
            res.add(ans[i]);
        }
        
        return res;
    }
    
    private Node insert(int num, Node node, int[] ans, int i, int totalSmaller) {
        if (node == null) {
            node = new Node(num, 0);
            ans[i] = totalSmaller;
        } else if (node.val == num) {
            node.duplicate++;
            ans[i] = totalSmaller + node.smaller;
        } else if (node.val > num) {
            node.smaller++;
            node.left = insert(num, node.left, ans, i, totalSmaller);
        } else {
            node.right = insert(num, node.right, ans, i, node.smaller + node.duplicate + totalSmaller);
        }
        
        return node;
    }
}
```

### Method 1 - HashSet - :turtle: 484ms

```java

```
