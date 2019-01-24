boolean found = false;	// global var

public void dfs(int s, int t) {
	found = false;
	boolean[] visited = new boolean[v];	// v is the total number
	int[] prev = new int[v];
	for (int i = 0; i < v; ++i) {
		prev[i] = -1;
	}
	
	recurDfs(s, t, visited, prev);
	print(prev, s, t);
}

private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
	if (found) return;
	visited[w] = true;
	if (w == t) {
		found = true;
		return;
	}
	
	for (int i = 0; i < adj[w].size(); ++i) {
		int q = adj[w].get(i);
		if (!visited[q]) {
			prev[q] = w;
			recurDfs(q, t, visited, prev);
		}
	}
}
