package com.weltond.search.binarysearch;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/22/2019
 */
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
