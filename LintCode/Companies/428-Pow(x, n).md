## [428. Pow(x, n)](https://www.lintcode.com/problem/powx-n/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Implement `pow(x, n)`. (n is an integer.)

Example
Example 1:

```
Input: x = 9.88023, n = 3
Output: 964.498
```

Example 2:

```
Input: x = 2.1, n = 3
Output: 9.261
Example 3:

Input: x = 1, n = 0
Output: 1
Challenge
O(logn) time
```

- Notice: You don't need to care about the precision of your answer, it's acceptable if the expected answer and your answer 's difference is smaller than `1e-3`.

## Answer
### Method 2 - Binary Search 

```java
public class Solution {
    /**
     * @param x the base number
     * @param n the power number
     * @return the result
     */
    public double myPow(double x, int n) {
        boolean isNegative = false;
        if (n < 0) {
            x = 1 / x;
            isNegative = true;
            n = -(n + 1);       // Avoid overflow when n == MIN_VALUE
        }

        double ans = 1, tmp = x;

        while (n != 0) {
            if (n % 2 == 1) {
                ans *= tmp;
            }
            tmp *= tmp;
            n /= 2;
        }
        
        if (isNegative) {
            ans *= x;
        }
        return ans;
    }
}
```

### Method 1 - Divide and Conquer - :rocket: 3322ms (99.80%)

```java
public class Solution {
    /**
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        // write your code here
        if (n == Integer.MIN_VALUE) return myPow(x, n / 2);
        if (n < 0) return 1 / myPow(x, -n);
        
        return dc(x, n);
    }
    
    private double dc(double x, int n) {
        if (n == 0) return 1;
        if (n == 1 || x == 1) return x;
        
        double ret = dc(x, n / 2);
        if (n % 2 == 0) {
            return ret * ret;
        } else {
            return ret * ret * x;
        }
    }
}
```
