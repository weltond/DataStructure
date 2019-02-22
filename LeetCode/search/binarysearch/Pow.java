package com.weltond.search.binarysearch;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/22/2019
 */
public class Pow {
    public double myPow(double x, int n) {
        // base case
        if (n == 0) return 1;
        if (n == 1 || x == 1) return x;
        // Stack Overflow if n = MIN_VALUE
        // Here we simply use n/2 to make sure new n is an even number
        // in order to deal with negative x.
        if (n == Integer.MIN_VALUE) return myPow(x, n/2);

        double half = myPow(x, n/2);

        return (n > 0) ? (n % 2 == 1) ? half * half * x : half * half : 1 / myPow(x, 0-n);
    }
}
