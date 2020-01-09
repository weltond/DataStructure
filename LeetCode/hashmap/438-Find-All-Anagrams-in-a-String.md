## [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/)

Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:
```
Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
```
Example 2:
```
Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
```

## Answer
### Method 2 - Sliding Window - :rabbit:24ms (42.50%)
#### Approach 1
```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList();
        
        Map<Character, Integer> map = new HashMap();
        
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        int rem = map.size();
        
        int begin = 0, end = 0;
        
        int len = Integer.MAX_VALUE;
        
        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) rem--;
            }
            end++;
            
            while (rem == 0) {
                char tmp = s.charAt(begin);
                if (map.containsKey(tmp)) {
                    map.put(tmp, map.get(tmp) + 1);
                    if (map.get(tmp) > 0) rem++;
                }
                
                if (end - begin == p.length()) res.add(begin);
                
                begin++;
            }
        }
        
        return res;
    }
}
```

#### Approach 2

```java
public class Solution {
    /**
     * @param s: a string
     * @param p: a string
     * @return: a list of index
     */
    public List<Integer> findAnagrams(String s, String p) {
        // Write your code here
        List<Integer> ans = new ArrayList <Integer>();
        int[] sum = new int[30];

        int plength = p.length(), slength = s.length();
        for(char c : p.toCharArray()){
            sum[c - 'a'] ++;
        }
        
        int start = 0, end = 0, matched = 0;
        while(end < slength){
            if(sum[s.charAt(end) - 'a'] >= 1){
                matched ++;
            }
            sum[s.charAt(end) - 'a'] --;
            end ++;
            if(matched == plength) {
                ans.add(start);
            }
            if(end - start == plength){
                if(sum[s.charAt(start) - 'a'] >= 0){
                    matched --;
                }
                sum[s.charAt(start) - 'a'] ++;
                start ++;
            }
        }
        return ans;
    }
}
```

### Method 1 - Naive - :turtle: 492ms (13.20%)
```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList();
        
        int[] arr = new int[26];
        for (int i = 0, len = p.length(); i < len; i++) {
            arr[p.charAt(i) - 'a']++;
        }

        int i = 0, len = s.length(), lenp = p.length(), rem = lenp;
        
        while (i < len) {
            if (arr[s.charAt(i) - 'a'] != 0) {
                rem = lenp;
                int t = i;
                int[] tmp = Arrays.copyOf(arr, arr.length);

                while (rem != 0 && i < len) {
                    char c = s.charAt(i);

                    if (tmp[c - 'a']-- <= 0) break;
                    rem--;
                    i++;
                }
                
                if (rem == 0) {
                    res.add(t);
                }
                i = t + 1;
            } else {
                i++;
            }
        }
        
        return res;
    }
}
```
