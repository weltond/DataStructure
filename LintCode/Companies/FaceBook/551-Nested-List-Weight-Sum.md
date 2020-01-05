## [551. Nested List Weight Sum](https://www.lintcode.com/problem/nested-list-weight-sum/description?_from=ladder&&fromId=130)

Given a nested list of integers, return the sum of all integers in the list weighted by their depth. Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

```
Input: the list [[1,1],2,[1,1]], 
Output: 10. 
Explanation:
four 1's at depth 2, one 2 at depth 1, 4 * 1 * 2 + 1 * 2 * 1 = 10
```

Example 2:

```
Input: the list [1,[4,[6]]], 
Output: 27. 
Explanation:
one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4 * 2 + 6 * 3 = 27
```

## Answer
### Method 1 :rabbit: 1922ms (68.20%)

```java
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        // Write your code here
        return dfs(nestedList, 1);
    }
    
    private int dfs(List<NestedInteger> n, int lvl) {
        int res = 0;
        for (NestedInteger nest : n) {
            if (nest.isInteger()) {
                res += nest.getInteger() * lvl;
            } else {
                res += dfs(nest.getList(), lvl + 1);
            }
        }
        
        return res;
    }
}
```
