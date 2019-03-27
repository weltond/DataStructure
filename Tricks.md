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

