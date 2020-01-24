// https://leetcode.com/problems/find-median-from-data-stream/
class MedianFinder {

    PriorityQueue<Integer> minpq, maxpq;
    /** initialize your data structure here. */
    public MedianFinder() {
        minpq = new PriorityQueue<>();
        maxpq = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
    }
    
    // rules:
    //   1. 1) if empty add to minpq.
    //      2) if not empty, if val < minpq.top, move to maxpq. otherwise to minpq.
    //   2. if minpq has 2 more elements than maxpq or vice versa, remove minpq top and insert into maxpq or vice versa. So that the minpq and maxpq are a sorted array
    //   3. always get result from minpq if odd total size, or minpq + maxpq if even total size.
    public void addNum(int num) {
        if (minpq.isEmpty() || num > minpq.peek()) {
            minpq.offer(num);
        } else {
            maxpq.offer(num);
        }
        
        int minSize = minpq.size(), maxSize = maxpq.size();
        
        if(minSize - 2 == maxSize) {
            maxpq.offer(minpq.poll());
        }
        if (maxSize - 2 == minSize) {
            minpq.offer(maxpq.poll());
        }
    }
    
    public double findMedian() {
        return minpq.size() == maxpq.size() ? (minpq.peek() + maxpq.peek()) / 2.0 : minpq.size() > maxpq.size() ? minpq.peek() : maxpq.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

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
