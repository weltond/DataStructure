## [17. Subsets](https://www.lintcode.com/problem/subsets/description?_from=ladder&&fromId=130)

Given a set of distinct integers, return all possible subsets.

Example 1:

```
Input: [0]
Output:
[
  [],
  [0]
]
```

Example 2:

```
Input: [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

- Challenge: Can you do it in both recursively and iteratively?

Notice
- Elements in a subset must be in non-descending order.
- The solution set must not contain duplicate subsets.

## Answer
### Method 2 - BFS - :turtle: 258ms (25.00%)

```java
public class Solution {
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        // write your code here
        List<List<Integer>> res = new LinkedList();
        if (nums == null || nums.length == 0) {
            res.add(new ArrayList());
            return res;
        }
        Arrays.sort(nums);
        
        Queue<List<Integer>> q = new LinkedList();
        q.offer(new LinkedList());
        
        while (!q.isEmpty()) {
            List<Integer> subset = q.poll();
            res.add(subset);
            
            for (int i = 0; i < nums.length; i++) {
                if (subset.size() == 0 || subset.get(subset.size() - 1) < nums[i]) {
                    List<Integer> next = new LinkedList(subset);
                    next.add(nums[i]);
                    q.offer(next);
                }
            }
        }
        
        return res;
    }
}
```

### Method 1 - DFS - 
#### Approach 2 - :rabbit: 243ms (60.60%)

```java
public class Solution {
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        // write your code here
        List<List<Integer>> res = new LinkedList();
        if (nums == null || nums.length == 0) {
            res.add(new ArrayList());
            return res;
        }
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList(), res);
        
        return res;
    }
    
    private void dfs(int[] nums, int lvl, List<Integer> l, List<List<Integer>> res) {
        res.add(new ArrayList(l));
        
        for (int i = lvl; i < nums.length; i++) {
            l.add(nums[i]);
            dfs(nums, i + 1, l, res);
            l.remove(l.size() - 1);
        }
    }
}
```

#### Approach 1 - :turtle: 302ms (15.00%)

```java
public class Solution {
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        // write your code here
        List<List<Integer>> res = new LinkedList();
        if (nums == null || nums.length == 0) {
            res.add(new ArrayList());
            return res;
        }
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList(), res);
        
        return res;
    }
    
    private void dfs(int[] nums, int lvl, List<Integer> l, List<List<Integer>> res) {
        if (lvl == nums.length) {
            res.add(new ArrayList(l));
            return;
        }
        
        l.add(nums[lvl]);
        dfs(nums, lvl + 1, l, res);
        l.remove(l.size() - 1);
        
        dfs(nums, lvl + 1, l, res);
    }
}
```
