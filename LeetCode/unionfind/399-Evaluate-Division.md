## [637. Valid Word Abbreviation](https://www.lintcode.com/problem/valid-word-abbreviation/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a non-empty string word and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as `"word"` contains only the following valid abbreviations:

`["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]`

Example
Example 1:

```
Input : s = "internationalization", abbr = "i12iz4n"
Output : true
```

Example 2:

```
Input : s = "apple", abbr = "a2e"
Output : false
```

Notice
- Notice that only the above abbreviations are valid abbreviations of the string word. Any other string is not a valid abbreviation of word.

## Answer
### Method 1 - Two Pointer - :rabbit: 330ms (70%)

```java
public class Solution {
    /**
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        // write your code here
        if (word == null || word.length() == 0) return false;
        
        int i = 0, j = 0;
        int lenw = word.length(), lena = abbr.length();
        
        while (i < lena) {
            char c = abbr.charAt(i);
            if (c > '0' && c <= '9') {     // ignore num start with `0`
                int res = 0;
                while (i < lena && Character.isDigit(abbr.charAt(i))) {
                    res = res * 10 + abbr.charAt(i) - '0';
                    i++;
                }
                j = j + res;
            } else {
                if (j >= lenw || c != word.charAt(j++)) {
                    //System.out.println(i+","+j);
                    return false;
                }
                i++;
            }
        }
        
        return i == lena && j == lenw;
    }
}
```
class Solution {
    // ============ Sol 2: UNION FIND ============
    // 1ms, beat 100%
  class Node {
    public String parent;
    public double ratio;
    public Node(String parent, double ratio) {
      this.parent = parent;
      this.ratio = ratio;
    }
  }
  
  class UnionFindSet {
    private Map<String, Node> parents = new HashMap<>();
    
    public Node find(String s) {
      if (!parents.containsKey(s)) return null;
      Node n = parents.get(s);
      if (!n.parent.equals(s)) {
        Node p = find(n.parent);
        n.parent = p.parent;
        n.ratio *= p.ratio;
      }
      return n;
    }
    
    public void union(String s, String p, double ratio) {
      boolean hasS = parents.containsKey(s);
      boolean hasP = parents.containsKey(p);
      if (!hasS && !hasP) {
        parents.put(s, new Node(p, ratio));
        parents.put(p, new Node(p, 1.0));
      } else if (!hasP) {
        parents.put(p, new Node(s, 1.0 / ratio));
      } else if (!hasS) {
        parents.put(s, new Node(p, ratio));
      } else {
        Node rS = find(s);
        Node rP = find(p);
        rS.parent = rP.parent;
        rS.ratio = ratio / rS.ratio * rP.ratio;
      }
    }
  }
  
  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    UnionFindSet u = new UnionFindSet();
    
    for (int i = 0; i < equations.length; ++i)
      u.union(equations[i][0], equations[i][1], values[i]);
    
    double[] ans = new double[queries.length];
    
    for (int i = 0; i < queries.length; ++i) {      
      Node rx = u.find(queries[i][0]);
      Node ry = u.find(queries[i][1]);
      if (rx == null || ry == null || !rx.parent.equals(ry.parent))
        ans[i] = -1.0;        
      else
        ans[i] = rx.ratio / ry.ratio;
    }
    
    return ans;
  }
    
    // ============ Sol 1: GRAPH ==============
    // 35ms
    Map<String, HashMap<String, Double>> map = new HashMap();
    
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        double[] res = new double[queries.length];
        
        // build graph
        for (int i = 0; i < equations.length; i++) {
            String x = equations[i][0];
            String y = equations[i][1];
            double k = values[i];
            // x / y = k : x -> <y, k>
            map.computeIfAbsent(x, n -> new HashMap<String, Double>()).put(y, k);
            // y / x = 1/k : y -> <x, 1/k>
            map.computeIfAbsent(y, n -> new HashMap<String, Double>()).put(x, 1 / k);
        }

        // query
        for (int i = 0; i < queries.length; i++) {
            String x = queries[i][0];
            String y = queries[i][1];
            if (!map.containsKey(x) || !map.containsKey(y)) {
                res[i] = -1;
                continue;
            }
            res[i] = dfs(x, y, new HashSet<String>());
        }
        
        return res;
    }
    
    public double dfs(String x, String y, HashSet<String> visited) {
        // base case
        if (x.equals(y)) return 1.0;
        
        if (!map.containsKey(x)) return -1.0;
        
        visited.add(x);
        
        for (String s : map.get(x).keySet()) {
            if (visited.contains(s)) continue;
            visited.add(s);
            
            double d = dfs(s, y, visited);
            
            // we don't have negative number in the problem.
            if (d != -1.0) {
                // a / c = (a / b) * (b / c) 
                return d * map.get(x).get(s);
            }
        }
        
        return -1.0;
    }
}
