## [425. Letter Combinations of a Phone Number](https://www.lintcode.com/problem/letter-combinations-of-a-phone-number/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a digit string excluded `'0'` and `'1'`, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.


Example 1:

```
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
Explanation: 
'2' could be 'a', 'b' or 'c'
'3' could be 'd', 'e' or 'f'
```

Example 2:

```
Input: "5"
Output: ["j", "k", "l"]
```

Notice
- Although the answer above is in lexicographical order, your answer could be in any order you want.

## Answer
### Method 1 - DFS - :rocket: 178ms (100%)

```java
public class Solution {
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
     Map<Character, String> map = new HashMap();
    public List<String> letterCombinations(String digits) {
        // write your code here
        List<String> res = new LinkedList();
        if (digits == null || digits.length() == 0) return res;
        
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        
        dfs(digits, 0, new StringBuilder(), res);
        return res;
    }
    
    private void dfs(String s, int lvl, StringBuilder sb, List<String> res) {
        int len = s.length();
        if (lvl == len) {
            res.add(sb.toString());
            return;
        }
        
        char c = s.charAt(lvl);
        for (char next : map.get(c).toCharArray()) {
            sb.append(next);
            dfs(s, lvl + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
```
