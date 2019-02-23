// https://leetcode.com/problems/gas-station/
class Solution {
    // ======== Better Solution =========
    // time: O(n), space: O(1) 
    public int canCompleteCircuit(int[] gas, int[] cost) { 
        int goodIndex = 0;
        int totalSum = 0;
        int currSum = 0;
        for(int i = 0; i < gas.length; i++){
            totalSum = totalSum + (gas[i] - cost[i]);
            currSum = currSum + (gas[i] - cost[i]);
            
            if(currSum < 0){
                goodIndex = i + 1;
                currSum = 0;
            }
        }
        
        return totalSum < 0 ? -1 : goodIndex;
    }
    
    // ========= Naive Solution ==========
    // time: O(n2), space:O(1)
    int[] gas;
    int[] cost;
    public int canCompleteCircuit(int[] gas, int[] cost) {
        this.gas = gas;
        this.cost = cost;
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] < cost[i]) continue;
            if (isValid(i)) return i;
        }
        return -1;
    }
    
    private boolean isValid(int start) {
        int total = this.gas[start];
        for (int i = 0; i < this.gas.length; i++) {
            
            if (total - this.cost[start % this.gas.length]< 0) {
                return false;
            }
            
            total = total + this.gas[(start + 1) % this.gas.length] - this.cost[start % this.gas.length];
            start++;
        }
        return true;
    }
}
