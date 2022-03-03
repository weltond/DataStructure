## [773. Sliding Puzzle](https://leetcode.com/problems/sliding-puzzle/)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is `[[1,2,3],[4,5,0]]`.

Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

 

Example 1:

<img width="125" alt="image" src="https://user-images.githubusercontent.com/9000286/156499389-1f4ed9bc-2c79-46cf-8de8-87d480844a20.png">

```
Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
```

Example 2:

<img width="122" alt="image" src="https://user-images.githubusercontent.com/9000286/156499412-0d6fb192-af95-4a20-b03f-e05d75735e68.png">

```
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
```

Example 3:

<img width="123" alt="image" src="https://user-images.githubusercontent.com/9000286/156499443-f3c939a1-020f-4341-93c7-6fd7f03d783b.png">

```
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
``` 

**Constraints**:

- board.length == 2
- board[i].length == 3
- 0 <= board[i][j] <= 5
- Each value board[i][j] is unique.

## Answers

### Method 1 - BFS - 20ms (37.68%)
Consider each state in the board as a graph node, we just need to find out the min distance between start node and final target node "123450". Since it's a single point to single point questions, Dijkstra is not needed here. We can simply use BFS, and also count the level we passed. Every time we swap 0 position in the String to find the next state. Use a hashTable to store the visited states.

```java
class Solution {
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        String start = "";
        
        // all the positions 0 can be swapped to
        /**
        0 1 2
        3 4 5 --> 0 can go to {1, 3}

        1 0 2
        3 4 5 --> 0 can go to index of {0,2,4}

        1 2 0
        3 4 5 --> 0 can go to {1, 5}

        1 2 3
        0 4 5 --> 0 can go to {0,4}

        1 2 3
        4 0 5 --> 0 can go to {1, 3, 5}

        1 2 3
        4 5 0 --> 0 can go to {2, 4}
        */
        int[][] dirs = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                start += board[i][j];
            }
        }
        
        Set<String> visited = new HashSet();
        Deque<String> q = new LinkedList();
        
        q.offer(start);
        visited.add(start);
        
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                
                if (cur.equals(target)) return step;
                
                int zeroIdx = cur.indexOf('0');
                
                for (int dir : dirs[zeroIdx]) {
                    String next = swap(cur, zeroIdx, dir);
                    
                    if (visited.contains(next)) continue;
                    
                    visited.add(next);
                    q.offer(next);
                }
            }
            
            step++;
        }
        
        return -1;
    }
    
    private String swap(String cur, int i, int j) {
        StringBuilder sb = new StringBuilder(cur);
        sb.setCharAt(i, cur.charAt(j));
        sb.setCharAt(j, cur.charAt(i));
        
        return sb.toString();
    }
}
```
