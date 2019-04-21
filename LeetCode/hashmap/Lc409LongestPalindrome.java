// https://leetcode.com/problems/longest-palindrome/

/**
Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
*/

class Solution {
    // 1ms (100%)
    public int longestPalindrome(String s) {
        int[] cnt = new int[128];
        for (char c: s.toCharArray()) {
            cnt[c]++;
        }
        
        int ans = 0;
        for (int v : cnt) {
            ans += v / 2 * 2;   // "aaaaa" -> "aaaa"
            if (ans % 2 == 0 && v % 2 == 1) {   // if v % 2 == 1 then this letter can be added as a unique center. We also check ans % 2 == 0 to make sure there isn't a unique center added yet.
                ans++;
            }
        }
        return ans;
    }
}
