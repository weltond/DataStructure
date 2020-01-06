## [105. Copy List with Random Pointer](https://www.lintcode.com/problem/copy-list-with-random-pointer/description?_from=ladder&&fromId=130)

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

**Challenge:**
- Could you solve it with O(1) space?

## Answer
### Method 2 - O(1) space - 

```java

```

### Method 1 - DFS - :rabbit: 2123ms (66.20%)

```java
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
     Map<RandomListNode, RandomListNode> map = new HashMap();
    public RandomListNode copyRandomList(RandomListNode head) {
        // write your code here
        return dfs(head);
    }
    
    private RandomListNode dfs(RandomListNode head) {
        if (head == null) return null;
        
        if (map.containsKey(head)) return map.get(head);
        
        RandomListNode rd = new RandomListNode(head.label);
        map.put(head, rd);
        
        rd.next = dfs(head.next);
        rd.random = dfs(head.random);
        
        return rd;
    }
}
```
