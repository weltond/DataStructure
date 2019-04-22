// https://leetcode.com/problems/flood-fill/

/**
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.
*/

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null) return new int[][]{};
        
        if (newColor != image[sr][sc])
            dfs(image, sr, sc, newColor, image[sr][sc]);
        
        return image;
    }
    
    private void dfs(int[][] arr, int x, int y, int c, int old) {
        if (x >= arr.length || y >= arr[0].length || x < 0 || y < 0 || arr[x][y] != old) return;
        
        arr[x][y] = c;
        int[] dir = {-1, 0, 1, 0, -1};
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i];
            int ny = y + dir[i + 1];
            
            dfs(arr, nx, ny, c, old);
        }
    }
}

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null) return new int[][]{};
        
        if (newColor == image[sr][sc]) return image;
        
        Queue<int[]> q = new LinkedList();
        
        q.offer(new int[]{sr, sc});
        
        int old = image[sr][sc];
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                
                int s = cur[0], e = cur[1];
                image[s][e] = newColor;
                
                int[] dir = {-1, 0, 1, 0, -1};
                for (int j = 0; j < 4; j++) {
                    int nx = dir[j] + s;
                    int ny = dir[j + 1] + e;
                    if (nx < 0 || ny < 0 || nx >= image.length || ny >= image[0].length || image[nx][ny] != old) continue;
                    //image[nx][ny] = newColor;     // DO NOT Set HERE!
                    //System.out.println(nx + ", " + ny);
                    q.offer(new int[]{nx, ny});
                }
                //System.out.println("= ===");
            }
            
        }
           
        
        return image;
    }
}
