## [402. Continuous Subarray Sum](https://www.lintcode.com/problem/continuous-subarray-sum/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given an integer array, find a continuous subarray where the sum of numbers is the biggest. Your code should return the index of the first number and the index of the last number. (If their are duplicate answer, return the minimum one in lexicographical order)

Example 1:

```
Input: [-3, 1, 3, -3, 4]
Output: [1, 4]
```

Example 2:

```
Input: [0, 1, 0, 1]
Output: [0, 3]
Explanation: The minimum one in lexicographical order.
```

## Answer
### Method 1 - Two Pointer - :rabbit: 748ms (65.40%)

```java
public class Solution {
    /*
     * @param A: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySum(int[] A) {
        // write your code here
        List<Integer> res = new ArrayList();
        if (A == null || A.length == 0) return res;
        
        int cmax = 0, tmax = Integer.MIN_VALUE;
        int ts = 0, te = 0, start = 0, end = 0;
        
        for (int i = 0; i < A.length; i++) {
            // cmax = Math.max(A[i], A[i] + cmax);
            if (cmax < 0) {
                cmax = A[i];
                ts = i;
                te = i;
            } else {
                cmax += A[i];
                te = i;
            }
            if (tmax < cmax) {
                tmax = cmax;
                start = ts;
                end = te;
            }
        }
        
        res.add(start);
        res.add(end);
        return res;
    }
}
```
