// https://leetcode.com/problems/single-number-ii/

/**
Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
*/
class Solution {
    public int singleNumber(int[] A) {
        int ones = 0, twos = 0;
        for(int i = 0; i < A.length; i++){
            /*
             IF the set "ones" does not have i
                Add i to the set "ones" if and only if its not there in set "twos"
            ELSE
                Remove it from the set "ones"
            */
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
        }
        return ones;
}
}

