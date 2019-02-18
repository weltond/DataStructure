package com.weltond.backtracking;

/** https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
 * @author weltond
 * @project LeetCode
 * @date 2/18/2019
 */
public class RatInMaze {
    int N;

    public RatInMaze(int n) {
        N = n;
    }

    /*A utility to print sol*/
    void printSolution(int[][] sol) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*A utility to check if x, y is valid index for N*N maze*/
    boolean isSafe(int[][] maze, int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N && maze[x][y] != 0;
    }

    /**
     *  This func solve Maze problem using Backtracking.
     *  It mainly uses solveMazeUtil() to solve the problem.
     *  It returns false if no path, otherwise print the path in the form of 1s.
     *  Please not that there may be more than one solutions
     *
     * @param maz
     * @return
     */
    boolean solveMaze(int[][] maz) {
        int size = maz.length;
        int[][] sol = new int[size][size];

        if (!solveMazeUtil(maz, 0, 0, sol)) {
            System.out.println("Solution not exists");
            return false;
        }

        printSolution(sol);
        return true;
    }

    boolean solveMazeUtil(int[][] maze, int x, int y, int[][] sol) {
        // if (x,y) reach lower right, i.e. destination
        if (x == N - 1 && y == N - 1) {
            sol[x][y] = 1;
            return true;
        }

        // check if maze[x][y] valid
        if (isSafe(maze, x, y)) {
            // mark x, y as part of solution path
            sol[x][y] = 1;

            /* Move forward in x direction */
            if (solveMazeUtil(maze, x + 1, y, sol)) {
                return true;
            }

            /* If moving in x direction doesn't give
               solution then  Move down in y direction */
            if (solveMazeUtil(maze, x, y + 1, sol)) {
                return true;
            }

            /* If none of the above movements works then
               BACKTRACK: unmark x,y as part of solution
               path */
            sol[x][y] = 0;
            return false;
        }

        return false;
    }


    boolean solveMaze2(int[][] maz) {
        int size = maz.length;
        int[][] sol = new int[size][size];

        if (!solveMazeUtil2(maz, 0, 0, sol)) {
            System.out.println("Solution not exists");
            return false;
        }

        return true;
    }

    boolean solveMazeUtil2(int[][] maze, int x, int y, int[][] sol) {
        // if (x,y) reach lower right, i.e. destination
        if (x == N - 1 && y == N - 1) {
            sol[x][y] = 1;

            System.out.println("Get: ");
            printSolution(sol);

            return true;
        }

        boolean flag = false;
        // check if maze[x][y] valid
        if (isSafe(maze, x, y)) {
            // mark x, y as part of solution path
            sol[x][y] = 1;

            /* Move forward in x direction */
            flag = solveMazeUtil2(maze, x + 1, y, sol) || flag;


            /* If moving in x direction doesn't give
               solution then  Move down in y direction */
            flag = solveMazeUtil2(maze, x, y + 1, sol) || flag;

            /* If none of the above movements works then
               BACKTRACK: unmark x,y as part of solution
               path */
            sol[x][y] = 0;
        }
        return flag;
    }

    public static void test() {
        RatInMaze rat = new RatInMaze(4);
        int[][] maze = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 1},
                {1, 1, 1, 1}
        };
        rat.solveMaze(maze);
        System.out.println("=======");
        rat.solveMaze2(maze);
    }
}
