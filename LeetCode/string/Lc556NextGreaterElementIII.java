// https://leetcode.com/problems/next-greater-element-iii/

class Solution {
    // 0ms (100%)
    
    // 1. "1234" -> "1243": swap the last two
    // 2. "4321" -> "-1"
    // 3. "534976"
    //    1) From right to left, find the first element that is greater than its right: "4"
    //    2) From above, find the smallest that larger than it to the its right: "6"
    //    3) swap above. And then sort the rest: "536974" => "536479"
    
    public int nextGreaterElement(int n) {
        char[] number = (n + "").toCharArray();
        
        int i, j;
        // Step 1: Start from the right most digit and find the first digit that 
        //          is smaller than the digit next to it.
        for (i = number.length - 2; i >= 0; i--) {
            if (number[i] < number[i + 1]) break;
        }
        // if no such digit is found, it's case 1 "1234"
        if (i == -1) return -1;
        
        // find the smallest that larger than the ith element
        int x = number[i], smallest = i + 1;
        for (j = i + 1; j < number.length; j++) {
            if (number[j] > x && number[j] <= number[smallest]) {
                smallest = j;
            }
        }
        // swap them. "534976" => "536974"
        char tmp = number[i];
        number[i] = number[smallest];
        number[smallest] = tmp;
        
        // sort the rest. "536974" => "536479"
        Arrays.sort(number, i + 1, number.length);
        
        // Approach 1:
        int sum = 0;
        for (int k = 0; k < number.length; k++) {
            if (sum > Integer.MAX_VALUE / 10 || (sum == Integer.MAX_VALUE / 10 && number[k] >= '7')) return -1;
            sum = sum * 10 + number[k] - '0';
        }
        return sum;
        
        // Approach 2:
        long val = Long.parseLong(new String(number));
        return val <= Integer.MAX_VALUE ? (int) val : -1;
    }
}
