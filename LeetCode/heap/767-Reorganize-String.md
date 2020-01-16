## [767. Reorganize String](https://leetcode.com/problems/reorganize-string/)

Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:
                            
```
Input: S = "aab"
Output: "aba"
```

Example 2:

```
Input: S = "aaab"
Output: ""
```

Note:

- S will consist of lowercase letters and have length in range [1, 500].

## Answer
### Method 3 - :rocket: 0ms 
- **Time O(N)**: fill hash[] + find the letter + write results into char array
- **Space O(N + 26)**: result + hash[]
- count letter appearance and store in hash[i]
- find the letter with largest occurence.
- put the letter into even index numbe (0, 2, 4 ...) char array
- put the rest into the array

```java
class Solution {
    public String reorganizeString(String S) {
        int[] map = new int[26];
        for (char c : S.toCharArray()) {
            map[c - 'a']++;
        }
        
        int maxV = 0, letter = 0;
        for (int i = 0; i < 26; i++) {
            if (map[i] > maxV) {
                maxV = map[i];
                letter = i;
            }
        }           
        
        int len = S.length();
        if (maxV > (len + 1) / 2) return "";
        
        // fill the largest one
        int idx = 0;
        char[] res = new char[len];
        while (map[letter] > 0) {
            res[idx] = (char) (letter + 'a');
            idx += 2;
            map[letter] -= 1;
        }
        
        // fill the rest
        for (int i = 0; i < 26; i++) {
            while (map[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                
                res[idx] = (char) (i + 'a');
                idx += 2;
                map[i] -= 1;
            }
        }
        
        return String.valueOf(res);
                                                          
    }
}
```

### Method 2 - Heap - :rabbit: 6ms (48%)

- PQ to store chars based on max freq.
- If next poll is the same as prev, keep poll next if exists, otherwise return "".

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

### Method 1 

```java
class Solution {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) return "";
        Map<Character, Integer> map = new HashMap();
        PriorityQueue<Character> pq = new PriorityQueue<Character>(S.length(), (o1, o2) -> map.get(o2) - map.get(o1));    // max heap
        
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int maxF = 0;
        for (char c : map.keySet()) {
            maxF = Math.max(maxF, map.get(c));
            pq.offer(c);
        }
        if (S.length() - maxF < maxF - 1) return "";
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int size = pq.size();
            List<Character> tmp = new ArrayList();
            for (int i = 0; i < 2 && i < size; i++) {
                char c = pq.poll();
                Integer j = map.get(c);
                sb.append(c);
                if (j > 1) {
                    map.put(c, j - 1);
                    tmp.add(c);
                }
            }
            
            // add char back to pq
            for (char cha : tmp) {
                pq.add(cha);
            }
        }
        
        return sb.toString();
    }
}
```
