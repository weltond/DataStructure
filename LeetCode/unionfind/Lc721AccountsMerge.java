// https://leetcode.com/problems/accounts-merge/

class Solution {
    
    // ========== Method 1: Union Find ===========
    // 67ms 
    class DisJoint {
        int[] parent, rank;
        int n;
        
        DisJoint(int n) {
            this.n = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            
            return parent[x];
        }
        
        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            
            if (px == py) return;
            
            if (rank[px] > rank[py]) {
                parent[py] = px;
            } else if (rank[px] < rank[py]) {
                parent[px] = py;
            } else {
                parent[py] = px;
                rank[px] += 1;
            }
        }
        
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null) return new ArrayList();
        DisJoint ds = new DisJoint(10001);
        Map<String, String> names = new HashMap();  // <email, name>
        Map<String, Integer> emailToId = new HashMap(); // <email, id>
        
        int id = 0;
        
        for (List<String> account : accounts) {
            String name = account.get(0);
            
            // union graph
            for (int i = 1, len = account.size(); i < len; i++) {
                String email = account.get(i);
                if (!emailToId.containsKey(email)) {
                    emailToId.put(email, id++);
                }
                names.put(email, name);
                if (i == 1) continue;
                
                String prevEmail = account.get(i - 1);
                ds.union(emailToId.get(email), emailToId.get(prevEmail));   // union their distinct ID
            }
  
        }
        
        Map<Integer, List<String>> ans = new HashMap();     // <parent_id, email list>
            
        // get same account's emails
        for (String email : names.keySet()) {
            int idx = ds.find(emailToId.get(email));    // find each email's parent id
            ans.computeIfAbsent(idx, x -> new ArrayList()).add(email);
        }

        List<List<String>> res = new ArrayList();
        // Sort each list and add usrname 
        for (Map.Entry<Integer, List<String>> entry : ans.entrySet()) {
            List<String> list = entry.getValue();
            Collections.sort(list); // sort each email list
            list.add(0, names.get(list.get(0)));    // add name to each list
            res.add(list);
        }
        
        return res;
    }
    
    
    // ========== Method 1: DFS ===========
    // 46ms
    
    // Idea: emails are graph nodes. A connected graph for distinct username.
    //  Also, since emails are not sorted, we cannot garuntee that the first email stores username. So 
    //      we need to store a pair <email, username> for every single email.
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null) return new ArrayList();
        
        Map<String, String> names = new HashMap();  // <email, usrname>
        Map<String, Set<String>> graph = new HashMap(); // <email node, neighbors of the node>
        
        // build graph
        for (List<String> account : accounts) {
            String usrname = account.get(0);
            
            // account: [usrname, email1, email2,email3]
            // build graph like: email1 = email2 = email3
            for (int i = 1, len = account.size(); i < len; i++) {
                // first make sure cover the case when there is only one email, so its neighbor is empty
                // so it might not be a good idea to use computeIfAbsent()
                String email = account.get(i);
                if (!graph.containsKey(email)) {
                    graph.put(email, new HashSet());
                }
                
                // add usrname to each email. <email, usrname>
                names.put(email, usrname);
                
                if (i == 1) continue;   
                
                // <1, <2>>, <2, <1, 3>>, <3, <2>>
                String prevEmail = account.get(i - 1);
                graph.get(email).add(prevEmail);
                graph.get(prevEmail).add(email);
            }
        }
        
        // DFS to traverse the graph
        List<List<String>> res = new LinkedList();
        Set<String> visited = new HashSet();    // <email>
        for (String email : graph.keySet()) {
            if (visited.contains(email)) continue;  // no need to traverse visited graph node.
            
            String usrname = names.get(email);  // get the usrname from email
            List<String> list = new LinkedList();
            dfs(graph, email, visited, list);
            
            // after traversing, emails of same usrname is connected. 
            // Sort them to meet the command
            Collections.sort(list);
            
            // Add name to the list
            list.add(0, usrname);
            
            // Add to result
            res.add(list);
        }
        
        return res;
    }
    
    private void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
        if (visited.contains(email)) return;
        
        visited.add(email);
        list.add(email);
        
        for (String nei : graph.get(email)) {
            dfs(graph, nei, visited, list);
        }
    }
}
