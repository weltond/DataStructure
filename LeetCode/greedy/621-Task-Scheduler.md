## [621. Task Scheduler](https://leetcode.com/problems/task-scheduler/)

Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

 

Example:
```
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
``` 

Note:

- The number of tasks is in the range [1, 10000].
- The integer n is in the range [0, 100].

## Answer
### Method 1 - Greedy - :rocket: 2ms (100%)
```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        
        int maxcnt = 0;
        char[] arr = new char[26];
        
        // get max freq
        for (int i = 0; i < tasks.length; i++ ){
            arr[tasks[i] - 'A']++;
        }
        for (int i = 0; i < 26; i++) {
            maxcnt = Math.max(maxcnt, arr[i]);
        }
        
        // calc how many same freq chars
        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (arr[i] == maxcnt) cnt++;
        }
        
        // AAABBB
        // A x x A x x A
        int res = (maxcnt - 1) * (n + 1) + cnt;
        
        return Math.max(res, tasks.length);
    }
}
```
