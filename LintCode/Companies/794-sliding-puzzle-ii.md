## [794. Sliding Puzzle II](https://www.lintcode.com/problem/sliding-puzzle-ii/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

On a 3x3 board, there are 8 tiles represented by the integers 1 through 8, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

Given an initial state of the puzzle board and final state, return the least number of moves required so that the initial state to final state.

If it is impossible to move from initial state to final state, return -1.

Example 1:

```
Input:
[
 [2,8,3],
 [1,0,4],
 [7,6,5]
]
[
 [1,2,3],
 [8,0,4],
 [7,6,5]
]
Output:
4

Explanation:
[                 [
 [2,8,3],          [2,0,3],
 [1,0,4],   -->    [1,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [2,0,3],          [0,2,3],
 [1,8,4],   -->    [1,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [0,2,3],          [1,2,3],
 [1,8,4],   -->    [0,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [1,2,3],          [1,2,3],
 [0,8,4],   -->    [8,0,4],
 [7,6,5]           [7,6,5]
]                 ]
```

Example 2：

```
Input:
[[2,3,8],[7,0,5],[1,6,4]]
[[1,2,3],[8,0,4],[7,6,5]]
Output:
-1
```

Challenge
- How to optimize the memory?
- Can you solve it with A* algorithm?

## Answer
### Method 2 - A* - 

```java
// TO DO..
```

### Method 1 - Bi-direction BFS - :turtle: 1798ms (28.40%)

```java
public class Solution {
    /**
     * @param init_state: the initial state of chessboard
     * @param final_state: the final state of chessboard
     * @return: return an integer, denote the number of minimum moving
     */
    public int minMoveStep(int[][] init_state, int[][] final_state) {
        String init = convert(init_state), target = convert(final_state);
        Set<String> start = new HashSet(), end = new HashSet();
        start.add(init);
        end.add(target);
        
        Set<String> visited = new HashSet();
        int steps = 0;
        
        while (!start.isEmpty() && !end.isEmpty()) {
            Set<String> tmp = new HashSet();
            steps++;
            for (String s : start) {
                // 1. should add visited.
                // 2. cannot put it into the next loop
                if (visited.contains(s)) {
                    continue;
                }
                visited.add(s);
                for (String next : getNext(s)) {
                    if (end.contains(next)) return steps;
                    tmp.add(next);
                }
            }
            
            start = end;
            end = tmp;
        } 
        
        return -1;
    }
    
    private String convert(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(arr[i][j]);
            }
        }
        
        return sb.toString();
    }
    private List<String> getNext(String state) {
        List<String> states = new ArrayList();
        int idx = state.indexOf('0');
        int x = idx / 3;
        int y = idx % 3;
        
        int[] dir = {0, 1, 0, -1, 0};
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i], ny = y + dir[i + 1];
            if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;
            
            // swap 0 and number in 4 direction
            char[] arr = state.toCharArray();
            arr[3 * x + y] = arr[3 * nx + ny];
            arr[3 * nx + ny] = '0';
            states.add(new String(arr));
        }
        
        return states;
    }
}
```
