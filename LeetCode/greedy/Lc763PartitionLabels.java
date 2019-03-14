// https://leetcode.com/problems/partition-labels/

class Solution {
    // Idea:
    //      Need an arry last[char] -> index of S where char occur last
    //      Then, we have pointers left, right -> parition; i -> cur index of S
    //      right will be updated when we see a char's last occurrence larger then right
    //      Add res if cur idx == right.
    // 6ms
    public List<Integer> partitionLabels(String S) {
        if (S == null) return new ArrayList();
        
        int[] last = new int[26];
        
        for (int i = 0; i < S.length(); i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        
        List<Integer> res = new ArrayList();
        int left = 0, right = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            right = Math.max(right, last[c - 'a']);
            // when right == cur idx, which means that
            // between [left, right], there is no other char that occurs after right
            if (right == i) {
                res.add(right - left + 1);
                left = right + 1;
            }
        }
        
        return res;
    }
}
