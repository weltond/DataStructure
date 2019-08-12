// https://leetcode.com/problems/pascals-triangle-ii/
class Solution {
    // 0ms
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList();
        int[] dp = new int[rowIndex + 1];
        dp[0] = 1;
        
        for (int i = 1; i < dp.length; i++) {
            int prev = 1;
            for (int j = 1; j < i; j++) {
                int tmp = dp[j];
                dp[j] = dp[j] + prev;
                prev = tmp;
            }
            
            dp[i] = 1;
        }
        
        for (int i = 0; i < dp.length; i++) {
            res.add(dp[i]);
        }
        
        return res;
    }
}

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList(rowIndex + 1);
        
        if (rowIndex < 0) return res;
        
        for (int row = 0; row <= rowIndex; row++) {
            res.add(1);
            
            // add idx from the 3rd row
            // idx start from end to start to make sure there is no impact on current row
            for (int idx = row - 1; idx >= 1; idx--) {
                res.set(idx, res.get(idx - 1) + res.get(idx));
            }
        }
        
        return res;
    }
}
