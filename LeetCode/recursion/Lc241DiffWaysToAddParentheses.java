// https://leetcode.com/problems/different-ways-to-add-parentheses/

/**
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation: 
((2-1)-1) = 0 
(2-(1-1)) = 2
Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
*/

class Solution {
    // ============ Method1: Recursion + Memoization ==============
    // 1ms (100%)
    Map<String, List<Integer>> map = new HashMap();
    public List<Integer> diffWaysToCompute(String input) {
        if (map.containsKey(input)) return map.get(input);
        
        List<Integer> ret = new LinkedList();
        int size = input.length();
        for (int i = 0; i < size; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i + 1);
                
                List<Integer> ret1 = diffWaysToCompute(part1);
                List<Integer> ret2 = diffWaysToCompute(part2);
                
                for (Integer p1 : ret1) {
                    for (Integer p2 : ret2) {
                        int res = 0;
                        switch(c) {
                            case '+':
                                res = p1 + p2;
                                break;
                            case '-':
                                res = p1 - p2;
                                break;
                            case '*':
                                res = p1 * p2;
                                break;
                        }
                        ret.add(res);
                    }
                }
            }
        }
        
        if (ret.size() == 0) {
            ret.add(Integer.valueOf(input));  // digits only
        }
        
        map.put(input, ret);
        
        return ret;
    }
}
