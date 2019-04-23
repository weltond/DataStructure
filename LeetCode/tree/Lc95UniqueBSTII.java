// https://leetcode.com/problems/unique-binary-search-trees-ii/

/**
Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
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
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        return gen(1, n);
    }
    
    public List<TreeNode> gen(int start, int end) {
        List<TreeNode> all = new LinkedList<>();
        
        if (start > end) {
            all.add(null);
            return all;
        }
        
        // pick a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is choosen to be a root
            List<TreeNode> left = gen(start, i - 1);
            // all possible right subtrees
            List<TreeNode> right = gen(i + 1, end);
            
            // connect left and right trees to the root i
            for (TreeNode l : left) {
                for (TreeNode r: right) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = l;
                    cur.right = r;
                    all.add(cur);
                }
            }
        }
        
        return all;
    }
}
