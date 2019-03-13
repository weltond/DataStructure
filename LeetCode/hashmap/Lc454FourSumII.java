// https://leetcode.com/problems/4sum-ii/

class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap();  // <sum, freq>
        
        int size = A.length, res = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int sum = A[i] + B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res += map.getOrDefault(-1 * (C[i] + D[i]), 0);
                // int rem = 0 - (C[i] + D[j]);
                // if (map.containsKey(rem)) {
                //     res += map.get(rem);
                // }
            }
        }
        
        return res;
    }
}
