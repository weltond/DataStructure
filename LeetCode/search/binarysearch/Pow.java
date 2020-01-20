package com.weltond.search.binarysearch;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/22/2019
 */
public class Solution {
    /**
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        // write your code here
        if (n == Integer.MIN_VALUE) n /= 2;
        
        if (Math.abs(x) < 1e-10) return 0;
        
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        double ans = 1, tmp = x;    // tmp is x^1, x^2, x^4, x^8...
        while (n != 0) {
            if (n % 2 == 1) {
                ans *= tmp;
            }
            tmp *= tmp;
            n /= 2;
        }
        
        return ans;
    }
}

public class Pow {
class Solution {
    public double myPow(double x, int n) {
        // Stack Overflow if n = MIN_VALUE
        // Here we simply use n/2 to make sure new n is an even number
        // in order to deal with negative x.
        if (n == Integer.MIN_VALUE) return myPow(x, n/2);
        
        // If n < 0, make it positive and then divided by 1.
        if (n < 0) return 1 / helper(x, -n);
        else return helper(x, n);
    }
    
    public double helper(double x, int n) {
        // base case
        if (n == 0) return 1;
        if (n == 1 || x == 1) return x;

        double half = helper(x, n/2);
        
        return (n % 2 == 1) ? half * half * x : half * half;
    }
}
}
