// https://leetcode.com/problems/minimum-cost-for-tickets/

/**
Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: 
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total you spent $11 and covered all the days of your travel.
*/
class Solution {
    // ========= Method 2: Track travel days ===========
    /**
    Travel days: 1  4   6   7   9   10
    Cost:        2  4   6   7   9   9
    Last 7:      7  9   11  13  14  16
    */
    // 2ms (89.95%)
    class Pair {
        int day, cost;
        Pair(int day, int cost) {
            this.day = day;
            this.cost = cost;
        }
    }
    public int mincostTickets(int[] days, int[] costs) {
        Queue<Pair> last7 = new LinkedList();
        Queue<Pair> last30 = new LinkedList();
        
        int cost = 0;
        for (int day : days) {
            while (!last7.isEmpty() && last7.peek().day + 7 <= day) last7.poll();
            while (!last30.isEmpty() && last30.peek().day + 30 <= day) last30.poll();
            
            last7.offer(new Pair(day, cost + costs[1]));
            last30.offer(new Pair(day, cost + costs[2]));
            
            cost = Math.min(cost + costs[0], Math.min(last7.peek().cost, last30.peek().cost));
        }
        
        return cost;
    }
    
    // ========= Method 1: Track calendar days ===========
    /** days=[1,4,6,7,9,10]
    Calendar days: 0    1   2   3   4   5   6   7   8   9   10  ...
    Costs:         0    2   2   2   4   4   6   7   7   9   9 
    */
    // Approach 2: 2ms(89.95%) Optimization: dp[30]
    public int mincostTickets(int[] days, int[] costs) {
        Set<Integer> set = new HashSet();   // travel days
        int lastDay = 0;
        for (int i = 0; i < days.length; i++) {
            set.add(days[i]);
            lastDay = days[i];
        }
        
        // dp[i] represents the min cost until i-th day
        int[] dp = new int[30];
        
        // dp[i] = min(dp[i-1] + cost[0], dp[i-7] + cost[1], dp[i-30] + cost[2])
        for (int i = 1; i <= lastDay; i++) {
            if (!set.contains(i)) {
                dp[i % 30] = dp[(i - 1) % 30];
            } else {
                dp[i % 30] = Math.min(dp[(i - 1) % 30] + costs[0], Math.min(dp[(Math.max(0, i - 7)) % 30] + costs[1], dp[(Math.max(0, i - 30)) % 30] + costs[2]));
            }
        }
        
        return dp[lastDay % 30];
    }
    // Approach 1: 3ms(85.02%) dp[366] 
    public int mincostTickets(int[] days, int[] costs) {
        Set<Integer> set = new HashSet();   // travel days
        for (int i = 0; i < days.length; i++) {
            set.add(days[i]);
        }
        
        // dp[i] represents the min cost until i-th day
        int[] dp = new int[366];
        
        // dp[i] = min(dp[i-1] + cost[0], dp[i-7] + cost[1], dp[i-30] + cost[2])
        for (int i = 1; i < 366; i++) {
            if (!set.contains(i)) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = Math.min(dp[i - 1] + costs[0], Math.min(dp[Math.max(0, i - 7)] + costs[1], dp[Math.max(0, i - 30)] + costs[2]));
            }
        }
        
        return dp[365];
    }
}
