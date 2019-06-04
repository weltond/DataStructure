// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

/**
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // ============= Method 1: Store locations ============
    // 2ms. O(NlogN)
    List<Location> locations;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        locations = new ArrayList();
        dfs(root, 0, 0);
        
        Collections.sort(locations);
        
        List<List<Integer>> res = new ArrayList();
        res.add(new ArrayList());
        
        int prev = locations.get(0).x;
        
        for (Location loc : locations) {
            // if the x value changed, it's part of a new report
            if (loc.x != prev) {
                prev = loc.x;
                res.add(new ArrayList());
            }
            
            // we always add the node's value to the latest report.
            res.get(res.size() - 1).add(loc.val);
        }
        
        return res;
    }
    
    private void dfs(TreeNode root, int x, int y) {
        if (root == null) return;
        
        locations.add(new Location(x, y, root.val));
        
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }
}

class Location implements Comparable<Location> {
    int x, y, val;
    
    Location(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    
    // sort by x first, then y, finally val.
    @Override
    public int compareTo(Location that) {
        if (this.x != that.x) {
            return Integer.compare(this.x, that.x);
        } else if (this.y != that.y) {
            return Integer.compare(this.y, that.y);
        } else {
            return Integer.compare(this.val, that.val);
        }
        
        
    }
}
