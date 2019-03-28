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
