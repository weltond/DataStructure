// https://leetcode.com/problems/find-median-from-data-stream/

class MedianFinder {
    // ============= Method 1: Heap ================
    // 104ms (99.03%)
    PriorityQueue<Integer> smaller;
    PriorityQueue<Integer> larger;
    /** initialize your data structure here. */
    public MedianFinder() {
        smaller = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);  // max heap
        larger = new PriorityQueue<Integer>();  // min heap
    }
    
    public void addNum(int num) {
        if (!larger.isEmpty() && num > larger.peek()) larger.offer(num);
        else smaller.offer(num);
        
        int sizeS = smaller.size();
        int sizeL = larger.size();
        if (sizeS - sizeL > 1) {
            larger.offer(smaller.poll());
        }
        if (sizeL - sizeS > 1) {
            smaller.offer(larger.poll());
        }
    }
    
    public double findMedian() {
        int sizeS = smaller.size();
        int sizeL = larger.size();
        return sizeS == sizeL ? (double)(smaller.peek() + larger.peek()) / 2 : sizeS > sizeL ? smaller.peek() : larger.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
