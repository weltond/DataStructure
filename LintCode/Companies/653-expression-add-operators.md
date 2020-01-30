## [653. Expression Add Operators](https://www.lintcode.com/problem/expression-add-operators/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a string that contains only digits `0`-`9` and a target value, return all possibilities to add binary operators (not unary) `+`, `-`, or `*` between the digits so they evaluate to the target value.

Example 1:

```
Input:
"123"
6
Output: 
["1*2*3","1+2+3"]
```

Example 2:

```
Input:
"232"
8
Output: 
["2*3+2", "2+3*2"]
```

Example 3:

```
Input:
"105"
5
Output:
["1*0+5","10-5"]
```

Example 4:

```
Input:
"00"
0
Output:
["0+0", "0-0", "0*0"]
```

Example 5:

```
Input:
"3456237490",
9191 
Output: 
[]
```

## Answer
### Method 1 - DFS - :rocket: 1369ms (88.20%)

- For `*`, `preTotal - preVal + preVal * curVal`.

```java
public class Solution {
    /**
     * @param num: a string contains only digits 0-9
     * @param target: An integer
     * @return: return all possibilities
     */
    public List<String> addOperators(String num, int target) {
        // write your code here
        List<String> res = new ArrayList();
        if (num == null || num.length() == 0) return res;
        
        dfs(num, 0, 0, 0, target, "", res);
        
        return res;
    }
    
    private void dfs(String num, int lvl, long preVal, long preTotal, int target, String s, List<String> res) {
        int len = num.length();
        if (len == lvl) {
            if (preTotal == target) {
                res.add(s);
            }
            return;
        }
        
        for (int i = lvl; i < len; i++) {
            String cur = num.substring(lvl, i + 1);
            if (cur.length() > 1 && cur.charAt(0) == '0') continue;
            
            long curVal = Long.parseLong(cur);
            
            if (lvl == 0) {
                dfs(num, i + 1, curVal, curVal, target, cur, res);
            } else {
                dfs(num, i + 1, curVal, preTotal + curVal, target, s + "+" + cur, res);
                dfs(num, i + 1, -curVal, preTotal - curVal, target, s + "-" + cur, res);
                dfs(num, i + 1, curVal * preVal, preTotal - preVal + preVal * curVal, target, s + "*" + cur, res);
            }
        }
    }
}
```
