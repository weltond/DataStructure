## [1570. Dot Product of Two Sparse Vectors](https://leetcode.com/problems/dot-product-of-two-sparse-vectors/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given two sparse vectors, compute their dot product.

Implement class `SparseVector`:

- `SparseVector(nums)` Initializes the object with the vector nums
- `dotProduct(vec)` Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector **efficiently** and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

 

Example 1:

```
Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
```

Example 2:

```
Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
```

Example 3:

```
Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6
``` 

**Constraints**:

- n == nums1.length == nums2.length
- 1 <= n <= 10^5
- 0 <= nums1[i], nums2[i] <= 100

## Answers
### Method 1 - 7ms (80.97%) - 🐰

Store non-zero into list, where [0] is idx, [1] is value.

when compute dot product, use Two Pointer to calculate non-zero product.

```java
class SparseVector {
    List<int[]> pair;  // <idx, val>
    SparseVector(int[] nums) {
        pair = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            
            pair.add(new int[] {i, nums[i]});
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        List<int[]> vecPair = vec.pair;
        
        int i = 0, j = 0;
        int isize = this.pair.size(), jsize = vecPair.size();
        
        int res = 0;
        
        while (i < isize && j < jsize) {
            int[] pair1 = this.pair.get(i), pair2= vecPair.get(j);
            
            // [0] is index, [1] is value
            if (pair1[0] == pair2[0]) {
                res += pair1[1] * pair2[1];
                i++;
                j++;
            } else if (pair1[0] < pair2[0]) {
                i++;
            } else {
                j++;
            }
        }
        
        return res;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
```
