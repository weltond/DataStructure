## [825. Friends Of Appropriate Ages](https://leetcode.com/problems/friends-of-appropriate-ages/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person. 

Person A will NOT friend request person B (B != A) if any of the following conditions are true:

- `age[B] <= 0.5 * age[A] + 7`
- `age[B] > age[A]`
- `age[B] > 100 && age[A] < 100`

Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:

```
Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
```

Example 2:

```
Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
```

Example 3:

```
Input: [20,30,100,110,120]
Output: 
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
```


Notes:

- `1 <= ages.length <= 20000.`
- `1 <= ages[i] <= 120.`

## Answer
### Method 1 - Counting - :rabbit: 4ms (40.71%)

- Because `arr` length is `20000` and `age` won't exceed `120`. So instead of compare `20000`, we can store into a size of `121` arr.
- Store the same age into `cnt`. 
- If match, current ageA and ageB total request would be `cnt[ageA] * cnt[ageB]`. 
- Notice when two ages are the same, it turns to `cnt[ageA] * (cnt[ageA] - 1)` as no self request.

```java
class Solution {
    public int numFriendRequests(int[] ages) {
        int[] cnt = new int[121];
        for (int age : ages) cnt[age]++;
        
        int res = 0;
        for (int i = 0; i <= 120; i++) {
            int c1 = cnt[i];
            for (int j = 0; j <= 120; j++) {
                int c2 = cnt[j];
                
                if (j <= i * 0.5 + 7) continue;
                if (j > i) continue;
                if (j > 100 && i < 100) continue;
                
                res += c1 * c2;
                
                if (i == j) res -= c1;  // if same age, they will not request self.
            }
        }
        return res;
    }
}
```
