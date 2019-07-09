## [714. Best Time to Buy and Sell Stock with Transaction Fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:
```
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
```
Note:

- 0 < prices.length <= 50000.
- 0 < prices[i] < 50000.
- 0 <= fee < 50000.

## Answer
### Method 1 - DP
#### Approach 2 :rabbit: 5ms (84.13%)
```java
class Solution {
    // ======== Method 1: DP ==========
    // 5ms (84.13%)
    public int maxProfit(int[] prices, int fee) {
        if (prices == null) return 0;
        
        int n = prices.length;
        
        int sell = 0;
        int buy = -prices[0];
        
        for (int i = 1; i < n; i++) {
            int tmp = sell;
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = Math.max(buy, tmp - prices[i]);
        }
        
        return Math.max(buy, sell);
    }
}
```
#### Approach 1 :turtle: 6ms (39.89%)
```java
class Solution {
    // ======== Method 1: DP ==========
    // 6ms (39.89%)
    public int maxProfit(int[] prices, int fee) {
        if (prices == null) return 0;
        
        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        
        buy[0] = -prices[0];
        
        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }
        
        return Math.max(buy[n - 1], sell[n - 1]);
    }
}
```
