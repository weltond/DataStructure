// https://leetcode.com/problems/search-a-2d-matrix/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
        int n = matrix.length;
        int m = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int row = mid / m;
            int col = mid % m;
            
            if (target < matrix[row][col]) {
                right = mid - 1;
            } else if (target > matrix[row][col]) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
