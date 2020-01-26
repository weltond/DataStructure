# Longest Related Problems


## Sliding Window
### [3. Longest Substring Without Repeating Characters]

#### Solution
- Sliding Window
  - Two pointers `start` and `end`.
  - Move `end` to calculate each char frequency, when seeing repeating, `cnt++`.
  - while `cnt > 0`, move `start` pointer to find a non-repeating substring.
  - either update `res` before or after above while loop.
- Elegant sliding window
  - Use array to store each char's **position** instead of frequency.
  - A `start` pointer points to the last seen char's position of current char. **start = Math.max(start, position[c])**
  - update res and store current position into array.
