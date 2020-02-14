## [187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:
```
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]
```
## Answer
### Method 1 - Bit - 

#### Approach 2 :rocket: 13ms (96.57%)

```java
class Solution {
    // 4 letters, only 2 bits can represent them
    
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> words = new HashSet();
        List<String> res = new ArrayList();
        Set<Integer> seenWords = new HashSet();
        
        char[] map = new char[26];
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        
        for (int i = 0, len = s.length(); i < len - 9; i++) {
            int v = 0;
            for (int k = i; k < i + 10; k++) {
                v <<= 2;    // move 2 bits (1 letter) right
                v |= map[s.charAt(k) - 'A'];    // concate 10 letter together.
            }
            
            if (!words.add(v) && seenWords.add(v)) {
                res.add(s.substring(i, i + 10));
            }
        }
        
        return res;
    }
}
```

#### Approach 1 :turtle: 23ms (28.86%)

```java
class Solution {
    // ======== Method 1: Bit ==========
    // 23ms (28.86%)
    // A:001, C:011, G:111, T:100
    // store 10 letters' last 3 bits into Set or HashMap.
    //   instead of store String. 
    // It will save a lot space.
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList();
        if (s == null || s.length() <= 9) return res;
        
        int cur = 0, mask = 0x7ffffff;  //mask has 27 1s.
        // get first 9 letters' last three bits
        for (int i = 0; i < 9; i++) {
            cur = (cur << 3) | (s.charAt(i) & 7);   // 001001001001001011011011011 <- AAAAACCCC
        }
        
        Set<Integer> set = new HashSet();
        Set<String> store = new HashSet();
        
        for (int i = 9, len = s.length(); i < len; i++) {
            cur = ((cur & mask) << 3) | (s.charAt(i) & 7);
            if (set.contains(cur)) store.add(s.substring(i - 9, i + 1));
            else set.add(cur);
        }
        
        for (String st : store)
            res.add(st);
        
        return res;
    }
}
```
