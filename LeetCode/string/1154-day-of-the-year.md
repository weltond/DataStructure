## [1154. Day of the Year](https://leetcode.com/problems/day-of-the-year/submissions/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given a string date representing a Gregorian calendar date formatted as `YYYY-MM-DD`, return the day number of the year.

Example 1:

```
Input: date = "2019-01-09"
Output: 9
Explanation: Given date is the 9th day of the year in 2019.
```

Example 2:

```
Input: date = "2019-02-10"
Output: 41
```

Example 3:

```
Input: date = "2003-03-01"
Output: 60
```

Example 4:

```
Input: date = "2004-03-01"
Output: 61
```

Constraints:

- `date.length == 10`
- `date[4] == date[7] == '-'`, and all other date[i]'s are digits
- `date` represents a calendar date between Jan 1st, 1900 and Dec 31, 2019.

## Answer
### Method 1 :rabbit: 2ms (74.11%)

- Like [1185. Days of week](https://github.com/weltond/DataStructure/blob/master/LeetCode/array/1185-day-of-the-week.md)
- `1900` Feb only has 28 days

```java
class Solution {
    public int dayOfYear(String date) {
        String[] strs = date.split("-");
        int year = Integer.parseInt(strs[0]);
        int month = Integer.parseInt(strs[1]);
        int day = Integer.parseInt(strs[2]);
        
        int[] daysOfMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
        int num = 0;
        
        if (year != 1900 && year % 4 == 0) daysOfMonth[1] = 29;
        
        for (int m = 0; m < month - 1; m++) {
            num += daysOfMonth[m];
        }
        
        num += day;
        
        return num;
    }
}
```
