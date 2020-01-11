## [246. Strobogrammatic Number](https://www.lintcode.com/problem/strobogrammatic-number/description)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

A mirror number is a number that looks the same when rotated 180 degrees (looked at upside down).For example, the numbers "69", "88", and "818" are all mirror numbers.

Write a function to determine if a number is mirror. The number is represented as a string.

Example
Example 1:

```
Input : "69"
Output : true
```

Example 2:

```
Input : "68"
Output : false
```

## Answer
### Method 1 - HashTable - :turtle: 202ms (26.60%)

```java
public class Solution {
    /**
     * @param num: a string
     * @return: true if a number is strobogrammatic or false
     */
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap();
        
        map.put('1', '1');
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');
        map.put('0', '0');
        
        char[] arr = num.toCharArray();
        int i = 0, j = arr.length - 1;
        
        while (i <= j) {
            char c1 = arr[i++], c2 = arr[j--];
            if (!map.containsKey(c1) || map.get(c1) != c2) {
                return false;
            }
        }
        
        return true;
    }
}
```
