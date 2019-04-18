// https://leetcode.com/problems/longest-substring-without-repeating-characters/
/**
Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
class Solution {
    // ===== Using Array =======
    // 15ms
    public int lengthOfLongestSubstring(String s) {
        int[] arr = new int[128];
        int left = -1;  // its right is the start of the window
        int res = 0;
        Arrays.fill(arr, -1);
        for (int i = 0; i < s.length(); i++) {
            // if (arr[s.charAt(i)] == -1) {
            //     arr[s.charAt(i)] = i;
            // } else {
            //     left = Math.max(left, arr[s.charAt(i)]);
            //     arr[s.charAt(i)] = i;
            // }
            left = Math.max(left, arr[s.charAt(i)]);
            arr[s.charAt(i)] = i;
            res = Math.max(res, i - left);
        }
        
        return res;
    }
    
    // ===== Using Hash table ======
    // 20ms
    public int lengthOfLongestSubstring(String s) {
        int left = -1;
        int res = 0;
        Map<Character, Integer> map = new HashMap();    // current index of character
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                // make sure j is not going back. Consider "abba".
                left = Math.max(left, map.get(s.charAt(i)));
            }
            res = Math.max(res, i - left);
            map.put(s.charAt(i), i);
        }
        return res;
    }
}
