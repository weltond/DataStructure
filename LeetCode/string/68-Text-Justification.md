## [68. Text Justification](https://leetcode.com/problems/text-justification/)

Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

- A word is defined as a character sequence consisting of non-space characters only.
- Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
- The input array words contains at least one word.

Example 1:
```
Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
```
Example 2:
```
Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
```
Example 3:
```
Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
```
## Answer
### Method 1 - Brute Force - :rocket: 0ms
```java
class Solution {
    // ======= Method 1 ==========
    // 0ms 
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList();
        if (words == null || words.length == 0) return res;
        
        int i = 0;  // the ith word
        while (i < words.length) {
            int j = i, len = 0; // j is the word from i-th to the end of each row
            // calc how many words for one line
            while (j < words.length && len + words[j].length() + j - i <= maxWidth) {   // j - i is at least one space between each word
                len += words[j++].length();
            }
            
            StringBuilder sb = new StringBuilder();
            int space = maxWidth - len;
            // cal how many spaces between each word
            for (int k = i; k < j; k++) {
                sb.append(words[k]);
                if (space > 0) {
                    int cnt = 0;
                    // last row is different
                    if (j == words.length) {
                        if (k == j - 1) cnt = space;    // last word
                        else cnt = 1; // space of words before last word is 1
                    }
                    // not the last row
                    else {
                        if (k < j - 1) {
                            // can be assigned evenly
                            if (space % (j - k - 1) == 0) cnt = space / (j - k - 1);    
                            // CANNOT be assigned evenly, then left will be assigned one more space between two words
                            else cnt = space / (j - k - 1) + 1;
                        } 
                        else {    // if only one word or last word
                            cnt = space;
                        }
                    }
                    
                    for (int c = 0; c < cnt; c++) {
                        sb.append(' ');
                    }
                    space -= cnt;
                }
            }
            
            res.add(sb.toString());
            i = j;  // j is the next
        }
        
        return res;
    }
}
```
