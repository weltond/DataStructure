// https://leetcode.com/problems/word-break/


class Solution {
    // ========= Method 2: DP ============
    // 4ms
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0) return false;
        
        Set<String> dict = new HashSet();
        for (String str : wordDict) {
            dict.add(str);
        }
        
        boolean[] dp = new boolean[s.length() + 1];
        
        // Approach 1: 7ms
        // for (int i = 1; i <= s.length(); i++) {
        //     String cur = s.substring(0, i);
        //     if (dict.contains(cur)) {
        //         dp[i] = true;
        //     } else {
        //         for (int j = 0; j < i; j++) {
        //             dp[i] = dp[i] || (dp[j] && dict.contains(cur.substring(j)));
        //         }
        //     }
        // }
        
        // Approach 2: 4ms
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[s.length()];
    }
    
    // ========= Method 1: Recursion ========
    // 7ms
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0) return false;
        
        Set<String> dict = new HashSet();
        for (String str : wordDict) {
            dict.add(str);
        }
        
        Map<String, Boolean> memo = new HashMap();
        
        return helper(s, dict, memo);
    }
    
    private boolean helper(String s, Set<String> dict, Map<String, Boolean> memo) {
        if (s.length() == 0) return true;
        
        if (memo.containsKey(s)) return memo.get(s);
        
        for (int i = 0; i < s.length(); i++) {
            String cur = s.substring(0, i + 1);
            if (dict.contains(cur) && helper(s.substring(i + 1), dict, memo)) {
                memo.put(s, true);
                return true;
            }
        }
        memo.put(s, false);
        
        return false;
    }
}
