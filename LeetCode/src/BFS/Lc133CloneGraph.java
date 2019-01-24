package BFS;

import java.util.*;

public class Lc133CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();

        return dfs(node, map);
    }

    public UndirectedGraphNode dfs(UndirectedGraphNode node, Map map) {
        if (node == null) return node;

        if (map.containsKey(node.label))
            return (UndirectedGraphNode) map.get(node.label);

        UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
        map.put(cloneNode.label, cloneNode);
        for(UndirectedGraphNode n: node.neighbors) {
            cloneNode.neighbors.add(dfs(n, map));
        }

        return cloneNode;
    }

    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    };
	
	// BFS
	public UndirectedGraphNode cloneGraph2(UndirectedGraphNode root) {
        if (root == null) return null;
  
      // use a queue to help BFS
      Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
      queue.add(root);

      // use a map to save cloned nodes
      Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

      // clone the root
      map.put(root, new UndirectedGraphNode(root.label));

      while (!queue.isEmpty()) {
        UndirectedGraphNode node = queue.poll();

        // handle the neighbors
        for (UndirectedGraphNode neighbor : node.neighbors) {
          if (!map.containsKey(neighbor)) {
            // clone the neighbor
            map.put(neighbor, new UndirectedGraphNode(neighbor.label));
            // add it to the next level
            queue.add(neighbor);
          }

          // copy the neighbor
          map.get(node).neighbors.add(map.get(neighbor));
        }
      }

      return map.get(root);
    }
}


