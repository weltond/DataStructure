// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/


class Solution {
    // DP
    // unhold[i] : max profit that unhold stock on the i-th day
    // hold[i] : max profit that hold stock on the i-th day
    /**
        Base case:
            hold[0] = -prices[0], hold[1] = max(-price[0], -price[1])
            unhold[0] = 0
        Induction Rule:
            hold[i] = unhold[i - 2] - prices[i]         if buy on the i-th day
                    = hold[i - 1]                       if not buy on the i-th day
            unhold[i] = hold[i - 1] + prices[i]         if sell on the i-th day
                      = unhold[i - 1]                   if not sell on the i-th day
    */
    
    // ======== Approach 2 : without use DP matrix O(1) space =========
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        
        int size = prices.length;
        int hold = prices[0];
        int unhold = 0;
        int prehold = 0;
        for (int i = 1; i < size; i++) {
            int tmp = unhold;
            // another base case
            if (i == 1) {
                hold = Math.max(-prices[i - 1], -prices[i]);
            } else {
                hold = Math.max(prehold - prices[i], hold);
            }
            
            unhold = Math.max(hold + prices[i], unhold);
            prehold = tmp;
        }
        
        return unhold;
    }
    
    // ======== Approach 1 : use DP matrix O(n) space =========
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        
        int size = prices.length;
        int[] hold = new int[size];
        int[] unhold = new int[size];
        // base case
        hold[0] = -prices[0];
        unhold[0] = 0;
        
        for (int i = 1; i < size; i++) {
            // another base case
            if (i == 1) {
                hold[i] = Math.max(-prices[i - 1], -prices[i]);
            } else {
                hold[i] = Math.max(unhold[i - 2] - prices[i], hold[i - 1]);
            }
            
            unhold[i] = Math.max(hold[i - 1] + prices[i], unhold[i - 1]);
        }
        
        return unhold[size - 1];
    }
}
