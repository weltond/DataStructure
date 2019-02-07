import java.util.*;
import java.lang.*;
import java.io.*;

/**
* Input : mat[][] = {{1, 1, 0, 0, 0},
*                   {0, 1, 0, 0, 1},
*                   {1, 0, 0, 1, 1},
*                   {0, 0, 0, 0, 0},
*                   {1, 0, 1, 0, 1} 
* Output : 5
* 
* Explain: 8 directions
*/


class NumberOfIslands {
	// No. of rows and columns
	static final int ROW = 5, COL = 5;
	
	// A function to check if a given cell (row, col) can be included in DFS
	boolean isSafe(int M[][], int row, int col, boolean visited[][]) {
		return (row >= 0) && (row < ROW) && 
				(col >= 0) && (col < COL) &&
				(M[row][col] == 1 && !visited[row][col]);
	}
	
	// =================== DFS ====================
	// Time = O(ROW * COL)
	// A utility function to do DFS for a 2D boolean matrix
	// It only considers the 8 neighbors as adjacent vertices
	void DFS(int M[][], int row, int col, boolean visited[][]) {
		// These arrays are used to get row and column numbers of 8 neighbors of a given cell
		int rowNbr[] = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
		int colNbr[] = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
		
		visited[row][col] = true;
		
		for (int k = 0; k < 8; ++k) {
			if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited)) {
				DFS(M, row + rowNbr[k], col + colNbr[k], visited);
			}
		}
	}
	
	// The main function that returns count of islands in a given boolean 2D matrix
	int dfsCountIslands(int M[][]) {
		boolean visited[][] = new boolean[ROW][COL];
		
		int cnt = 0;
		
		for (int i = 0; i < ROW; ++i) {
			for (int j = 0; j < COL; ++j) {
				if (M[i][j] == 1 && !visited[i][j]) {
					DFS(M, i, j, visited);
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	// =================== Union Find ====================
	// The idea is to consider all 1 values as individual sets. Union all adjacent 1 vertices.
	// 1. Initialize reult (count of islands) as 0
	// 2. Traverse each index of the 2D matrix
	// 3. If value at that index is 1, check all its 8 neighbours. If a neighbour is also equals to 1, take union of index and its neighbors
	// 4. Now define an array of size row*column to store frequencies of all sets
	// 5. Now traverse the matrix again. 
	//       If value at index is 1, find its set.
	//       If frequency of the set in the above array is 0, increment the result be 1.
	public static int unionCountIslands(int a[][]) {
		int n = a.length;
		int m = a[0].length;
		
		DisjointUnionSets dus = new DisjointUnionSets(n * m);
		
		// Check for its neighbors and unites the indexes if both are 1
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				// if cell is 0, do nothing
				if (a[j][k] == 0) continue;
				
				// check all 8 neighbors and do a union with neighbors' set if neighbors are also 1
                if (j + 1 < n && a[j + 1][k] == 1)
                    dus.union(j * m + k, (j + 1) * m + k);
                if (j - 1 >= 0 && a[j - 1][k] == 1)
                    dus.union(j * m + k, (j - 1) * m + k);
                if (k + 1 < m && a[j][k + 1] == 1)
                    dus.union(j * m + k, j * m + k + 1);
                if (k - 1 >= 0 && a[j][k - 1] == 1)
                    dus.union(j * m + k, j * m + k - 1);
                if (j + 1 < n && k + 1 < m && a[j + 1][k + 1] == 1)
                    dus.union(j * m + k, (j + 1) * m + (k + 1));
                if (j + 1 < n && k - 1 >= 0 && a[j + 1][k - 1] == 1)
                    dus.union(j * m + k, (j + 1) * m + (k - 1));
                if (j - 1 >= 0 && k + 1 < m && a[j - 1][k + 1] == 1)
                    dus.union(j * m + k, (j - 1) * m + (k + 1));
                if (j - 1 >= 0 && k - 1 >= 0 && a[j - 1][k - 1] == 1)
                    dus.union(j * m + k, (j - 1) * m + (k - 1));
			}
		}
		
		// array to note down frequency of each set
		int[] c = new int[n * m];
		int cnt = 0;
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				if (a[j][k] == 1) {
					int x = dus.find(j * m + k);
					// if frequency of set is 0
                    // increment cnt
                    if (c[x] == 0) {
                        cnt++;
                        c[x]++;
                    } else {
                        c[x]++;
                    } 
				}
			}
		}
		return cnt;
	}
	
	public static void test() {
		int M[][]=  new int[][] {{1, 1, 0, 0, 0}, 
                                 {0, 1, 0, 0, 1}, 
                                 {1, 0, 0, 1, 1}, 
                                 {0, 0, 0, 0, 0}, 
                                 {1, 0, 1, 0, 1} 
                                }; 
        
        System.out.println("Number of islands is: " + NumberOfIslands.dfsCountIslands(M));
		System.out.println("Number of islands is: " + NumberOfIslands.unionCountIslands(M));
	}
}


class DisjointUnionSets {
	int[] rank, parent;
	int n;
	
	public DisjointUnionSets(int n) {
		this.n = n;
		rank = new int[n];
		parent = new int[n];
		makeSet();
	}
	
	void makeSet() {
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}
	
	int find(int x) {
		if (parent[x] != x) {
			return parent[x] = find(parent[x]);
		}
		return x;
	}
	
	void union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		if (xRoot == yRoot) return;
		
		if (rank[xRoot] < rank[yRoot]) parent[xRoot] = yRoot;
		else if (rank[xRoot] > rank[yRoot]) parent[yRoot] = xRoot;
		else {
			parent[yRoot] = xRoot;
			rank[yRoot] += 1;
		}
	}
}