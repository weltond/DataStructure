// https://leetcode.com/problems/gray-code/

/**
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

Example 1:

Input: 2
Output: [0,1,3,2]
Explanation:
00 - 0
01 - 1
11 - 3
10 - 2

For a given n, a gray code sequence may not be uniquely defined.
For example, [0,2,3,1] is also a valid gray code sequence.

00 - 0
10 - 2
11 - 3
01 - 1
*/

class Solution {
    // =========== Method 1: Recursion ==========
    // 0ms 
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList();
        if (n < 0) return res;
        if (n == 0) {
            res.add(0);
            return res;
        }
        
        List<Integer> tmp = grayCode(n - 1);
        res = tmp;
        int len = tmp.size();
        int addNum = 1 << (n - 1);
        
        // [0,1] => [11,10] => [110,111,101,100]
        for (int i = len - 1; i >= 0; i--) {
            res.add(addNum + tmp.get(i));
        }
        
        return res;
    }
}
