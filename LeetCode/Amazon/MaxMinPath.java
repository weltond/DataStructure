package com.weltond.amazon;

import java.util.*;

/**
 * Given a matrix int[][], find the max value of the min path from left-top to bottom-right. (Only go to right or down)
 *
 * Example：
 * [8, 4, 7]
 * [6, 5, 9]
 *
 * Output: 5
 *
 * Explain：
 * There are 3 paths:
 * 8-4-7-9 min: 4
 * 8-4-5-9 min: 4
 * 8-6-5-9 min: 5
 *
 * @author weltond
 * @project LeetCode
 * @date 3/26/2019
 */
public class MaxMinPath {
    // ============= Method 3: DP =================
    public int MaxMinPathDP (int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];

        //base case
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], matrix[i][0]);
        }
        for (int i = 1; i < m; i++) {
            dp[0][i] = Math.min(dp[0][i - 1], matrix[0][i]);
        }

        // induction rule
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(matrix[i][j], Math.max(dp[i - 1][j], dp[i][j - 1]));
                //System.out.println(i + ", " + j + " : " + dp[i][j]);
            }
        }

        return dp[n - 1][m - 1];
    }

    // ============= Method 2: DFS ================
    int max = 0;
    public int MaxMinPathDFS (int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        helper(matrix, min, 0, 0);
        return max;
    }
    public void helper (int[][] matrix, int min, int row, int col) {

        if (row >= matrix.length || col >= matrix[0].length) {
            return;
        }
        if (row == matrix.length - 1 && col == matrix[0].length - 1) {
            max = Math.max(min, max);
            return;
        }
        min = Math.min(matrix[row][col], min);
        helper(matrix, min, row + 1, col);
        helper(matrix, min, row, col + 1);

    }


    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x; this.y = y;
        }
    }

    public static void main(String[] args) {
        MaxMinPath mmp = new MaxMinPath();

        int[][] matrix = {
                {8, 4, 7},
                {6, 5, 9}
        };
        //System.out.println(mmp.MaxMinPathBFS(matrix));
        System.out.println(mmp.MaxMinPathDFS(matrix));
        System.out.println(mmp.MaxMinPathDP(matrix));
    }
}
