## [316. Remove Duplicate Letters](https://leetcode.com/problems/remove-duplicate-letters/)

Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

```
Input: "bcabc"
Output: "abc"
```

Example 2:

```
Input: "cbacdcbc"
Output: "acdb"
```

## Answer
### Method 1 - Stack - :rocket: 2ms (86.83%)

```java
class Solution {
    // 2ms (86.83%)
    public String removeDuplicateLetters(String s) {
        int[] arr = new int[26];
        char[] ch = s.toCharArray();
        for (char c : ch) {
            arr[c - 'a']++;
        }
        
        Deque<Character> stack = new LinkedList();
        boolean[] seen = new boolean[26];   // contain if character is present in stack
        StringBuilder sb = new StringBuilder();
        
        for (char c : ch) {
            arr[c - 'a']--; // decrease num of characters remainingg in the string to be analysed
            if (seen[c - 'a']) continue; // if already in stack, ignore
            
            // e.g. bc in stack, next are 'abc', then we can pop b and c and then push a
            while (!stack.isEmpty() && c < stack.peek() && arr[stack.peek() - 'a'] != 0) {
                seen[stack.pop() - 'a'] = false;
            }
            
            stack.push(c);
            seen[c - 'a'] = true;
        }
        
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        
        return sb.toString();
    }
}
```
