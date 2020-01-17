## [378. Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/)

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

```
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
```

Note:
- You may assume k is always valid, 1 ≤ k ≤ n2.

## Answer
### Method 3 - Binary Search - :rocket: 1ms (85.71%)

```java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[row - 1][col - 1];
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = 0, j = col - 1;
            
            for (int i = 0; i < row; i++) {
                while (j >= 0 && matrix[i][j] > mid) j--;
                
                cnt += (j + 1); // how many is greater than mid
            }
            
            if (cnt < k) lo = mid + 1;
            else hi = mid;
        }
        
        return lo;
    }
}
```
### Method 2 - Heap - :rabbit: 16ms (56.43%)

```java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1,o2)->(o2-o1));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                pq.offer(matrix[i][j]);
                if (pq.size() > k) pq.poll();
            }
        }
        return pq.peek();
    }
}
```

### Method 1 - Heap - :turtle: 19ms (41.38%)

```java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        // min heap.
        // int[] = {x, y, val}
        PriorityQueue<int[]> pq = new PriorityQueue<>(k + 1, (o1, o2) -> o1[2] - o2[2]);
        
        pq.offer(new int[]{0, 0, matrix[0][0]});
        boolean[][] seen = new boolean[matrix.length][matrix[0].length];
        seen[0][0] = true;
        
        int[] dir = {0, 1, 0};
        while (!pq.isEmpty()) {
            k--;
            int[] cur = pq.poll();
            int x = cur[0], y = cur[1], val = matrix[x][y];  
            if (k == 0) return val;
            for (int i = 0; i < 2; i++) {
                int nx = x + dir[i];
                int ny = y + dir[i + 1];
                if (nx >= matrix.length || ny >= matrix[0].length || seen[nx][ny]) continue;
                
                seen[nx][ny] = true;
                pq.offer(new int[]{nx, ny, matrix[nx][ny]});
            }
        }
        
        return -1;
    }
}
```
