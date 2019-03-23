// https://leetcode.com/problems/pascals-triangle-ii/

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
