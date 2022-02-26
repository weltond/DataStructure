## [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in `candidates` may only be used **once** in the combination.

**Note**: The solution set must not contain duplicate combinations.

 

Example 1:

```
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
```

Example 2:

```
Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]
 ```

**Constraints**:

- 1 <= candidates.length <= 100
- 1 <= candidates[i] <= 50
- 1 <= target <= 30

## Answers

### Method 1 - Backtracking 

```java
class Solution {
    // ============ Backtracking ==========
    // 4ms (99.74%)
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        
        if (candidates == null || candidates.length == 0 || target == 0) return res;
        
        Arrays.sort(candidates);
        
        bt(candidates, 0, target, new ArrayList(), res);
        
        return res;
    }
    
    private void bt(int[] arr, int level, int rem, List list, List res) {
        if (rem == 0) {
            res.add(new ArrayList(list));
            return;
        }
        
        
        Set<Integer> set = new HashSet();
        for (int i = level; i < arr.length; i++) {
            // skip same value on the same level
            if (!set.add(arr[i])) continue;
            // NOT compare with the next or previous one.
            //if (arr[i] == arr[level - 1]) continue;    
            
            if (rem - arr[i] < 0) break;    // stop early if rem is smaller than current
            
            list.add(arr[i]);
            
            bt(arr, i + 1, rem - arr[i], list, res);
            
            list.remove(list.size() - 1);
        }
    }
}
```
