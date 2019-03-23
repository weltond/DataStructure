// 

class Solution {
    public List<List<Integer>> generate(int numRows) {
        if (numRows < 1) return new ArrayList();
        
        List<List<Integer>> res = new ArrayList();
        
        for (int row = 0; row < numRows; row++) {
            List<Integer> list = new ArrayList(row + 1);
            list.add(1);    // first one
            for (int j = 1; j <= row - 1; j++) {
                list.add(j, res.get(row - 1).get(j) + res.get(row - 1).get(j - 1));
            }
            if (row != 0) list.add(1);  // last one
            res.add(list);
        }
        
        return res;
    }
}
