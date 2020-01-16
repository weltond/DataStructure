## [166. Fraction to Recurring Decimal](https://leetcode.com/problems/fraction-to-recurring-decimal/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

```
Input: numerator = 1, denominator = 2
Output: "0.5"
```

Example 2:

```
Input: numerator = 2, denominator = 1
Output: "2"
```

Example 3:

```
Input: numerator = 2, denominator = 3
Output: "0.(6)"
```

## Answer
### Method 1 -HashTable - :rocket: 1ms (100%)

- Be aware of edge case such as **negative**, **0**, **possible overflow** and etc.
- Use current sb's length as indicator for repeating part.

```java
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        
        // "+" or "-"
        sb.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        
        // integeral part
        sb.append(num / den);
        num %= den;
        
        if (num == 0) return sb.toString();
        
        sb.append(".");
        Map<Long, Integer> map = new HashMap(); // val, sb's size
        map.put(num, sb.length());
        
        while (num != 0) {
            num *= 10;
            sb.append(num / den);
            num %= den;
            
            if (map.containsKey(num)) {
                int idx = map.get(num);
                sb.insert(idx, "(");
                sb.append(")");
                break;
            } else {
                map.put(num, sb.length());
            }
            
        }
        
        return sb.toString();
    }
}
```
