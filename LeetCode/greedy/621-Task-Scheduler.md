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
### Method 2 - Greedy - :rocket: 2ms (100%)

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

### Method 1 - PriorityQueue - :turtle: 52ms (11.95%)

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        // max heap
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
    
        for (int i : map) {
            if (i > 0)
                pq.offer(i);
        }
        
        int res = 0;
        while (!pq.isEmpty()) {
            int i = 0;
            List<Integer> tmp = new LinkedList();
            
            // extract 
            while (i <= n) {
                // if have remaining elements
                if (!pq.isEmpty()) {
                    if (pq.peek() > 1) {
                        tmp.add(pq.poll() - 1); // add for later put into pq
                    } else {
                        pq.poll();
                    }
                }
                
                res++;
                
                // if no more remaining, just return
                if (pq.isEmpty() && tmp.size() == 0) break;
                
                i++;
            }
            
            for (int t : tmp) {
                pq.offer(t);
            }
        }
        
        return res;
    }
}
```

## Followup
- Print out the order

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < tasks.length; i++) {
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);
        }
        
        int res = 0;
        PriorityQueue<Character> pq = new PriorityQueue((o1, o2) -> (map.get(o2) - map.get(o1)));
        for (char c : map.keySet()) {
            pq.offer(c);
        }
        StringBuilder sb = new StringBuilder();
        
        while (!pq.isEmpty()) {
            int i = 0;
            List<Character> list = new ArrayList();
            while (i <= n) {
                if (!pq.isEmpty()) {
                    char c = pq.poll();
                    sb.append(c).append("->");
                    int freq = map.get(c);
                    if (freq > 1) list.add(c);
                    map.put(c, freq - 1);
                } else {
                    sb.append("idle").append("->");
                }
                res += 1;
                i++;
                
                if (pq.isEmpty() && list.size() == 0) break;
            }
            
            for (char c : list) {
                pq.offer(c);
            }
        }
        System.out.println(sb.toString());  // A->B->idle->A->B->idle->A->B->
        return res;
    }
}
```
