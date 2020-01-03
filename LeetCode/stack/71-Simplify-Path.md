## [71. Simplify Path](https://leetcode.com/problems/simplify-path/)

Given an **absolute path** for a file (Unix-style), simplify it. Or in other words, convert it to the **canonical path**.

In a UNIX-style file system, a period `.` refers to the current directory. Furthermore, a double period `..` moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix

Note that the returned canonical path must always begin with a slash `/`, and there must be only a single slash `/` between two directory names. The last directory name (if it exists) **must not** end with a trailing `/`. Also, the canonical path must be the **shortest** string representing the absolute path.

 

Example 1:
```
Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
```
Example 2:
```
Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
```
Example 3:
```
Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
```
Example 4:
```
Input: "/a/./b/../../c/"
Output: "/c"
```
Example 5:
```
Input: "/a/../../b/../c//.//"
Output: "/c"
```
Example 6:
```
Input: "/a//b////c/d//././/.."
Output: "/a/b/c"
```

## Answer
### Method 2 - O(1) space - :rocket: 4ms (90.44%)

```java
class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) return "";
        
        String[] strs = path.split("/");
        StringBuilder sb = new StringBuilder();
        
        int skip = 0;
        for (int i = strs.length - 1; i >= 0; i--) {
            String s = strs[i];
            if (s.equals("") || s.equals(".")) continue;
            else if (s.equals("..")) {
                skip++;
            } else {
                if (skip > 0) {
                    skip--;
                    continue;
                }
                sb.insert(0, s).insert(0, "/");
            }
        }
        
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
```

### Method 1 - Stack - :rocket: 4ms (90.44%)

```java
class Solution {
    // 4ms (90.44%)
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList();
        for (String s : path.split("/")) {
            if (s.equals("..")) stack.pollFirst();  // return null if stack is empty
            else if (!s.equals("") && !s.equals(".")) stack.addFirst(s);   
        }
        
        StringBuilder sb = new StringBuilder();
        
        if (stack.isEmpty()) return "/";
        
        while (!stack.isEmpty()) {
            sb.append("/").append(stack.pollLast());
        }
        
        return sb.toString();
    }
}
```
