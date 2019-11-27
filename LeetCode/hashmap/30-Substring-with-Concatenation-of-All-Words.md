## [30. Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)

You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:
```
Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
```
Example 2:
```
Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
```
## Answer
### Method 1 - Sliding Window - 
```java
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> l = new ArrayList();
        if (nums == null || nums.length == 0) return l;
        
        int[] a = new int[nums.length];
        
        for (int i = nums.length - 2; i >= 0; i--) {
            int cnt = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) cnt++;
            }
            a[i] = cnt;
        }
        
        
        
        for (int i = 0; i < nums.length; i++) 
            l.add(a[i]);
        
        return l;
    }
}
```
