## [935. Knight Dialer](https://leetcode.com/problems/knight-dialer/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.

Example 1:

```
Input: 1
Output: 10
```

Example 2:

```
Input: 2
Output: 20
```

Example 3:

```
Input: 3
Output: 46
```

Note:

- `1 <= N <= 5000`

## Answer
### Method 1 - DFS + Memo - :turtle: 112ms (24.69%)

```java
class Solution {
    Map<Integer, List<Integer>> map;
    int[][] memo;
    int MOD = 1000000007;
    public int knightDialer(int N) {
        map = new HashMap();
        map.put(1, Arrays.asList(8,6));
        map.put(2, Arrays.asList(7,9));
        map.put(3, Arrays.asList(4,8));
        map.put(4, Arrays.asList(3,9,0));
        map.put(5, Arrays.asList());
        map.put(6, Arrays.asList(1,7,0));
        map.put(7, Arrays.asList(2,6));
        map.put(8, Arrays.asList(1,3));
        map.put(9, Arrays.asList(4,2));
        map.put(0, Arrays.asList(4,6));
        
        memo = new int[N + 1][10];
        
        int res = 0;
        for (int i = 0; i <= 9; i++) {
            res = (res + dfs(N - 1, i)) % MOD;
        }
        
        return res;
    }
    
    private int dfs(int n, int cur) {
        if (n == 0) return 1;
        if (memo[n][cur] != 0) return memo[n][cur];
        
        int res = 0;
        for (int next : map.get(cur)) {
            res = (res + dfs(n - 1, next)) % MOD; 
        }
        
        memo[n][cur] = res;
        
        return res;
    }
}
```
