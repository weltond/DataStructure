## [72. Edit Distance](https://leetcode.com/problems/edit-distance/)

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

- Insert a character
- Delete a character
- Replace a character

Example 1:

```
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
```

Example 2:

```
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
```

## Answer
### Method 2 - DP -
#### Approach 1 - 2D array - :turtle:7ms (42.13%)

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int del = dp[i - 1][j] + 1;
                    int add = dp[i][j - 1] + 1;
                    int rep = dp[i - 1][j - 1] + 1;
                    
                    dp[i][j] = Math.min(del, Math.min(add, rep));
                }
            }
        }
        
        return dp[len1][len2];
    }
}
```

### Method 1 - DFS - :rocket: 4ms (96.27%)

```java
class Solution {
    int[][] memo;
    public int minDistance(String word1, String word2) {
        memo = new int[word1.length()][word2.length()];
        return dfs(word1, word2, 0, 0);
    }
    
    private int dfs(String w1, String w2, int i, int j) {
        int len1 = w1.length(), len2 = w2.length();
        if (i == len1) return len2 - j;
        if (j == len2) return len1 - i;
        
        if (memo[i][j] != 0) return memo[i][j];
        
        int ret = Integer.MAX_VALUE;
        if (w1.charAt(i) == w2.charAt(j)) {
            ret = dfs(w1, w2, i + 1, j + 1);
        } else {
            int delete = dfs(w1, w2, i + 1, j) + 1;
            int insert = dfs(w1, w2, i, j + 1) + 1;
            int rep = dfs(w1, w2, i + 1, j + 1) + 1;
            ret = Math.min(delete, Math.min(insert, rep));
        }
        
        memo[i][j] = ret;
        return ret;
    }
}
```
