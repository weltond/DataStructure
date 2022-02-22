## [340. Longest Substring with At Most K Distinct Characters]()

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

Given a string S, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

```
Input: S = "eceba" and k = 3
Output: 4
Explanation: T = "eceb"
```

Example 2:

```
Input: S = "WORLD" and k = 4
Output: 4
Explanation: T = "WORL" or "ORLD"
```

- Challenge: O(n) time

## Answer
### Method 1 - Sliding Window - :rabbit: 632ms (54.80%)
#### Approach 2

```java
public class Solution {
    /*
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int[] cnt = new int[256];
        char[] sc = s.toCharArray();

        int ans = 0;
        int sum = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {
            cnt[sc[r]]++;
            if (cnt[sc[r]] == 1) {
                sum++;
            }
            while (sum > k) {
                cnt[sc[l]]--;
                if (cnt[sc[l]] == 0) {
                    sum--;
                }
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### Approach 1
```java
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0, right = 0, len = s.length(), res = 0, cnt = 0;
        
        Map<Character, Integer> map = new HashMap();
        
        while (right < len) {
            char c = s.charAt(right);
            
            if (map.containsKey(c)) {
                int f = map.get(c);
                
                if (f == 0) cnt++;  // means c appears before but was excluded later. Case: "aa", k = 0
                
                map.put(c, f + 1);
                 
            } else {
                cnt++;
                map.put(c, 1);
            }
            if (cnt <= k) {      // cnt <= k to cover the case where len < k
                res = Math.max(res, right - left + 1);
            }
            
            while (cnt > k) {   // CANNOT be cnt == k. Case "aa", k=1
                char c2 = s.charAt(left++);
                int freq = map.get(c2);
                
                if (freq == 1) cnt--;
                
                map.put(c2, freq - 1);
            }
            
            right++;
        }
        
        return res;
    }
}
```
