# Longest Related Problems

- [Sliding Window](#sliding-window)

## HashSet
### [409. Longest Palindrome](https://github.com/weltond/DataStructure/blob/master/LeetCode/hashmap/409-Longest-Palindrome.md) 

#### HashSet
  - A common trick to verify if a string can form palindrome is to use a hashset. Remove char if already seen. At the end, if set size is **less than 2**, it means the string can form a palindrome.
  - So final result would be either **length of string**(meaning set size is 0) or **lenth - size + 1**(meaning we should delete `size - 1` chars to make the string palindrome.

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
