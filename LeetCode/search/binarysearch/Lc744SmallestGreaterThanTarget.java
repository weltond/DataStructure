// https://leetcode.com/problems/find-smallest-letter-greater-than-target/solution/

// Template 2
// 4ms
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0) return 'a';
        
        int left = 0, right = letters.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (letters[mid] > target) right = mid;
            else left = mid + 1;
        }
        
        return letters[left] <= target ? letters[0] : letters[left]; 
    }
}

// Template 3
// 7ms
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0) return 'a';
        
        int left = 0, right = letters.length - 1;
        if ((letters[right] - 'a') <= (target - 'a')) return letters[left];
        
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            
            if (letters[mid] - 'a' > target - 'a') right = mid;
            else left = mid;
        }
        
        if ((letters[left] - 'a') > (target - 'a') || (letters[right] - 'a') < (target - 'a')) return letters[left];
        else return letters[right];
    }
}
