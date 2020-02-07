// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0);
        
        int i = 0, len = prices.length;
        int buy = 0, sell = 0, res = 0;
        while (i < len) {
            while (i + 1 < len && prices[i] > prices[i + 1]) i++;
            
            buy = prices[i];
            
            while (i  + 1 < len && prices[i] < prices[i + 1]) i++;
            
            sell = prices[i++];
            
            res += sell - buy;
        }
        
        return res;
    }
}
class Solution {
    // Peak-Valley
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int res = 0;
        
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) i++;    // find valeys
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) i++;    // find peaks
            peak = prices[i];
            
            res += peak - valley;
        }
        
        return res;
    }
}
