## [282. Expression Add Operators](https://leetcode.com/problems/expression-add-operators/)

Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:
```
Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 
```
Example 2:
```
Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
```
Example 3:
```
Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
```
Example 4:
```
Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
```
Example 5:
```
Input: num = "3456237490", target = 9191
Output: []
```

## Answer
### Method 1 - Backtracking - :turtle: 248ms (5.25%)
#### Approach 2

```java
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new LinkedList();
        if (num == null || num.length() == 0) return res;
        
        dfs(num, 0, target, 0, 0, "", res);
        
        return res;
    }
    
    private void dfs(String num, int lvl, int target, long curSum, long last, String s, List<String> res) {
        if (lvl == num.length()) {
            if (curSum == target) {
                res.add(s);
            }
            return;
        }        
        
        for (int i = lvl, len = num.length(); i < len; i++) {
            String sv = num.substring(lvl, i + 1);
            if (sv.length() > 1 && sv.charAt(0) == '0') continue;
            
            long val = Long.valueOf(sv);
        
            if (lvl == 0) {
                dfs(num, i + 1, target, val, val, "" + val, res);
            } else {
                dfs(num, i + 1, target, curSum + val, val, s + "+" + val, res);
                dfs(num, i + 1, target, curSum - val, -val, s + "-" + val, res);
                dfs(num, i + 1, target, curSum - last + last * val, val * last, s + "*" + val, res);
        }
        }
        
    }
}
```

```java
public class Solution {
    /**
     * @param num: a string contains only digits 0-9
     * @param target: An integer
     * @return: return all possibilities
     */
    public List<String> addOperators(String num, int target) {
        // write your code here
        List<String> res = new LinkedList();
        dfs(num, target, 0, 0, 0, new String(), res);
        
        return res;
    }
    
    private void dfs(String num, long target, int lvl, long pre, long last, String s, List<String> res) {
        int len = num.length();
        
        if (lvl == len) {
            if (last == target) {
                res.add(s);
            }
            return;
        }
        
        for (int i = lvl; i < len; i++) {
            String sv = num.substring(lvl, i + 1);
            if (sv.length() > 1 && sv.charAt(0) == '0') return;
            long val = Long.valueOf(sv);
            
            // dfs(num, target, 1 + i, val, lvl == 0 ? val : last + val, lvl == 0 ? s + sv : s + "+" + sv, res);
            // dfs(num, target, 1 + i, -val, lvl == 0 ? val : last - val, lvl == 0 ? s + sv : s + "-" + sv, res);
            // dfs(num, target, 1 + i, pre * val, lvl == 0 ? val : last - pre + pre * val, lvl == 0 ? s + sv : s + "*" + sv, res);
            
            if (lvl == 0) {
                dfs(num, target, i + 1, val, val, "" + sv, res);
            } else {
                dfs(num, target, 1 + i, val, last + val, s + "+" + sv, res);
                dfs(num, target, 1 + i, -val, last - val,  s + "-" + sv, res);
                dfs(num, target, 1 + i, pre * val, last - pre + pre * val, s + "*" + sv, res);
            }
        }
    }
}
```

#### Approach 1

```java
class Solution {
    // ======== Method 1: Back tracking ==========
    // 248ms (5.25%)
    Set<String> set;
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList();
        set = new HashSet();
        dfs(num, 0, target, 0, 0, new String(), res);
        
        return res;
    }
    
    private void dfs(String num, int lvl, long t, long diff, long cur, String s, List<String> res) {
        int len = num.length();
        if (set.contains(s)) return;
        set.add(s);
        
        if (lvl == len) {
            //System.out.println(s +","+Integer.toString(cur));
            if (t == cur) {
                res.add(s);
            }
            return;
        }
        
        //int val = 0;
        for (int i = lvl; i < len; i++) {
            //val = val * 10 + num.charAt(i) - '0'; // how to deal with 05?
            //String sv = Integer.toString(val);
            
            String sv = num.substring(lvl, i + 1);
            if (sv.length() > 1 && sv.charAt(0) == '0') return;
            long val = Long.valueOf(sv);
            
            dfs(num, 1 + i, t, val, lvl == 0 ? val : cur + val, lvl == 0 ? s + sv : s + "+" + sv, res);
            dfs(num, 1 + i, t, -val, lvl == 0 ? val : cur - val, lvl == 0 ? s + sv : s + "-" + sv, res);
            dfs(num, 1 + i, t, val * diff, lvl == 0 ? val : (cur - diff) + diff * val, lvl == 0 ? s + sv : s + "*" + sv, res);
        }
        
    }
}
```
