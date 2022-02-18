## [1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.

We repeatedly make duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.

 

Example 1:

```
Input: s = "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
```

Example 2:

```
Input: s = "azxxzy"
Output: "ay"
``` 

**Constraints**:

- 1 <= s.length <= 105
- s consists of lowercase English letters.

## Answers

### Method 1 - Stack 

#### Approach 2 - Optimal - 11ms (82.13%)

Use a new string to represent stack itself.

```java
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        
        for (char c : s.toCharArray()) {
            if (i == 0 || c != sb.charAt(i - 1)) {
                sb.append(c);
                i++;
            } else {
                sb.deleteCharAt(i-- - 1);
            }
        }
        
        return sb.toString();
    }
}
```

#### Approach 1 - Naive - 70ms (30.34%)

```java
class Solution {
    public String removeDuplicates(String s) {
        Deque<Character> stack = new LinkedList();
        
        for (char c : s.toCharArray()) {
            if (stack.isEmpty() || c != stack.peek()) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        
        return sb.toString();
    }
}
```
