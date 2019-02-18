package com.weltond.backtracking;

/** https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
 * @author weltond
 * @project LeetCode
 * @date 2/18/2019
 */
public class NQueen {
    final int N = 4;
    private static int k = 1;
    /*A utility to print solution*/
    void printSolution(int[][] board) {
        System.out.print(k++ + "-\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0;j < N; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * A utility func to check if a queen can be palced on board[row][col].
     * Note that this func is called when 'col' queens are already placed in columns from 0 to col-1
     * So wee need to check only left side for attacking queens
     * @param board input
     * @param row row
     * @param col col
     * @return is safe or not
     */
    boolean isSafe(int[][] board, int row, int col) {
        int i, j;

        // check this row on left side
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) return false;
        }

        // check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // check lower diagnoal on left side
        for (i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1) return false;
        }

        return true;
    }
    /**
     * A recursive utility function to solve N Queen Problem， get only 1 possible result
     * @param board input
     * @param col each col
     * @return is solved or not
     */
    boolean solveNQUtil(int[][] board, int col) {
        // Base case:
        // If all queens are placed then return true
        if (col >= N) {
            printSolution(board);
            return true;
        }

        // Consider this column and try placing this queen in all rows one by one
        for (int i = 0; i < N; i++) {
            // check if the queen can be placed on board[i][col]
            if (isSafe(board, i, col)) {
                // place this queen
                board[i][col] = 1;

                // rec to palce rest of the queens
                if (solveNQUtil(board, col + 1))
                    return true;

                // if placing queen in board[i][col] doesn't lead to  a solution
                // then remove queen from board[i][col]
                board[i][col] = 0; // BACK TRACKING
            }
        }

        // if the queen can not be placed in any row in this col, then return false
        return false;
    }

    /**
     * A recursive utility function to solve N Queen Problem， get ALL possible result
     * @param board input
     * @param col each col
     * @return is solved or not
     */
    boolean solveAllNQUtil(int[][] board, int col) {
        // Base case:
        // If all queens are placed then return true
        if (col >= N) {
            printSolution(board);
            return true;
        }

        boolean res = false;
        // Consider this column and try placing this queen in all rows one by one
        for (int i = 0; i < N; i++) {
            // check if the queen can be placed on board[i][col]
            if (isSafe(board, i, col)) {
                // place this queen
                board[i][col] = 1;

                // rec to palce rest of the queens
                res = solveAllNQUtil(board, col + 1) || res;

                // if placing queen in board[i][col] doesn't lead to  a solution
                // then remove queen from board[i][col]
                board[i][col] = 0; // BACK TRACKING
            }
        }

        // if the queen can not be placed in any row in this col, then return false
        return res;
    }

    /**
     * The function solves the N Queen problem using Backtracking.
     * It mainly uses solveNQUtil() to solve the problem.
     * It returns false if queens cannot be placed, else return true and prints placement of queens in the form of 1s
     * Please note that there may be more than one solutions, this func prints one of the feasible solutions
     * @return
     */
    boolean solveNQ() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        if (!solveAllNQUtil(board, 0)) {
            System.out.println("Solution not exists");
            return false;
        }

        return true;
    }

    public static void test() {
        NQueen queen = new NQueen();
        queen.solveNQ();
    }
}
