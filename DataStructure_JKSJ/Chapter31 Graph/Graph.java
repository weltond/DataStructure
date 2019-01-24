// undirection graph
public class Graph {
	private int v;	// number of vertex
	private LinkedList<Integer> adj[]; 	// adjacency list
	
	public Graph(int v) {
		this.v = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i) {
			adj[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int s, int t) {
		adj[s].add(t);
		adj[t].add(s);
	}
	
}