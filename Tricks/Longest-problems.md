# Longest Related Problems

* [DP](#dp)
* [HashSet](#hashset)
* [Sliding Window](#sliding-window)
* [Divide and Conquer](#divide-and-conquer)
* [Trie](#trie)

## DP
### [516. Longest Palindromic Subsequence]()
#### DP
- Common dp template for palindrome.
- `dp[i][j]` represent LPS of string from `i` to `j` (both inclusive).
  - if `c[i] == c[j]`, `dp[i][j] = dp[i+1][j-1]`
  - else, `dp[i][j] = max(dp[i+1][j], dp[i][j-1])`.
  
### [673. Number of Longest Increasing Subsequence](https://github.com/weltond/DataStructure/blob/master/LeetCode/dp/673-Number-of-Longest-Increasing-Subsequence.md)
#### DP
- 2 dp arrays. One storing Longest Increasing subsequence. One stores number of lis.

#### Segement Tree

### [329. Longest Increasing Path in a Matrix](https://github.com/weltond/DataStructure/blob/master/LeetCode/recursion/Lc329LongestIncreasingPathInAMatrix.java)
#### DFS + Memo
- simple idea, go to next recursion if its neighbors are less than it. 
- We don't need to initial the `memo` array since for each element, their return result should be at least **1**.

### [1143. Longest Common Subsequence]()
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
  
## Divide and Conquer
### [395. Longest Substring with At Least K Repeating Characters](https://github.com/weltond/DataStructure/blob/master/LeetCode/string/395-Longest-Substring-with-At-Least%20K-Repeating-Characters.md)

#### Divide and Conquer
  - Basic idea is that the substring must be divided by those chars that appears less than `k` times in the string.
  - First find the freq for every char in the string. (**SHOULD CALCULATE FOR EVERY NEW SUBSTRING**)
  - Iterate the string and find the first char that appears less than `k` times.
  - Divide and conquer

## Trie
### [720. Longest Word In Dictionary](https://github.com/weltond/DataStructure/blob/master/LeetCode/trie/720-longest-word-in-dictionary.md)
- Idea is simple, use trie to store all words and iterate if every substring is stored in the trie.
- Two things can make the code length smaller
  - Iterate from `z` to `a` so that it is guranteed that if length is same, the lexi smaller will be returned last.
  - Save each word at the end of trie point so that we don't need a stringbuilder to reconstruct the result.
