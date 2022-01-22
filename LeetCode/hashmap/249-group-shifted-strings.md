## 🔒 [249. Group Shifted Strings](https://leetcode.com/problems/group-shifted-strings/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)


We can shift a string by shifting each of its letters to its successive letter.

For example, "abc" can be shifted to be "bcd".
We can keep shifting the string to form a sequence.

For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.

 

Example 1:

```
Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
```

Example 2:

```
Input: strings = ["a"]
Output: [["a"]]
``` 

**Constraints**:

-1 <= strings.length <= 200
- 1 <= strings[i].length <= 50
- strings[i] consists of lowercase English letters.

## Answers

### Method 1 - HashMap - 4ms (44%) 🐰

Use hashmap to store the list for every `key` in the map, where `key` is the pivot for the same pattern.

**Time**: O(N * K)

**Space**: O(N * K)

where N is the length of `strings`, K is the maximum length of a string in `strings`.

```java
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        // <pivot_string, origin_string>
        Map<String, List<String>> map = new HashMap();
        
        for (String s : strings) {
            char pivot = s.charAt(0);
            
            // "bcd" -> "abc"
            StringBuilder sb = new StringBuilder();
            for (int i = 0, len = s.length(); i < len; i++) {
                char c = shift(s.charAt(i), pivot);
                sb.append(c);
            }
            
            // map["abc"] = {"abc","bcd"}
            map.computeIfAbsent(sb.toString(), (o) -> new ArrayList()).add(s);
        }
        
        // get result
        List<List<String>> res = new ArrayList();
        
        // Iterate map - Approach 1
        // for (Map.Entry<String, List<String>> it : map.entrySet()) {
        //     List<String> list = it.getValue();
        //     res.add(list);
        // }
        
        // Iterate map - Approach 2
        for (List<String> group : map.values()) {
            res.add(group);
        }
        
        return res;
    }
    
    private char shift(char origin, char pivot) {
        return (char) ((origin - pivot + 26) % 26 + 'a');   // need "+26" to take care of negative value
    }
}
```
