# Introduction
**Backtracking is an algorithmic paradigm that tries different solutions until finds a solution that 'works'.**

Problems which are typically solved using **backtracking** technique have following property in common.

  - These problems can only be solved by trying every possible configuration and each configuration is tried only once.
    - A naive solution for these problems is to try all configurations and output a configuration that follows given problem constraints.

  - **Backtracking** works in incremental way and is an optimization over the Naive solution where all possible configurations are generated and tried.
    - [Knight's Tour problem](https://github.com/weltond/DataStructure/blob/master/LeetCode/backtracking/KnightsTour.java)
    - [N Queens problem](https://github.com/weltond/DataStructure/blob/master/LeetCode/backtracking/NQueen.java)
    - [Rat in Maze problem](https://github.com/weltond/DataStructure/blob/master/LeetCode/backtracking/RatInMaze.java)
