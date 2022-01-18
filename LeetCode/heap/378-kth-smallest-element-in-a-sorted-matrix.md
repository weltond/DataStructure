## [378. Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/)

Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n2).

 

Example 1:

```
Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
```

Example 2:

```
Input: matrix = [[-5]], k = 1
Output: -5
``` 

**Constraints**:

- n == matrix.length == matrix[i].length
- 1 <= n <= 300
- -109 <= matrix[i][j] <= 109
- All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
- 1 <= k <= n2
 

**Follow up**:

- Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
- Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.

## Answer
### Method 3 - Binary Search - :rocket: 1ms (85.71%)

```
方法一：二分答案
Time O(logRange * nlogn)
Space O(1)
解析：
函数countLessEqual(int[][] matrix, int value)的作用：统计matrix矩阵中小于等于value的数有多少个，时间复杂度为 nlogn（n行，每行的复杂度为logn)
总共调用 logRange 次 countLessEqual，所以总的时间复杂度为 logRange * nlogn
又因为 Range 的最大值为 Integer.MAX_VALUE - Integer.MIN_VALUE = 2^31 -1 - (-2^31) = 2^32 - 1，故 logRange 最大也不超过32, 可视为常数

方法二：在方法一的基础上，对countLessEqual函数的改进
Time O(logRange * n)
Space O(1)
解析：
sorted matrix 的两个性质：(1) 每一行是升序 (2) 每一列是升序
方法一的countLessEqual函数，只利用了性质(1)对行做二分，那能不能把性质(2)也利用起来呢?
答案是可以的，过程如下：
以矩阵右上角的点做为起点，
如果该点 <= value, 那么这一行的数都 <= value，计入cnt，i++
如果该点 > value，那么这一列都是 > value , 舍弃这一列，j--
对更新后的点重复上述操作，时间复杂度优化到O(n)

方法三：minHeap
Time O(klogn)
Space O(n)

以k的三个量级O(1) O(n) O(n^2), 比较方法二与方法三

k = O(1)	k = O(n)	k = O(n^2)
方法二（n）	n	n	n
方法三 （klogn）	logn	n * logn	n^2 * logn
可见，当k为常数量级的时候，方法三是有优势的，此外都是方法二更优
```

- Method 2 - 0ms (100%)
```java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[row - 1][col - 1];
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            int cnt = countLessEqual(matrix, mid);
            
            if (cnt < k) lo = mid + 1;
            else hi = mid;
        }
        
        return lo;
    }
    
    private int countLessEqual(int[][] arr, int target) {
        int cnt = 0, n = arr.length;
        int i = n - 1, j = 0;
        
        while (i >= 0 && j < arr[0].length) {
            if (arr[i][j] <= target) {
                j++;    // move to next column
                cnt += i + 1;   // the whole column is less than target. The count should be the hight of row.
            } else {
                i--;
                
            }
        }
        
        return cnt;
    }
}
```
```java
public class Solution {
    /**
     * @param matrix: List[List[int]]
     * @param k: a integer
     * @return: return a integer
     */
    public int kthSmallest(int[][] matrix, int k) {
        // write your code here
        int n = matrix.length;
        
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (countLessEqual(matrix, mid) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo;
    }
    
    private int countLessEqual(int[][] arr, int target) {
        int cnt = 0, n = arr.length;
        int i = 0, j = n - 1;
        
        while (i < n && j >= 0) {
            if (arr[i][j] <= target) {
                cnt += j + 1;
                i++;
            } else {
                j--;
            }
        }
        
        return cnt;
    }
}
```

- Method 1 - 1ms (85.39%)

```java
public class Solution {
    /**
     * @param matrix: List[List[int]]
     * @param k: a integer
     * @return: return a integer
     */
    public int kthSmallest(int[][] matrix, int k) {
        // write your code here
        int n = matrix.length;
        
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (countLessEqual(matrix, mid) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo;
    }
    
    private int countLessEqual(int[][] arr, int target) {
        int cnt = 0;
        for (int[] row : arr) {
            int i = 0, j = row.length - 1;
            
            while (i <= j) {
                int mid = i + (j - i) / 2;
                if (row[mid] > target) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            }
            
            cnt += i;
        }
        
        return cnt;
    }
}
```


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
