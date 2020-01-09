## [645. Find the Celebrity](https://www.lintcode.com/problem/find-the-celebrity/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function `bool knows(a, b)` which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.


Example1

```
Input:
2 // next n * (n - 1) lines 
0 knows 1
1 does not know 0
Output: 1
Explanation:
Everyone knows 1,and 1 knows no one.
```

Example2

```
Input:
3 // next n * (n - 1) lines 
0 does not know 1
0 does not know 2
1 knows 0
1 does not know 2
2 knows 0
2 knows 1
Output: 0
Explanation:
Everyone knows 0,and 0 knows no one.
0 does not know 1,and 1 knows 0.
2 knows everyone,but 1 does not know 2.
```

Notice
- There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return `-1`.

## Answer
[More Solution here](https://www.geeksforgeeks.org/the-celebrity-problem/)

### Method 2

```java
public class Solution extends Relation {
    /**
     * @param n a party with n people
     * @return the celebrity's label or -1
     */
    public int findCelebrity(int n) {
        // Write your code here
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (knows(ans, i)) {
                ans = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (ans != i && knows(ans, i)) {
                return -1;
            }
            if (ans != i && !knows(i, ans)) {
                return -1;
            }
        }
        return ans;
    }
}
```

### Method 1 - Recursion - :rabbit: 2534ms (70.00%)

```java
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    boolean[] notCel;
    /**
     * @param n a party with n people
     * @return the celebrity's label or -1
     */
    public int findCelebrity(int n) {
        notCel = new boolean[n];
        return dfs(n, 0);
    }
    
    private int dfs(int n, int cur) {
        if (notCel[cur] == true) return 0;
        
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (i == cur) continue;
            
            if (knows(cur, i)) {
                notCel[cur] = true;
                idx = dfs(n, i);
                if (idx != 0) return idx;
            } else{
                notCel[i] = true;
            }
        }
        
        if (idx != -1) return -1;
        
        // if idx = -1, it means cur knows nobody
        // so check if every knows cur
        for (int i = 0; i < n; i++) {
            if (i != cur && !knows(i, cur)) return -1;  // no result
        }
        
        return cur;
    }
}
```
