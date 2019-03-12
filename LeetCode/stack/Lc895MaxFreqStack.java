// https://leetcode.com/problems/maximum-frequency-stack/

class FreqStack {
    Map<Integer, Integer> freq; // number, fq
    Map<Integer, Stack<Integer>> groupOfFreq;   // freq, group of numbers
    int maxFreq;
    public FreqStack() {
        freq = new HashMap();
        groupOfFreq = new HashMap();
        maxFreq = 0;
    }
    
    public void push(int x) {
        int fq = freq.getOrDefault(x, 0) + 1;
        freq.put(x, fq);
        
        if (maxFreq < fq) {
            maxFreq = fq;
        }
        
        groupOfFreq.computeIfAbsent(fq, o -> new Stack()).push(x);
    }
    
    public int pop() {
        int x = groupOfFreq.get(maxFreq).pop(); // get the max freq from a stack with max freq
        
        // update it's freq map
        freq.put(x, freq.get(x) - 1);
        
        // verify if the maxFreq's stack is empty
        if (groupOfFreq.get(maxFreq).isEmpty()) {
            maxFreq--;  // garunteed work because the maxFreq is added 1 each push.
        }
        
        return x;
    }
}
/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
