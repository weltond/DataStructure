## [1380. Log Sorting](https://www.lintcode.com/problem/log-sorting/description?_from=ladder&&fromId=14)

Given a list of string logs, in which each element representing a log. Each log can be separated into two parts by the first space. The first part is the ID of the log, while the second part is the content of the log. (The content may contain spaces as well.) The content is composed of only letters and spaces, or only numbers and spaces.

Now you need to sort logs by following rules:

- Logs whose content is letter should be ahead of logs whose content is number.
- Logs whose content is letter should be sorted by their content in lexicographic order. And when two logs have same content, sort them by ID in lexicographic order.
- Logs whose content is number should be in their input order.

Example
Example 1:

```
Input:  
    logs = [
        "zo4 4 7",
        "a100 Act zoo",
        "a1 9 2 3 1",
        "g9 act car"
    ]
Output: 
    [
        "a100 Act zoo",
        "g9 act car",
        "zo4 4 7",
        "a1 9 2 3 1"
    ]
Explanation: "Act zoo" < "act car", so the output is as above.
```

Example 2:

```
Input:  
    logs = [
        "zo4 4 7",
        "a100 Actzoo",
        "a100 Act zoo",
        "Tac Bctzoo",
        "Tab Bctzoo",
        "g9 act car"
    ]
Output: 
    [
        "a100 Act zoo",
        "a100 Actzoo",
        "Tab Bctzoo",
        "Tac Bctzoo",
        "g9 act car",
        "zo4 4 7"
    ]
Explanation:
    Because "Bctzoo" == "Bctzoo", the comparison "Tab" and "Tac" have "Tab" < Tac ", so" Tab Bctzoo "< Tac Bctzoo".
    Because ' '<'z', so "A100 Act zoo" < A100 Actzoo".
```

Notice
- The total number of logs will not exceed 5000
- The length of each log will not exceed 100
- Each log's ID is unique.

## Answer
### Approach 3 (LintCode Answer)

```java
public class Solution {
    /**
     * @param logs: the logs
     * @return: the log after sorting
     */
    class MyCompartor implements Comparator<String> {
        public int compare(String s1, String s2) {
            int t1 = s1.indexOf(' ');
            int t2 = s2.indexOf(' ');
            String ID1 = s1.substring(0, t1);
            String ID2 = s2.substring(0, t2);
            String body1 = s1.substring(t1);
            String body2 = s2.substring(t2);
            if (body1.equals(body2)) {
                return ID1.compareTo(ID2);
            } else {
                return body1.compareTo(body2);
            }
        }
    }

    public String[] logSort(String[] logs) {
        // Write your code here
        List<String> list = new ArrayList<>();
        String[] ans = new String[logs.length];
        int idx = logs.length - 1;

        for (int i = logs.length - 1; i >= 0; i--) {
            int tmp = logs[i].indexOf(' ');
            String body = logs[i].substring(tmp + 1);

            if (Character.isDigit(body.trim().charAt(0))) {
                ans[idx--] = logs[i];   //number
            } else {
                list.add(logs[i]);      //letter
            }
        }
        Collections.sort(list, new MyCompartor());

        idx = 0;
        for (String item : list) {
            ans[idx++] = item;
        }
        return ans;
    }
}
```

### Approach 2 (LeetCode) :turtle: 776ms (10.40%)

```java
class Solution {
    // rules are:
    //  1. Letter-logs come before digit-logs
    //  2. Letter-logs are sorted alphanumerically, by content then identifier
    //  3. Digit-logs remain in the same order
    public String[] logSort(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;   // not tied
                return split1[0].compareTo(split2[0]);  // tied. then compare identifier.
            }
            
            // if log1 is not digit (meaning log2 is digit) put log1 ahead of log2
            // if log1 is digit (meaning log2 might be digit or alpha):
            //      1) if log2 is also digit, they are tied. keep origin order
            //      2) if log2 is not digit, put log1 after log2
            return isDigit1 ? (isDigit2 ? 0 : 1): -1;
        });
        
        return logs;
    }
}
```

### Approach 1 :turtle: 632ms (28.40%)

```java
public class Solution {
    /**
     * @param logs: the logs
     * @return: the log after sorting
     */
    public String[] logSort(String[] logs) {
        // Write your code here
        Arrays.sort(logs, (o1, o2) -> {
            int i = 0, l1 = o1.length(), j = 0, l2 = o2.length();
            while (i < l1) {
                if (i < l1 && o1.charAt(i) == ' ') break;
                i++;
            }
            while (j < l2) {
                if (j < l2 && o2.charAt(j) == ' ') break;
                j++;
            }
            String id1 = o1.substring(0, i + 1);
            String id2 = o2.substring(0, j + 1);
            String content1 = o1.substring(i + 1);
            String content2 = o2.substring(j + 1);
            char c1 = content1.charAt(0), c2 = content2.charAt(0);
            boolean isDigit1 = Character.isDigit(c1), isDigit2 = Character.isDigit(c2);
            i = 0;
            j = 0;
            l1 = content1.length();
            l2 = content2.length();
            if (!isDigit1 && !isDigit2) {
                while (i < l1 && j < l2) {
                    if (content1.charAt(i) != content2.charAt(j)) break;
                    i++;
                    j++;
                }
                // if content is same
                if (i == l1 && j == l2) return id1.compareTo(id2);
                
                // content not same
                return content1.compareTo(content2);
            } else {
                // match 1 and 3.
                return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
            }
        });
        
        return logs;
    }
}
```
