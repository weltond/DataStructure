## [1185. Day of the Week](https://leetcode.com/problems/day-of-the-week/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given a date, return the corresponding day of the week for that date.

The input is given as three integers representing the `day`, `month` and `year` respectively.

Return the answer as one of the following values `{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}`.

 

Example 1:

```
Input: day = 31, month = 8, year = 2019
Output: "Saturday"
```

Example 2:

```
Input: day = 18, month = 7, year = 1999
Output: "Sunday"
```

Example 3:

```
Input: day = 15, month = 8, year = 1993
Output: "Sunday"
```

Constraints:

- The given dates are valid dates between the years 1971 and 2100.

## Answer
### Method 1 - :rocket: 0ms

- 1971/1/1 is Friday

```java
class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        // 1971/1/1 is Friday
        
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] daysOfMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
        int num = 0;
        // year
        for (int y = 1971; y < year; y++) {
            if (y % 4 == 0) num += 366;
            else num += 365;
        }
        
        if (year % 4 == 0) daysOfMonth[1] = 29;
        
        // month
        for (int m = 0; m < month - 1; m++) {
            num += daysOfMonth[m];
        }
        
        // day
        num += day - 1;
        
        return week[(num + 5) % 7];
    }
}
```
