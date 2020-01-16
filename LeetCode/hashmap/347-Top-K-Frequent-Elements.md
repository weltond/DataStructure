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
    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null) return new ArrayList();
        
        Map<Integer, Integer> map = new HashMap();  // <val, freq>
        
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // ======== Method 2: Bucket ========
        // 10ms
        List[] bucketFreq = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (bucketFreq[freq] == null) bucketFreq[freq] = new ArrayList();
            bucketFreq[freq].add(key);
        }
        
        List<Integer> res = new ArrayList();
        
        for (int i = bucketFreq.length - 1; i >= 0 && res.size() < k; i--) {
            if (bucketFreq[i] != null) {
                for (int val : (ArrayList<Integer>)bucketFreq[i]) {
                    res.add(val);
                }
            }
        }
        
        return res;
        // ===========================================================================
        
        // ======== Method 1: PQ =========
        // 42ms
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k + 1, (o1, o2) -> map.get(o1) - map.get(o2));   // min heap
        
        int cnt = 0;
        for (int val : map.keySet()) {
            pq.add(val);
            cnt++;
            if (cnt > k) {
                pq.poll();
            }
        }
        
        List<Integer> res = new LinkedList();
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        Collections.reverse(res);
        return res;
    }
    // ===========================================================================
}
