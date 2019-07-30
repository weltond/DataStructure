// https://leetcode.com/problems/edit-distance/

/**
You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
*/

class Solution {
    int[][] memo;
    public int minDistance(String word1, String word2) {
        memo = new int[word1.length()][word2.length()];
        
        return dfs(word1, word2, 0, 0);
    }
    
    private int dfs(String w1, String w2, int i, int j) {
        if (w1.length() == i) {
            return w2.length() - j;
        }
        if (w2.length() == j) {
            return w1.length() - i;
        }
        
        if (memo[i][j] != 0) return memo[i][j];
        
        int ans = -23;
        if (w1.charAt(i) == w2.charAt(j)) {
            ans = dfs(w1, w2, i + 1, j + 1);
        } else {
            int delete = 1 + dfs(w1, w2, i + 1, j);
            int insert = 1 + dfs(w1, w2, i, j + 1);
            int replace = 1 + dfs(w1, w2, i + 1, j + 1);
            ans = Math.min(replace, Math.min(delete, insert));
        }
        
        memo[i][j] = ans;
        
        return ans;
    }
}

class Solution {
    // ============== Method 2: DP ================
    // Approach 2: 1-D array
    // 6ms (68.6%)
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return word1 == null ? word2 == null ? 0 : word2.length() : word1.length();
        }
        
        int len1 = word1.length(), len2 = word2.length();
        
        int[] dp = new int[len2 + 1];
            
        for (int i = 0; i <= len2; i++) {       
            dp[i] = i;
        }

        for (int i = 1; i <= len1; i++) {
            int prev = dp[0];
            dp[0] += 1; // for every row, we should also add 1 to it since it represents if w2 is empty, min edit.
            for (int j = 1; j <= len2; j++) {
                int cur = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prev;  //dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int delete = 1 + dp[j]; // int delete = 1 + dp[i - 1][j];
                    int replace = 1 + prev;  //int replace = 1 + dp[i - 1][j - 1];
                    int insert = 1 + dp[j - 1];    //int insert = 1 + dp[i][j - 1];
                    dp[j] = Math.min(delete, Math.min(replace, insert));
                }
                prev = cur;
            }
        }
        
        return dp[len2];
    }
    // Approach 1: 2-D array
    // 7ms (43.15%)
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return word1 == null ? word2 == null ? 0 : word2.length() : word1.length();
        }
        
        int len1 = word1.length(), len2 = word2.length();
        //dp[i][j] means the min edit distance for word1[0...i] and word[0...j]
        int[][] dp = new int[len1 + 1][len2 + 1];
        
        // base case is word1 is empty or word2 is empty
        for (int i = 0; i <= len2; i++) {       // be care of "i <= len2"
            dp[0][i] = i;
        }
        for (int i = 0; i <= len1; i++) {       // be care of "i <= len1"
            dp[i][0] = i;
        }
        
        //dp[i][j] = 4 cases.
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int delete = 1 + dp[i - 1][j];
                    int replace = 1 + dp[i - 1][j - 1];
                    int insert = 1 + dp[i][j - 1];
                    dp[i][j] = Math.min(delete, Math.min(replace, insert));
                }
            }
        }
        
        return dp[len1][len2];
    }
    
    // ============== Method 1: Recursion =============
    // Approach 2: DFS + Memo
    // 4ms (95.58%)
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return word1 == null ? word2 == null ? 0 : word2.length() : word1.length();
        }
        
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return dfs(word1, word2, 0, 0, memo);
    }
    
    private int dfs(String w1, String w2, int p1, int p2, int[][] memo) {
        
        if (w1.length() == p1) return w2.length() - p2;
        else if (w2.length() == p2) return w1.length() - p1;
        
        //System.out.println(p1 + ", " + p2 + " -> " + memo[p1][p2]);
        if (memo[p1][p2] != -1) {
            
            return memo[p1][p2];}
        
        int res = 0;
        if (w1.charAt(p1) == w2.charAt(p2)) {
            res += dfs(w1, w2, p1 + 1, p2 + 1, memo);
        } else {
            int delete = 1 + dfs(w1, w2, p1 + 1, p2, memo);
            int insert = 1 + dfs(w1, w2, p1, p2 + 1, memo);
            int replace = 1 + dfs(w1, w2, p1 + 1, p2 + 1, memo);
            //System.out.println(p1 + ", " + p2 + " : " + delete + "/ " + insert + "/ " + replace);
            res = Math.min(delete, Math.min(insert, replace));
        }
        
        memo[p1][p2] = res;
        
        return res;
    }
    // Approach 1: DFS only
    // TLE
//     public int minDistance(String word1, String word2) {
//         if (word1 == null || word2 == null) {
//             return word1 == null ? word2 == null ? 0 : word2.length() : word1.length();
//         }
        
//         return dfs(word1, word2);
//     }
    
//     private int dfs(String w1, String w2) {
        
//         if (w1.equals("")) return w2.length();
//         if (w2.equals("")) return w1.length();
        
//         if (w1.charAt(0) == w2.charAt(0)) {
//             return dfs(w1.substring(1, w1.length()), w2.substring(1, w2.length()));
//         }
        
//         int delete = 1 + dfs(w1.substring(1, w1.length()), w2);
//         int insert = 1 + dfs(w1, w2.substring(1, w2.length()));
//         int replace = 1 + dfs(w1.substring(1, w1.length()), w2.substring(1, w2.length()));
        
//         int res = Math.min(delete, Math.min(insert, replace));

//         return res;
//     }
}
