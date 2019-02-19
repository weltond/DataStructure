# Introduction
**Backtracking is an algorithmic paradigm that tries different solutions until finds a solution that 'works'.**

Problems which are typically solved using **backtracking** technique have following property in common.

  - These problems can only be solved by trying every possible configuration and each configuration is tried only once.
    - A naive solution for these problems is to try all configurations and output a configuration that follows given problem constraints.

  - **Backtracking** works in incremental way and is an optimization over the Naive solution where all possible configurations are generated and tried.
    - [Knight's Tour problem](https://github.com/weltond/DataStructure/blob/master/LeetCode/backtracking/KnightsTour.java)
    - [N Queens problem](https://github.com/weltond/DataStructure/blob/master/LeetCode/backtracking/NQueen.java)
    - [Rat in Maze problem](https://github.com/weltond/DataStructure/blob/master/LeetCode/backtracking/RatInMaze.java)

# 我的思考
### Steps
做backtracking一类的题目，必然要用到recursion，那么
  - 首先要想清楚recursion函数的signature和进入点：
    - 对于Number Of Islands，Word Search来说，recursion进入点就是碰到的第一个1 或者第一个满足条件的word的第一个字符， 那么在主函数中就需要遍历原矩阵来找到第一个1.
    - 对于Knites Tour 或者 Rat In Maze来说，进入点是单个的（左上角），那么在主函数中只需要给recursion函数一个(0,0)的坐标就行了
  - 一旦找到进入点后，进入recursion函数后
    - 先找到base case也就是recursion的终点条件。这里不先判断边界条件因为终点超出边界或者已满足所需解。
    - 再可以进行判断满不满足边界条件
    - 然后根据题目需求假定当前点满足条件并存储相对应的值/set visited
    - 进入recursion function的下一层
    - 如果下一层不满足条件退回到当前层，需要把之前假定的满足条件的值设置回初始值/set un-visited (**BACKTRACKING**)

### Note
**注意，如果你只需要其中一种解，可以给recursion函数一个返回值（通常是boolean），这样可以在当前层判断下一层返回的结果为true时直接返回true。**
  - 这种情况下，你给recursion传递的`solution[][]`在过程结束后会保存这种解。

**如果你想得到所有解，那么可以在不在当前层判断下一层返回的结果，在下一层返回后，做BACKTRACKING，在base case的终点处做处理得到所有想要的结果。**
  - 这种情况下，你给recursion传递的`solution[][]`在过程结束后会是**空的**， 因为你做了backtracking。你的所有解都是在base case里得到并处理。

# Permutation
```
P(nums, d, n, used, curr, ans):
    if d == n:
        ans.append(curr)
        return
    
    for i = 0 to len(nums):
        if used[i]: continue
        used[i] = true
        
        curr.add(nums[i])
        P(nums, d + 1, n, curr, ans)
        curr.remove()
        
        used[i] = false
```

- P([1,2,3], 0, 3, [false] * 3, [], ans)

[[1,2,3], [1,3,2], 
 [2,1,3], [2,3,1],
 [3,1,2], [3,2,1]]

- P([1,2,3], 0, 2, [false] * 3, [], ans)

[[1,2],[1,3],[2,1],[2,3],[3,1],[3,2]]

# Combination
```
C(nums, d, n, s, curr, ans):
    if d == n:
        ans.append(curr)
        return
    
    for i = s to len(nums):
        curr.add(nums[i])
        C(nums, d + 1, n, i + 1, curr, ans)
        curr.remove()
```


- C([1,2,3], 0, 3, [], ans)

[[1,2,3]]

- C([1,2,3], 0, 2, [], ans)

[[1,2],[1,3],[2,3]]
