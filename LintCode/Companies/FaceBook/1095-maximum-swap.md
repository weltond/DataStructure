## [1095. Maximum Swap](https://www.lintcode.com/problem/maximum-swap/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a non-negative integer. You could choose to swap two digits of it. Return the maximum valued number you could get.

Example 1:

```
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
```

Example 2:

```
Input: 9973
Output: 9973
Explanation: No swap.
```

Notice
- The given number is in the range of `[0, 10^8]`

## Answer
### Method 1 - Math - :rocket: 155ms (100%)

- Find the minimum left index by using **monotonic stack**.
- Find the **right-most** largest element that is greater than `left`'s value.
- swap

```java
public class Solution {
    /**
     * @param num: a non-negative intege
     * @return: the maximum valued number
     */
    public int maximumSwap(int num) {
        // Monotonic Stack
        Deque<Integer> stack = new LinkedList();    // pos
        char[] arr = (num + "").toCharArray();
        
        int left = arr.length;
        // find minimum left index
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int idx = stack.pop();
                left = Math.min(left, idx);
            }
            stack.push(i);
        }
        // 9973
        if (left == arr.length) return num;
        
        // find maximum after left
        int idx = left + 1, max = Integer.MIN_VALUE, right = 0;
        while (idx < arr.length) {
            if (arr[idx] - '0' >= max) {    // = because we need to find the very last element
                max = arr[idx] - '0';
                right = idx;
            }
            idx++;
        }
                //System.out.println(left+","+right);

        char t = arr[left];
        arr[left] = arr[right];
        arr[right] = t;
        
        return Integer.parseInt(new String(arr));
    }
}
```
