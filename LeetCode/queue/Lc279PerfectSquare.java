// https://leetcode.com/problems/merge-intervals/

class Solution {
	// TO DO:
	// Method 3: DP
	// Method 2: Math
	
	// Method 1 : BFS
	// 78ms
    public int numSquares(int n) {
        LinkedList<Integer> sqrt = new LinkedList<>();
		// get all possible square numbers for n.
        for (int i = 1; i * i <= n; i++) {
            sqrt.add(i * i);
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        Set<Integer> duplicate = new HashSet();	// avoid duplicate
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            res++;
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (int x : sqrt) {
                    int rem = cur - x;
                    if (rem < 0) continue;
                    if (rem == 0) return res;
                    
                    if (duplicate.add(rem)) {
                        q.add(rem);
                    }
                }
            }
        }
        
        return res;
    }
}