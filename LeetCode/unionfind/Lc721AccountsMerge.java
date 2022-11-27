// https://leetcode.com/problems/accounts-merge/

// UF Time: O(NKlogNK), Space: O(NK)

// 117ms (21.91%)
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UF uf = new UF();
        Map<String, String> names = new HashMap();
        
        for (List<String> account : accounts) {
            String name = account.get(0);
            String prev = account.get(1);
            names.put(prev, name);
            for (int i = 2, len = account.size(); i < len; i++) {
                String cur = account.get(i);
                uf.union(prev, cur);
                names.put(cur, name);
                prev = cur;
            }
        }
        
        Map<String, List<String>> emails = new HashMap();
        for (String email : names.keySet()) {
            String parent = uf.find(email);
            emails.computeIfAbsent(parent, o -> new ArrayList()).add(email);
        }
        
        List<List<String>> res = new ArrayList();
        for (String email : emails.keySet()) {
            String name = names.get(email);
            List<String> es = emails.get(email);
            Collections.sort(es);
            
            es.add(0, name);
            res.add(new ArrayList(es));
        }
        
        return res;
    }
    
    
}
class UF {
    Map<String, String> parent = new HashMap();
    public UF() {
        
    }
    
    public String find(String s) {
        // initialization
        if (!parent.containsKey(s)) {
            parent.put(s, s);
            return s;
        }
        
        // if s's parent is not itself
        if (!s.equals(parent.get(s))) {
            parent.put(s, find(parent.get(s)));
        }
        
        return parent.get(s);
    }
    
    public void union(String s1, String s2) {
        String p1 = find(s1);
        String p2 = find(s2);
        if (p1.equals(p2)) return;
        
        parent.put(p2, p1);
    }
}





public class Solution {
    /**
     * @param accounts: List[List[str]]
     * @return: return a List[List[str]]
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // write your code here
        List<List<String>> res = new ArrayList();
        if (accounts == null | accounts.size() == 0) return res;
        
        UF uf = new UF();
        
        Map<String, String> names = new HashMap();
        
        // Union Together
        for (List<String> list : accounts) {
            String name = list.get(0);
            
            int size = list.size();
            
            String pre = list.get(1);
            names.put(pre, name);
            // emails
            for (int i = 2; i < size; i++) {
                String email = list.get(i);
                names.put(email, name);
                uf.union(email, pre);

                
                pre = email;
            }
        }
        
        // Get distict parent emails
        Map<String, List<String>> map = new HashMap();
        for (String email : names.keySet()) {
            String par = uf.getParent(email);
            map.computeIfAbsent(par, o -> new ArrayList()).add(email);
        }

        
        // Connect together.
        for (String email : map.keySet()) {
            String name = names.get(email);
            List<String> list = new ArrayList();
            list.add(name);
            List<String> ems = map.get(email);
            Collections.sort(ems);
            for (String next : ems) {
                list.add(next);
            }
            res.add(new ArrayList(list));
        }
        
        return res;
    }
}

class UF {
    Map<String, String> parent;
    public UF() {
        parent = new HashMap();
    }
    
    public String getParent(String s) {
        if (!parent.containsKey(s)) {
            parent.put(s, s);
            return s;
        }
        
        if (!parent.get(s).equals(s)) {
            parent.put(s, getParent(parent.get(s)));
        }
        
        return parent.get(s);
    }
    
    public void union(String s1, String s2) {
        String p1 = getParent(s1), p2 = getParent(s2);
        
        if (p1.equals(p2)) return;
        
        parent.put(p2, p1);
    }
}

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
