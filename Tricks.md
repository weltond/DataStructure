1. 想得到BFS的路径，可以使用Map来保存每个结点generate出来的节点。最后再通过DFS来遍历。
2. 单向BFS耗费内存，可以使用双向BFS。
3. 剪枝(pruning)的时候，有时为了防止重复，如下，在backtracking的过程中可能还会产生`[5,3]`等。为了避免重复，可以使每一层的遍历仅从`2`,`3`,`5`开始。
在代码中我们往下层传递的不是`level + 1`而是`i`。详情请见[Combination Sum](https://github.com/weltond/DataStructure/blob/master/LeetCode/backtracking/Lc39CombinationSum.java)
```
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```
4. 多画recursion tree来理解题，必要的时候考虑清楚memoize什么东西以及为何要memoize。

- 对于[Word Break II](https://github.com/weltond/DataStructure/blob/master/LeetCode/backtracking/Lc140WordBreakII.java)来说，给的例子可能看不出为何要memo，但是考虑到这个例子`input [aaaa], dict [a,aa,aaa]`就会发现有很多重复计算的地方。画出recursion tree.发现`lvl2`中第一个的subtree与`lvl1`中第二个的subtree的结果完全一样。那么memo的东西可以以`map`来存储当前节点为key, 其子树的各个枝为Value。
```
                                                 root                                         <---- lvl 0
                a(aaa)                    |     aa(aa)         |       aaa(a)                 <---- lvl 1
    a(aa)     |   aa(a)    |   aaa()      | a(a)  |  aa()      |       a()                    <---- lvl 2
 a(a) |  aa() |    a()     |              | a()   |            |                              <---- lvl 3
a()   |       |                                                                               <---- lvl 4
```
5. 对于2sum，3sum，4sum基本就两种做法：
  - Two Pointers：固定前1个或2个，然后对剩下的两个做双指针。注意必须**排序**。
  - Hash Table： 存储之前的和，再在后面查找map中的值。无需排序。

6. Combination Sum相关问题。想清楚recursion时候从哪里作为起始点。注意必须先**排序**。
  - 6.1 Combination Sum: Given Non-Duplicate array and target, return solution set. **Each number can be used unlimited**.
```java
void bt(int[] arr, int level, int rem){
    for (int i = level) {
        list.add();
        bt(arr, i, rem - arr[i]); // next level start from i, NOT level + 1, to 1) reuse number; 2) avoid duplicate result
        list.remove();
    }
}
```
  - 6.2 Combination Sum II: Given Duplicate array and target, return solution set. **Each number can ONLY be used once**.
```java
void bt(int[] arr, int level, int rem) {
    Set<Integer> set = new HashSet();
    for (int i = level) {
        if (!set.add(arr[i])) continue; // skip same value on the same level
        if (rem - arr[i] < 0) break;  // pruning
        
        list.add();
        bt(arr, i + 1, rem - arr[i]); // next level start from i + 1 becuase each number can only be used once.
        list.remove();
    }
}
```
  - 6.3 Combintaion Sum III: Find all set combinations of `k` numbers that add up to a number `n`.
```java
void bt(int k, int lvl, int cnt, int n） {
    if (cnt > k) return;  // pruning
    if (rem == 0 && cnt == k) {} // base case
    
    for (int i = lvl) {
        if (n < i) break; // pruning
        
        list.add();
        bt(k, i + 1, cnt + 1);  // next level obviously starts from i + 1 because each number is unique
        list.remove();
    }
}
```
  - 6.4 Combination Sum IV: Given array Non-duplicate. Find number of combinations that add up to target.
    - DFS + Memoization
    - DP. dp[i] represents how many combinations to get to sum i.
```java
// ============== DFS + Memoization ===============
int dfs(int[] nums, int rem, int[] memo) {
    // SHOULDN'T use 0 as default
    // Otherwise we won't know whether is no result or not visited yet.
    if (memo[rem] != -1) return memo[rem];  
    if (rem == 0) return 1; // base case
    for (int i = 0) {
        if (rem < nums[i]) break; // pruning
        sum += dfs(nums, rem- nums[i], memo);
    }
    memo[rem] = sum;
    return sum;
}
``` 
```java

// ============== DP ===============
int[] dp = int[target + 1];
dp[0] = 1;
for (int i = 0; i <= target) {
    for (int j = 0; j < nums.length) {
        if (i < nums[j]) continue;
        dp[i] += dp[i - nums[j]];
    }
}
return dp[target];
```
