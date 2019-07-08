## [801. Minimum Swaps To Make Sequences Increasing](https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/)

We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

Example:
```
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation: 
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.
```
Note:

- A, B are arrays with the same length, and that length will be in the range [1, 1000].
- A[i], B[i] are integer values in the range [0, 2000].

## Answer
### Method 2 - DP
#### Approach 1 - :turtle: 6ms (6.97%)
```java
class Solution {
    // ======= Method 2: DP =======
    // 68ms (6.97%)
    public int minSwap(int[] a, int[] b) {
        int[] swap = new int[a.length];
        int[] keep = new int[a.length];
        Arrays.fill(swap, Integer.MAX_VALUE);
        Arrays.fill(keep, Integer.MAX_VALUE);
        swap[0] = 1;
        keep[0] = 0;
        
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1] && b[i] > b[i - 1]) {
                swap[i] = swap[i - 1] + 1;
                keep[i] = keep[i - 1];
            }
            
            if (a[i] > b[i - 1] && b[i] > a[i - 1]) {
                swap[i] = Math.min(swap[i], keep[i - 1] + 1);
                keep[i] = Math.min(keep[i], swap[i - 1]);
            }
            System.out.println(i +","+swap[i]+","+keep[i]);
        }
        
        return Math.min(swap[a.length - 1], keep[a.length - 1]);
    }
}
```
### Method 1 - DFS - TLE(84 / 102 test cases passed.)
```java
class Solution {
    // ======= Method 1: DFS =========
    // TLE (84 / 102 test cases passed.)
    int ans = Integer.MAX_VALUE;
    public int minSwap(int[] a, int[] b) {
        dfs(a, b, 1, 0);

        return ans;
    }
    
    private void dfs(int[] a, int[] b, int lvl, int res) {
        if (res >= ans) return;
        
        if (lvl == a.length) {
            ans = Math.min(ans, res);
            return;
        }
        
        if (a[lvl] > a[lvl - 1] && b[lvl] > b[lvl - 1]) {
            dfs(a, b, lvl + 1, res);
        }
        
        if (a[lvl] > b[lvl - 1] && b[lvl] > a[lvl - 1]) {
            swap(a, b, lvl);
            dfs(a, b, lvl + 1, res + 1);
            swap(a, b, lvl);
        }
    }
    
    private void swap(int[] a, int[] b, int i) {
        int tmp = a[i];
        a[i] = b[i];
        b[i] = tmp;
    }
}
```
