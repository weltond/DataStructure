## [636. Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/)

On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.

We store logs in timestamp order that describe when a function is entered or exited.

Each log is a string with this format: `"{function_id}:{"start" | "end"}:{timestamp}"`.  For example, `"0:start:3"` means the function with id 0 started at the beginning of timestamp 3.  `"1:end:2"` means the function with id 1 ended at the end of timestamp 2.

A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.

The CPU is single threaded which means that only one function is being executed at a given time unit.

Return the exclusive time of each function, sorted by their function id.

 

Example 1:


```
Input:
n = 2
logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3, 4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time. 
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
```

Note:

- 1 <= n <= 100
- Two functions won't start or end at the same time.
- Functions will always log when they exit.

## Answer
### Method 1 - Stack - :rocket: 13ms (97.26%)

```java
class Solution {
    public String reorganizeString(String s) {
        Map<Character, Integer> map = new HashMap();
        
        // store char-freq pair into map
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        // max heap
        PriorityQueue<Character> pq = new PriorityQueue<Character>((o1, o2) -> map.get(o2) - map.get(o1));
        
        // push all to pq
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            pq.offer(e.getKey());
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (!pq.isEmpty()) {
            char c = pq.poll();
            int len = sb.length();
            // if character is diff with tail char in sb
            if (len == 0 || c != sb.charAt(len - 1)) {
                sb.append(c);
                int freq = map.get(c);
                if (freq > 1) pq.offer(c);
                map.put(c, freq - 1);
            } 
            // if last char is the same
            else {
                if (pq.isEmpty()) return "";
                
                char c2 = pq.poll();
                                
                sb.append(c2);
                int freq2 = map.get(c2);
                if (freq2 > 1) pq.offer(c2);
                map.put(c2, freq2 - 1);
                
                // DO NOT FORGET to push top frequency entry into queue as well
                pq.offer(c);
            }
            
        }
        
        return sb.toString();
        
    }
}
```
