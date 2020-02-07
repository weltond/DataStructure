// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        
        int curMax = 0, total = 0;
        
        for (int i = 1; i < prices.length; i++) {
            curMax = Math.max(0, curMax += prices[i] - prices[i - 1]);
            total = Math.max(total, curMax);
        }
        
        return total;
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        
        int minP = prices[0];
        int maxP = prices[0];
        int res = 0;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minP) {
                minP = prices[i];
            } else {
                maxP = prices[i];
                res = Math.max(res, maxP - minP);
            }
        }
        
        return res;
    }
}
