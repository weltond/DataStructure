## [844. Backspace String Compare](https://leetcode.com/problems/backspace-string-compare/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

```
Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
```

Example 2:

```
Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
```

Example 3:

```
Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
```

Example 4:

```
Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
```

Note:

- `1 <= S.length <= 200`
- `1 <= T.length <= 200`
- S and T only contain lowercase letters and '#' characters.

Follow up:

- Can you solve it in O(N) time and O(1) space?

## Answer
### Method 1 - Two Pointer - :rocket: 0ms 

#### Approach 2

```java
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int skipS = 0;
        int skipT = 0;
        
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                // Find position of next possible char in S.
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            
            // if two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) return false;
            // if expecting to compare char VS nothing
            if ((i>=0) != (j>=0)) return false;
            
            i--; 
            j--;
        }
        return true;
    }
}
```

#### Approach 1

```java
public class Solution {
    /**
     * @param S: string S
     * @param T: string T
     * @return: Backspace String Compare
     */
    public boolean backspaceCompare(String S, String T) {
        // Write your code here

        int len1 = S.length(), len2 = T.length();
        int i = len1 - 1, j = len2 - 1;
        int cnt1 = 0, cnt2 = 0;
        while (i >= 0 || j >= 0) {
            char c1 = i < 0 ? '\0' : S.charAt(i), c2 = j < 0 ? '\0' : T.charAt(j);
            
            if (c1 == '#' && c2 == '#') {
                cnt1++;
                cnt2++;
                i--;
                j--;
            } else if (c1 == '#') {
                cnt1++;
                i--;
            } else if (c2 == '#') {
                cnt2++;
                j--;
            } else {
                if (cnt1 > 0 && c1 != '\0') {
                    cnt1--;
                    i--;
                    continue;
                }
                
                if (cnt2 > 0 && c2 != '\0') {
                    cnt2--;
                    j--;
                    continue;
                }
                
                if (c1 != c2) return false;
                i--;
                j--;
            }
        }
        
        return i == j;
    }
}
```

