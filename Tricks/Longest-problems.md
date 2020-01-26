# Longest Related Problems

- [DP](#dp)
- [HashSet](#hashset)
- [Sliding Window](#sliding-window)

## DP
### [1143. Longest Common Subsequence]
#### DP
- it's obvious that `dp[i][j]` represents longest common subsequence of string s1 starting from 0 - i and string s2 from 0 - j. 

## HashSet
### [409. Longest Palindrome](https://github.com/weltond/DataStructure/blob/master/LeetCode/hashmap/409-Longest-Palindrome.md) 

#### HashSet
  - A common trick to verify if a string can form palindrome is to use a hashset. Remove char if already seen. At the end, if set size is **less than 2**, it means the string can form a palindrome.
  - So final result would be either **length of string**(meaning set size is 0) or **lenth - size + 1**(meaning we should delete `size - 1` chars to make the string palindrome.
  
### [128. Longest Consecutive Sequence](https://github.com/weltond/DataStructure/blob/master/LeetCode/unionfind/Lc128LongestConsecutiveSequence.java)
#### HashSet
- O(n+n).
- Use hashset to store element.
- Second run, do search for current element, see if its consecutive **increasing** sequence is in the hashset or not.

## Sliding Window
### [3. Longest Substring Without Repeating Characters]()

####  Sliding Window
  - Two pointers `start` and `end`.
  - Move `end` to calculate each char frequency, when seeing repeating, `cnt++`.
  - while `cnt > 0`, move `start` pointer to find a non-repeating substring.
  - either update `res` before or after above while loop.
#### Elegant sliding window
  - Use array to store each char's **position** instead of frequency.
  - A `start` pointer points to the last seen char's position of current char. **start = Math.max(start, position[c])**
  - update res and store current position into array.
