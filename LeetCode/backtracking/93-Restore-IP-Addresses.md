## [93. Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/)

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:
```
Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
```

## Answer
### Method 1 - Backtracking - :rocket: 1ms (100%)

- Think about why we **cannot(?)** apply **Memoization** here.

#### Approach 2

- Like [Word Break II]().
- Notice: the base case should verify if `lvl == 4` first, **NOT** `s.length() == 0` first!

```java
class Solution {
    // Map<Integer, List<String>> map = new HashMap();
    public List<String> restoreIpAddresses(String s) {
        return dfs(s, 0);
    }
    
    private List<String> dfs(String s, int lvl) {
        //if (map.containsKey(lvl)) return map.get(lvl);
        
        List<String> res = new ArrayList();
        int len = s.length();

        if (lvl == 4) {
            if (len == 0) {
                res.add("");
            }
            return res;
        }
        
        len = Math.min(len, 3); // 
        
        for (int i = 1; i <= len; i++) {
            String sub = s.substring(0, i);
            
            if (i > 1 && sub.charAt(0) == '0') continue;
            if (i == 3 && Integer.parseInt(sub) > 255) continue;

            List<String> ret = dfs(s.substring(i), lvl + 1);
            
            if (ret.isEmpty()) {
                continue;
            }

            for (String next : ret) {
                res.add(sub + (next.equals("") ? "" : "." + next));
            }
        }
        
        //map.put(lvl, res);
        
        return res;
    }
}
```

#### Approach 1 

- Void return

```java
class Solution {
    // ============ Method 1 : Backtracking ============
    // 1ms (100%)
    // Map<String, Boolean> map;
    public List<String> restoreIpAddresses(String s) {
        // map = new HashMap();
        List<String> res = new ArrayList();
        dfs(s, 0, new StringBuilder(), res);
        return res;
    }
    
    private void dfs(String s, int lvl, StringBuilder sb, List<String> res) {
        //if (map.containsKey(s)) return map.get(s);
        if (lvl == 4) {
            //map.put(s, s.length() == 0);
            if (s.length() == 0) {
                sb.deleteCharAt(sb.length() - 1);
                res.add(sb.toString());
                sb.append(".");
            }
            return;
        }
        
        // boolean flag = false;
        int len = s.length();
        // go through every possible length
        for (int i = 1; i <= 3 && i <= len; i++) {
            String t = s.substring(0, i);
            if (isValid(t)) {
                sb.append(t).append(".");
                // flag = dfs(s.substring(i), lvl + 1, sb, res) || flag;
                dfs(s.substring(i), lvl + 1, sb, res);
                // backtracking
                int sbSize = sb.length();
                sb.delete(sbSize - 1 - i, sbSize);
            }
        }
        // map.put(s, flag);
        // return flag;
    }
    
    private boolean isValid(String s) {
        int len = s.length();
        int res = 0;
        for (int i = 0; i < len; i++) {
            int c = s.charAt(i) - '0';
            res = res * 10 + c;
        }
        
        // remember to verify if start with 0.
        return (0 <= res && res <= 255) && (s.length() > 1 ? s.charAt(0) != '0' : true);    
    }
}
```
