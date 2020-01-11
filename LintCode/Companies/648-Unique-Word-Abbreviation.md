## [648. Unique Word Abbreviation](https://www.lintcode.com/problem/unique-word-abbreviation/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)
An abbreviation of a word follows the form<first letter><number><last letter>. Below are some examples of word abbreviations:

```
a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
```

Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example
Example1

```
Input:
[ "deer", "door", "cake", "card" ]
isUnique("dear")
isUnique("cart")
Output:
false
true
Explanation:
Dictionary's abbreviation is ["d2r", "d2r", "c2e", "c2d"].
"dear" 's abbreviation is "d2r" , in dictionary.
"cart" 's abbreviation is "c2t" , not in dictionary.
```

Example2

```
Input:
[ "deer", "door", "cake", "card" ]
isUnique("cane")
isUnique("make")
Output:
false
true
Explanation:
Dictionary's abbreviation is ["d2r", "d2r", "c2e", "c2d"].
"cane" 's abbreviation is "c2e" , in dictionary.
"make" 's abbreviation is "m2e" , not in dictionary.
```

## Answer
### Method 1 - HashMap - :rabbit: 3653ms (41.40%)

– 单词在字典中出现次数 等于 对应缩写在字典中出现次数 -> unique
– 单词在字典中出现次数 不等于 对应缩写在字典中出现次数 -> not unique

```java
public class ValidWordAbbr {
    Map<String, Integer> dict = new HashMap<>();
    Map<String, Integer> abbr = new HashMap<>();

    // @param dictionary a list of word
    public ValidWordAbbr(String[] dictionary) {
        // Write your code here
        for (String d : dictionary) {
            dict.put(d, dict.getOrDefault(d, 0) + 1);
            String a = getAbbr(d);
            abbr.put(a, abbr.getOrDefault(a, 0) + 1);
        }
    }
    /**
     * @param word a string
     * @return true if its abbreviation is unique or false
     */
    public boolean isUnique(String word) {
        // Write your code here
        String a = getAbbr(word);
        System.out.println(a);
        System.out.println(dict.get(word) == abbr.get(a));
        return dict.get(word) == abbr.get(a);
    }

    String getAbbr(String str) {
        if (str.length() <= 2) {
            return str;
        }
        return "" + str.charAt(0) + (str.length() - 2) + str.charAt(str.length() - 1);
    }
}
// public class ValidWordAbbr {
//     Set<String> set;
//     /*
//     * @param dictionary: a list of words
//     */public ValidWordAbbr(String[] dictionary) {
//         // do intialization if necessary
//         set = new HashSet();
//         for (String s : dictionary) {
//             s = construct(s);
            
//             if (s == null) continue;
            
//             set.add(s);
//         }
        
//     }

//     /*
//      * @param word: a string
//      * @return: true if its abbreviation is unique or false
//      */
//     public boolean isUnique(String word) {
//         word = construct(word);
//         System.out.println(word);
//         return word == null || !set.contains(word);
//     }
    
//     private String construct(String s) {
//         if (s.length() <= 2) return null;
        
//         int val = s.length() - 2;
    
//         StringBuilder sb = new StringBuilder();
//         sb.append(s.charAt(0)).append(val).append(s.charAt(val + 1));
//         return sb.toString();
//     }
// }

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param = obj.isUnique(word);
 */
```
