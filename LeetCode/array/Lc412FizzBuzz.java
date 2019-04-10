// https://leetcode.com/problems/fizz-buzz/

/**
n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
*/
class Solution {
    // 2ms (59.10%)
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList();
        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            if (i % 3 == 0) {
                sb.append("Fizz");
            }
            if (i % 5 == 0) {
                sb.append("Buzz");
            }
            
            if (sb.length() == 0) {
                sb.append(i);
            }
            
            res.add(sb.toString());
        }
        
        return res;
    }
}
