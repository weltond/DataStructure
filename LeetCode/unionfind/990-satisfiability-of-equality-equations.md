## [990. Satisfiability of Equality Equations](https://leetcode.com/problems/satisfiability-of-equality-equations/description/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.

 

Example 1:
```
Input: equations = ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
There is no way to assign the variables to satisfy both equations.
```
Example 2:
```
Input: equations = ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
``` 

**Constraints**:

- 1 <= equations.length <= 500
- equations[i].length == 4
- equations[i][0] is a lowercase letter.
- equations[i][1] is either '=' or '!'.
- equations[i][2] is '='.
- equations[i][3] is a lowercase letter.

## Answer
### Method 1 - Union Find - 1ms (75.13%) 🐰
1. union letters that are '='
2. check letters that are '!' if they are connected from step 1.

```java
class Solution {
    public boolean equationsPossible(String[] equations) {
        // only lower letters
        UF uf = new UF(26);

        // 1. union letters that are "="
        for (int i = 0; i < equations.length; i++) {
            String s = equations[i];
            char c1 = s.charAt(0), c2 = s.charAt(3), op = s.charAt(1);
            int i1 = c1 - 'a', i2 = c2 - 'a';
            if (op == '=') {
                uf.union(i1, i2);
            }
        }

        // 2. check letters that are "!=". impossible if they are connected.
        for (int i = 0; i < equations.length; i++) {
            String s = equations[i];
            char c1 = s.charAt(0), c2 = s.charAt(3), op = s.charAt(1);
            int i1 = c1 - 'a', i2 = c2 - 'a';
            if (op == '!' &&  uf.connected(i1, i2)) {
                return false;  
            }
        }

        return true;
    }
}

class UF {
    int[] parent;
    int[] weight;

    public UF(int n) {
        parent = new int[n];
        weight = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    public int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }

        return x;
    }

    public void union(int p, int q) {
        int rp = find(p), rq = find(q);
        if (rp == rq) return;

        if (weight[rp] > weight[rq]) {
            parent[rq] = rp;
            weight[rp] += weight[rq];
        } else {
            parent[rp] = rq;
            weight[rq] += weight[rp];
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

}
```
