class Solution {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) return "";
        Map<Character, Integer> map = new HashMap();
        PriorityQueue<Character> pq = new PriorityQueue<Character>(S.length(), (o1, o2) -> map.get(o2) - map.get(o1));    // max heap
        
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int maxF = 0;
        for (char c : map.keySet()) {
            maxF = Math.max(maxF, map.get(c));
            pq.offer(c);
        }
        if (S.length() - maxF < maxF - 1) return "";
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int size = pq.size();
            List<Character> tmp = new ArrayList();
            for (int i = 0; i < 2 && i < size; i++) {
                char c = pq.poll();
                Integer j = map.get(c);
                sb.append(c);
                if (j > 1) {
                    map.put(c, j - 1);
                    tmp.add(c);
                }
            }
            
            // add char back to pq
            for (char cha : tmp) {
                pq.add(cha);
            }
        }
        
        return sb.toString();
    }
}
