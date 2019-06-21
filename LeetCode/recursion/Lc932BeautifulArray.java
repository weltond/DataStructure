// https://leetcode.com/problems/beautiful-array/

/**
For some fixed N, an array A is beautiful if it is a permutation of the integers 1, 2, ..., N, such that:

For every i < j, there is no k with i < k < j such that A[k] * 2 = A[i] + A[j].

Given N, return any beautiful array A.  (It is guaranteed that one exists.)

 

Example 1:

Input: 4
Output: [2,1,4,3]
Example 2:

Input: 5
Output: [3,1,2,5,4]
 

Note:

1 <= N <= 1000
*/

class Solution {
    // ========= Method 1: Divide and Conquer ========
    // 1ms (88.80%)
    Map<Integer, int[]> map;
    public int[] beautifulArray(int N) {
        map = new HashMap();
        
        return dfs(N);
    }
    
    private int[] dfs(int n) {
        if (map.containsKey(n)) return map.get(n);
        
        int[] arr = new int[n];
        int i = 0;
        if (n == 1) {
            arr[0] = 1;
        } else {
            for (int x : dfs((n + 1) / 2)) {
                arr[i++] = 2 * x - 1;
            }
            for (int x : dfs(n / 2)) {
                arr[i++] = 2 * x;
            }
        }
        map.put(n, arr);
        
        return arr;
    }
}
