## [652. Factorization](https://www.lintcode.com/problem/factorization/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

A non-negative numbers can be regarded as product of its factors.

Write a function that takes an integer n and return all possible combinations of its factors.

Example1

```
Input: 8
Output: [[2,2,2],[2,4]]
Explanation:
8 = 2 x 2 x 2 = 2 x 4
```

Example2

```
Input: 1
Output: []
```

Notice
- Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
- The solution set must not contain duplicate combination.

## Answer
### Method 1 - DFS - 

```java

```

### TLE - 75% passed

```java
public class Solution {
    /**
     * @param n: An integer
     * @return: a list of combination
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList();
        dfs(n, 2, new ArrayList(),  res);
        
        // for (List<Integer> l : res) {
        //     Collections.sort(l);
        // }
        return res;
    }
    
    private void dfs(int n, int start, List<Integer> list, List<List<Integer>> res) {
        if (n == 1) {
            // remove 1 * n situation
            if (list.size() > 1)
                res.add(new ArrayList(list));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                dfs(n / i, i, list, res);
                list.remove(list.size() - 1);
            }
        }
    }
}
```
