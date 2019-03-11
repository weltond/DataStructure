// https://leetcode.com/problems/clone-graph/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
class Solution {
    Map<Node, Node> map = new HashMap();    // oldNode, newNode
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        return dfs(node);
    }
    
    private Node dfs(Node node) {
        // if the old is already be processed. 
        if (map.containsKey(node)) return map.get(node);
        
        Node newNode = new Node(node.val, new ArrayList<Node>());
        map.put(node, newNode);
        
        //newNode.neighbors = new ArrayList();    // don't forget to initialize it
        
        for (Node nei : node.neighbors) {
            newNode.neighbors.add(dfs(nei));
        }
        
        return newNode;
    }
}
