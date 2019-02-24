// https://leetcode.com/problems/reconstruct-itinerary/
class Solution {
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList();
        
        Map<String, PriorityQueue<String>> map = buildMap(tickets);
        
        dfs(map, "JFK", res);
        
        Collections.reverse(res);
        
        return res;
    }
    
    private void dfs(Map<String, PriorityQueue<String>> map, String start, List<String> res) {
        
        while (map.get(start) != null && !map.get(start).isEmpty()) {
            String newStart = map.get(start).poll();
            dfs(map, newStart, res);
        } 
        
        res.add(start);
    }
    
    private Map<String, PriorityQueue<String>> buildMap(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap();
        for (int i = 0; i < tickets.length; i++) {
            if (!map.containsKey(tickets[i][0])) {
                map.put(tickets[i][0], new PriorityQueue<>());
            }
            map.get(tickets[i][0]).offer(tickets[i][1]);
            // map.getOrDefault(tickets[i][0], 
            //                  new PriorityQueue<String>()).offer(tickets[i][1]);
        }
        return map;
    }
}
