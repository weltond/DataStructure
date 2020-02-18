## [1089. Valid Parenthesis String](https://www.lintcode.com/problem/valid-parenthesis-string/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

- Any left parenthesis '(' must have a corresponding right parenthesis ')'.
- Any right parenthesis ')' must have a corresponding left parenthesis '('.
- Left parenthesis '(' must go before the corresponding right parenthesis ')'.
- '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.

An empty string is also valid.

Example 1:

```
Input:  "()"
	Output:  true
```
	
Example 2:

```
Input: "(*)"
	Output:  true

	Explanation:
	'*' is empty.
```

Example 3:

```
Input: "(*))"
	Output: true
  
	Explanation:
	use '*' as '('.
```

Notice
- The string size will be in the range [1, 100].

## Answer
### Method 1 - :rocket: 152ms (100%)

- `l` is how many `(`, `cp` is how many `(` and `*`.

```java
public class Solution {
    /**
     * @param s: the given string
     * @return: whether this string is valid
     */
    public boolean checkValidString(String s) {
        // l is how many (, cp is how many ( and *
        if (s == null || s.length() == 0) return true;
        
        int l = 0, cp = 0;
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                if (l < 0) return false;
                l++;
                cp++;
            } else if (c == '*') {
                // use as )
                if (l > 0) {
                    l--;
                }
                cp++;
            } else {
                if (l > 0) {
                    l--;
                }
                cp--;
                if (cp < 0) return false;
            }
        }
        
        return l == 0;
        
    }
}
```
