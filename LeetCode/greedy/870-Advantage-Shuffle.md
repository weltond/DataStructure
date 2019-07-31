## [870. Advantage Shuffle](https://leetcode.com/problems/advantage-shuffle/)

Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which `A[i] > B[i]`.

Return any permutation of A that maximizes its advantage with respect to B.
 

Example 1:
```
Input: A = [2,7,11,15], B = [1,10,4,11]
Output: [2,11,7,15]
```
Example 2:
```
Input: A = [12,24,8,32], B = [13,25,32,11]
Output: [24,32,8,12]
```

Note:

- `1 <= A.length = B.length <= 10000`
- `0 <= A[i] <= 10^9`
- `0 <= B[i] <= 10^9`

## Answer
### Method 1 - PQ - :rabbit: 63ms (53.56%)
- Time: O(nlogn)

**Two Rules:**
- We should always first satisfy the biggest of B, because they are the hardest to satisfy.
- If the biggest value of A cannot satisfy current value B, nothing can satisfy.


```java
class Solution {
    /**
        1. Sort A
        2. PQ of B in decreasing order
        3. extract element from PQ:
            1) if A[hi] > val, meanning A can satisfy. res[idx]=A[hi--]
            2) else B cannot be satisfied. put smallest in the slot. res[idx] = A[lo++]
    */
    public int[] advantageCount(int[] a, int[] b) {    
        Arrays.sort(a);
        int lo = 0, hi = a.length - 1;
        
        // max heap for b. <int[0] is val, int[1] is idx>
        PriorityQueue<int[]> pq = new PriorityQueue<>(a.length + 1, (o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < b.length; i++) {
            pq.offer(new int[]{b[i], i});
        }
        
        int[] ret = new int[a.length];
        
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int val = arr[0], idx = arr[1];
            // val is always the largest value from the remaining b.
            if (a[hi] > val) {
                ret[idx] = a[hi--];
            } else {
                ret[idx] = a[lo++];
            }
        }
        
        return ret;
    }
}
```
