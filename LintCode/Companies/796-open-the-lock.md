## [796. Open the Lock](https://www.lintcode.com/problem/open-the-lock/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: `'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'`. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at `'0000'`, a string representing the state of the 4 wheels.

You are given a list of `deadends` dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a `target` representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.


Example 1:

```
Given deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Return 6

Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
```

Example 2:

```
Given deadends = ["8888"], target = "0009"
Return 1

Explanation:
We can turn the last wheel in reverse to move from "0000" -> "0009".
```

Notice
- The length of deadends will be in the range `[1, 500]`.
- target will not be in the list deadends.
- Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities `'0000'` to `'9999'`.

## Answer
### Method 1 - Bi-directional BFS - :rocket: 496ms (98.20%)

- Same as [Word Ladder](https://github.com/weltond/DataStructure/blob/master/LeetCode/queue/Lc127WordLadder.java)
```java
public class Solution {
    /**
     * @param deadends: the list of deadends
     * @param target: the value of the wheels that will unlock the lock
     * @return: the minimum total number of turns 
     */
    public int openLock(String[] deadends, String target) {
        Set<String> start = new HashSet(), end = new HashSet();
        Set<String> set = new HashSet();
        for (String s : deadends) {
            set.add(s);
        }
        
        start.add("0000");
        end.add(target);
        
        if (set.contains(target) || set.contains("0000")) return -1;
        int steps = 0;
        
        while(!start.isEmpty() && !end.isEmpty()) {
            Set<String> tmp = new HashSet();
            steps++;
            for(String s : start) {
                char[] arr = s.toCharArray();
                for (int j = 0; j < 4; j++) {
                    char c = arr[j];
                    int val = c - '0';
                    for (int k = 9; k <= 11; k+=2) {
                        int nextVal = (val + k) % 10;
                        arr[j] = (char)(nextVal + '0');
                        String next = new String(arr);
                        if (end.contains(next)) return steps;
                        if (!set.contains(next)) tmp.add(next);
                    }
                    arr[j] = c;
                }
            }
            
            start = end;
            end = tmp;
            
        }
        
        return -1;
    }
}
```
