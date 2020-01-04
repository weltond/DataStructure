## [1745. Monotonic Array](https://www.lintcode.com/problem/monotonic-array/description?_from=ladder&&fromId=130)



An array is monotonic if it is either monotone increasing or monotone decreasing.

An array A is monotone increasing if for all i <= j, A[i] <= A[j]. An array A is monotone decreasing if for all i <= j, A[i] >= A[j].

Return true if and only if the given array A is monotonic.

Example 1:

```
Input: [1,2,2,3]
Output: true
```

Example 2:

```
Input: [1,3,2]
Output: false
```

Notice
- 1 \leq A.length \leq 500001≤A.length≤50000
- -100000 \leq A[i] \leq 100000−100000≤A[i]≤100000

## Answer
### Method 1 - Naive - :rabbit: 427ms (82.09%)

```java
public class Solution {
    /**
     * @param A: a array
     * @return: is it monotonous
     */
    public boolean isMonotonic(int[] a) {
        if (a == null || a.length <= 2) return true;
        
        boolean isInc = false;
        
        int i = 0;
        while (i + 1 < a.length && a[i + 1] == a[i]) {
            i++;
        }
        if (i == a.length - 1) return true;
        
        isInc = a[i + 1] - a[i] > 0;
        
        
        while (i < a.length - 1) {
            while (i < a.length - 1 && a[i] == a[i + 1]) i++;
            if (i == a.length - 1) break;
            
            if (isInc ^ (a[i + 1] - a[i] >= 0)) {
                System.out.println(i);
                return false;
            }
            i++;
        }
        return true;
    }
}
```
