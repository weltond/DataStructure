package com.weltond.backtracking;

/** https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
 * @author weltond
 * @project LeetCode
 * @date 2/18/2019
 */
public class KnightsTour {
    static int N = 8;

    /*A utility func to check if i, j are valid indexes for N*N chessboard*/
    static boolean isSafe(int x, int y, int[][] sol) {
        return (x >= 0) && (x < N) && y >= 0 && y < N && sol[x][y] == -1;
    }

    /*A utility func to print solution matrix*/
    static void printSolution(int[][] sol) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean solveKT() {
        int[][] sol = new int[8][8];

        // initiation of solution matrix
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                sol[x][y] = -1;
            }
        }

        /* xMove[] and yMove[] define next move of Knight.
          xMove[] is for next value of x coordinate
          yMove[] is for next value of y coordinate */
        int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1};

        // since the knight is initially at the first block
        sol[0][0] = 0;

        /* Start from 0,0 and explore all tours using utils*/
        if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
            System.out.println("Solution not exists");
            return false;
        }

        printSolution(sol);

        return true;
    }

    /**
     * A recursive utility function to solve Knight Tour problem
     * @param x current x position
     * @param y current y position
     * @param movei how many steps so far (level)
     * @param sol output
     * @param xMove next x value
     * @param yMove next y value
     * @return
     */
    static int v = 0;
    static boolean solveKTUtil(int x, int y, int movei, int[][] sol, int[] xMove, int[] yMove) {
        int k, nextX, nextY;
        if (movei == N * N) {
            return true;
        }

        boolean flag = false;
        /* Try all next moves from the current coordinate*/
        for (k = 0; k < 8; k++) {
            nextX = x + xMove[k];
            nextY = y + yMove[k];

            if (isSafe(nextX, nextY, sol)) {
                sol[nextX][nextY] = movei;

                // There are a lot of matching results
                //flag = solveKTUtil(nextX, nextY, movei + 1, sol, xMove, yMove) || flag;

                if (solveKTUtil(nextX, nextY, movei + 1, sol, xMove, yMove)) return true;
                sol[nextX][nextY] = -1; // BACK TRACKING
            }
        }

        return flag;
    }

    public static void test() {
        solveKT();
    }
}
