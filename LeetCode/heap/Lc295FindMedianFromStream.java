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


class MedianFinder {
    // 137ms (10.84%)
    PriorityQueue<Integer> lo;  // max heap
    PriorityQueue<Integer> hi;  // min heap
    /** initialize your data structure here. */
    public MedianFinder() {
        lo = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        hi = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // 1. add to max heap
        lo.offer(num);
        // 2. balance step
        hi.offer(lo.poll());
        
        // 3. maintain size property that lo has one more element than hi
        if (lo.size() < hi.size()) {
            lo.offer(hi.poll());
        }
    }
    
    public double findMedian() {
        return lo.size() > hi.size() ? (double) lo.peek() : (lo.peek() + hi.peek()) * 0.5;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
