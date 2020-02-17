## [158. Read N Characters Given Read4 II - Call Multiple Times]()

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Example 1

```
Input:
"filetestbuffer"
read(6)
read(5)
read(4)
read(3)
read(2)
read(1)
read(10)
Output:
6, buf = "filete"
5, buf = "stbuf"
3, buf = "fer"
0, buf = ""
0, buf = ""
0, buf = ""
0, buf = ""
```

Example 2

```
Input:
"abcdef"
read(1)
read(5)
Output:
1, buf = "a"
5, buf = "bcdef"
```

Notice
- The read function may be called multiple times.

## Answer
### Method 2 - 

```java
public class Solution extends Reader4 {
    /**
     * @param buf destination buffer
     * @param n maximum number of characters to read
     * @return the number of characters read
     */
    char[] buffer = new char[4];
    int head = 0, tail = 0;

    public int read(char[] buf, int n) {
        // Write your code here
        int i = 0;
        while (i < n) {
            if (head == tail) {               // queue is empty
                head = 0;
                tail = read4(buffer);         // enqueue
                if (tail == 0) {
                    break;
                }
            }
            while (i < n && head < tail) {
                buf[i++] = buffer[head++];    // dequeue
            }
        }
        return i;
    }
}
```

### Method 1 -

```java
public class Solution extends Reader4 {
    private char[] buf4 = new char[4];
    private int buf4Index = 4;
    private int buf4Size = 4;
    
    private boolean readNext(char[] buf, int index) {
        if (buf4Index >= buf4Size) {
            buf4Size = read4(buf4);
            buf4Index = 0;
            if (buf4Size == 0) {
                return false;
            }
        }

        buf[index] = buf4[buf4Index++];
        return true;
    }
    
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        for (int i = 0; i < n; i++) {
            if (!readNext(buf, i)) {
                return i;
            }
        }
        
        return n;
    }
}
```
