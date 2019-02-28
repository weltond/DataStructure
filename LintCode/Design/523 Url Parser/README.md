## Description
Parse a html page, extract the Urls in it.

## Example
Example 1:
```
Input:
  <html>
    <body>
      <div>
        <a href="http://www.google.com" class="text-lg">Google</a>
        <a href="http://www.facebook.com" style="display:none">Facebook</a>
      </div>
      <div>
        <a href="https://www.linkedin.com">Linkedin</a>
        <a href = "http://github.io">LintCode</a>
      </div>
    </body>
  </html>
Output:
  [
    "http://www.google.com",
    "http://www.facebook.com",
    "https://www.linkedin.com",
    "http://github.io"
  ]
```

Example 2:
```
Input:
  <html>
    <body>
      <div>
        <a  HREF =    "http://www.google.com" class="text-lg">Google</a>
        <a  HREF = "http://www.facebook.com" style="display:none">Facebook</a>
      </div>
      <div>
        <a href="https://www.linkedin.com">Linkedin</a>
        <a href = "http://github.io">LintCode</a>
      </div>
    </body>
  </html>
Output:
  [
    "http://github.io",
    "http://www.facebook.com",
    "http://www.google.com",
    "https://www.linkedin.com"
  ]
```

## TO DO
```java
public class HtmlParser {
    /*
     * @param content: content source code
     * @return: a list of links
     */
    public List<String> parseUrls(String content) {
        // write your code here
    }
}
```

## TAG
**String** / **Regular Expression**