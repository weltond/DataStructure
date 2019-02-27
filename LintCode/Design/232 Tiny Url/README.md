## Description
Given a long url, make it shorter.

You should implement two methods:

- `longToShort(url)` Convert a long url to a short url which starts with `http://tiny.url/`.
- `shortToLong(url)` Convert a short url to a long url.

You can design any shorten algorithm, the judge only cares about two things:

1. The `short key`'s length should equal to 6 (without domain and slash). And the acceptable characters are `[a-zA-Z0-9]`. For example: `abcD9E`
2. No two long urls mapping to the same short url and no two short urls mapping to the same long url.

## Example
Example 1:
```
Input: shortToLong(longToShort("http://www.lintcode.com/faq/?id=10"))
Output: "http://www.lintcode.com/faq/?id=10"
Explanation: 
  When longToShort() called, you can return any short url.
  For example, "http://tiny.url/abcdef".
  And "http://tiny.url/ABCDEF" is ok as well.
```

Example 2:
```
Input:
  shortToLong(longToShort("http://www.lintcode.com/faq/?id=10"))
  shortToLong(longToShort("http://www.lintcode.com/faq/?id=10"))
Output:
  "http://www.lintcode.com/faq/?id=10"
  "http://www.lintcode.com/faq/?id=10"
```

## TO DO
```java
public class TinyUrl {
    /*
     * @param url: a long url
     * @return: a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        // write your code here
    }

    /*
     * @param url: a short url starts with http://tiny.url/
     * @return: a long url
     */
    public String shortToLong(String url) {
        // write your code here
    }
}
```

## TAG
**Hash Table**