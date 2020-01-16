## [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

We have a list of `points` on the plane.  Find the `K` closest points to the origin `(0, 0)`.

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:

```
Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
```

Example 2:

```
Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
```

Note:

- `1 <= K <= points.length <= 10000`
- `-10000 < points[i][0] < 10000`
- `-10000 < points[i][1] < 10000`

## Answer
### Method 1 - PQ - :turtle: 37ms (14.44%)

```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue((o1, o2) -> dist((int[]) o2) - dist((int[]) o1));   // max
        for (int[] arr : points) {
            pq.offer(arr);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        
        int[][] res = new int[pq.size()][2];
        int idx = 0;
        while (!pq.isEmpty()) {
            res[idx++] = pq.poll();
        }
        
        return res;
    }
    
    private int dist(int[] d) {
        int x = d[0], y = d[1];
        return x * x + y * y;
    }
}
```

## Old post
```java
/*Old*/
class Solution {
    int[] ori = new int[]{0, 0} ;
    // =========== Method 2: Divide and Conquer ===========
    // 6ms (99.04%)
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0) return new int[][]{};
        
        findMatch(points, 0, points.length - 1, K);
        int[][] res = new int[K][];
        for (int i = 0; i < res.length; i++) {
            res[i] = points[i];
        }
        return res;
    }
    
    private void findMatch(int[][] arr, int start, int end, int K) {
        if (start >= end) return;
        
        int pos = findPos(arr, start, end);
        
        if (pos < K) findMatch(arr, pos + 1, end, K);
        else if (pos > K) findMatch(arr, start, pos - 1, K);
    }
    
    private int findPos(int[][] arr, int start, int end) {
        int tmp = end;
        int pivotDist = distance(arr[end], ori);
        end--;
        // ascending order
        while (start <= end) {
            while (start <= end && distance(arr[start], ori) < pivotDist) {
                start++;
            }
            while (start <= end && distance(arr[end], ori) >= pivotDist) {
                end--;
            }
            
            if (start <= end) {
                swap(arr, start, end);
            }
        }
        
        swap(arr, start, tmp);
        
        return start;
    }
    
    private void swap(int[][] arr, int l, int r) {
        int[] tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
    // =========== Method 1: PriorityQueue =============
    // 61ms (35.09%)
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0) return new int[][]{};
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(K + 1, (o1, o2) -> {
            return distance(o2, new int[]{0, 0}) - distance(o1, new int[]{0, 0});
        }); // max heap
        
        for (int i = 0; i < points.length; i++) {
            pq.offer(points[i]);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        
        int[][] res = new int[K][];
        int j = 0;
        while (!pq.isEmpty()) {
            res[j++] = pq.poll();
        }
        
        return res;
    }
    
    private int distance(int[] a, int[] b) {
        return a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1];
    }
}

// ====================== OLD ==================================
class Solution {
    
    /* Method 2: Divide and Conquer (Quick Partition)*/
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][];
        if (points == null || points.length == 0 || K >= points.length) return res;
        
        sort(points, K, 0, points.length - 1);
        
        return Arrays.copyOfRange(points, 0, K);
        
    }
    
    public void sort(int[][] points, int K, int start, int end) {
        if (start >= end) return;
        
        /* Fancy way to pick pivot
        int k = ThreadLocalRandom.current().nextInt(start, end);
        // swap with the end
        swap(points, k, end);
        */
        
        // here, we just pick the last as pivot
        
        int mid = partition(points, start, end);
        int leftLength = mid - start + 1;
        
        /* Method 1 to switch*/
        if (K < mid) {
            sort(points, K, start, mid - 1);
        }else if (K > mid) {
            sort(points, K, mid + 1, end);
        }
        
        /* Method 2 to switch*/
        // if (K < leftLength)
        //     sort(points, K, start, mid - 1);
        // else if (K > leftLength)
        //     sort(points, K, mid + 1, end);
        
        // else we find right position K == mid
        
    }
    
    private int partition(int[][] points, int l, int r) {
        int tmp = r;
        int pivotDist = getDist(points[r]);
        r -= 1;
        
        while(l <= r) {
            while (l <= r && getDist(points[l]) < pivotDist) {
                l++;
            }
            while (l <= r && getDist(points[r]) >= pivotDist) {
                r--;
            }
            
            if (l <= r) {
                swap(points, l, r);
            }
        }
        
        swap(points, tmp, l);
        
        return l;
    }
    
    private void swap(int[][] points, int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }
    
    /* Method 1 : Heap*/
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][];
        
        if (points == null || points.length == 0 || K >= points.length) return res;
        
        // max heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(K + 1, (o1, o2) -> getDist(o2) - getDist(o1));
        
        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        
        for (int i = 0; i < K; i++) {
            res[i] = pq.poll();
        }
        
        return res;
        
    }
    
    private int getDist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    } 
}
```
