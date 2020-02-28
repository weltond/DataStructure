## [1685. The Maze IV](https://www.lintcode.com/problem/the-mazeiv/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Give you a map where ‘S’ indicates the starting point and 'T' indicates the ending point. ‘#’ means that the wall is unable to pass, and '.’ means that the road can take a minute to pass. Please find the minimum time it takes to get from the start point to the end point. If the end point cannot be reached, please output -1.

Example

```
input:map=[['S','.'],['#','T']]
output:t=2
```

## Answer
### Method 1 - BFS - :rabbit: 409ms (68.97%)

- Tricky part: `maps[cx][cy] = '#'`. Set it to `#` immediately to avoid to many same coordinates enque that will cause **TLE** for some test cases.

```java
public class Solution {
    /**
     * @param maps: 
     * @return: nothing
     */
    public int theMazeIV(char[][] maps) {
        int si = 0, sj = 0, ti = 0, tj = 0;
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length; j++) {
                if (maps[i][j] == 'S') {
                    si = i; sj = j;
                } else if (maps[i][j] == 'T') {
                    ti = i; tj = j;
                }
            }
        }
        
        int[] dir = {0, 1, 0, -1, 0};
        
        Queue<int[]> q = new LinkedList();
        
        q.offer(new int[]{si, sj, 0});
        //int res = Integer.MAX_VALUE;
        boolean[][] seen = new boolean[maps.length][maps[0].length];
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                
                seen[cur[0]][cur[1]] = true;
                if (cur[0] == ti && cur[1] == tj) {
                    // res = Math.min(res, cur[2]);
                    return cur[2];
                }
                
                for (int k = 0; k < 4; k++) {
                    int cx = cur[0] + dir[k], cy = cur[1] + dir[k + 1], cost = cur[2] + 1;
                    if (cx < maps.length && cx >= 0 && cy < maps[0].length && cy >= 0 && maps[cx][cy] != '#' && !seen[cx][cy]) {
                        maps[cx][cy] = '#'; // IMPORTANT to avoid TLE!
                        q.offer(new int[]{cx, cy, cost});
                    }
                }
            }
        }
        
        return -1;
    }
}
```
