## [588. Design In-Memory File System](https://leetcode.com/problems/design-in-memory-file-system/)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:

- `FileSystem()` Initializes the object of the system.
- `List<String> ls(String path)`
  - If path is a file path, returns a list that only contains this file's name.
  - If path is a directory path, returns the list of file and directory names **in this directory**.
The answer should in **lexicographic order.**
- void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
- `void addContentToFile(String filePath, String content)`
  - If `filePath` does not exist, creates that file containing given `content`.
  - If `filePath` already exists, appends the given content to original `content`.
- `String readContentFromFile(String filePath)` Returns the content in the file at `filePath`.
 

Example 1:

<img width="710" alt="image" src="https://user-images.githubusercontent.com/9000286/155653316-5cf7cecd-e73f-48ea-9ffa-6a064f88ac96.png">

```
Input
["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
Output
[null, [], null, null, ["a"], "hello"]

Explanation
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/");                         // return []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/");                         // return ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
``` 

**Constraints**:

- 1 <= path.length, filePath.length <= 100
- path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
- You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.
- You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.
- 1 <= content.length <= 50
- At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.

## Answers

### Method 1 - Using separate Dir and File List - 69ms (52.14%)

<img width="528" alt="image" src="https://user-images.githubusercontent.com/9000286/155653378-9d0060d4-67dc-46d9-a82a-0a52f6ba1cd8.png">

Time: `m`is length of input string. `n` is depth of last dir level. `k` is number of entries in the last dir. 
- `ls`: O(m + n + klogk)
- `mkdir`: O(m + n)
- `addContentToFile` and `readContentFromFile`: O(m + n)

East to extend commands.

```java
class FileSystem {
    // <path, ls>
    Map<String, List<String>> pathMap = new HashMap();  
    
    // <file_path, content>
    Map<String, String> contentMap = new HashMap();
    
    Dir root;
    public FileSystem() {
        root = new Dir();
    }
    
    // path is guaranteed exist
    // e.g. ls /a/b
    public List<String> ls(String path) {
        List<String> files = new ArrayList();
        Dir cur = root;
        
        // not root dir
        if (!path.equals("/")) {
            String[] paths = path.split("/");   // ["","a","b"]
            for (int i = 1; i < paths.length - 1; i++) {
                cur = cur.dirs.get(paths[i]);
            }
            
            // cur = a
            // file
            if (cur.files.containsKey(paths[paths.length - 1])) {
                files.add(paths[paths.length - 1]);
                
                return files;
            } else {
                cur = cur.dirs.get(paths[paths.length - 1]);
            }
        }
        
        files.addAll(new ArrayList(cur.dirs.keySet()));
        files.addAll(new ArrayList(cur.files.keySet()));
        
        Collections.sort(files);
        
        return files;
    }
    
    public void mkdir(String path) {
        Dir cur = root;
        
        String[] paths = path.split("/");
        
        for (int i = 1; i < paths.length; i++) {
            String pName = paths[i];
            
            // create new dir if not exist
            if (!cur.dirs.containsKey(pName)) {
                Dir child = new Dir();
                cur.dirs.put(pName, child); 
            }
            
            cur = cur.dirs.get(pName);
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        String[] paths = filePath.split("/");
        
        Dir cur = getDir(paths);
        
        String fileName = paths[paths.length - 1];
        String oldContent = cur.files.get(fileName);
        
        cur.files.put(paths[paths.length - 1], oldContent == null ? content : oldContent + content);
    }
    
    public String readContentFromFile(String filePath) {
        String[] paths = filePath.split("/");
        
        Dir cur = getDir(paths);
        
        return cur.files.get(paths[paths.length - 1]);
    }
    
    private Dir getDir(String[] paths) {
        Dir cur = root;

        for (int i = 1; i < paths.length - 1; i++) {
            cur = cur.dirs.get(paths[i]);
        }
        
        return cur;
    }
}


class Dir {
    // <subdir_name, subdir_structure>
    Map<String, Dir> dirs;
    
    // <file_name, file_content>
    Map<String, String> files;
    
    public Dir() {
        dirs = new HashMap();
        files = new HashMap();
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
 ```

### Method 2 - Using unified Dir and File List

<img width="767" alt="image" src="https://user-images.githubusercontent.com/9000286/155653694-8b804e2f-633f-4bb9-82f8-4c7920a9df20.png">

A problem with the current design could occur if we want to list only the directories(and not the files), on any given path. 

```java
public class FileSystem {
    class File {
        boolean isfile = false;
        HashMap < String, File > files = new HashMap < > ();
        String content = "";
    }
    File root;
    public FileSystem() {
        root = new File();
    }

    public List < String > ls(String path) {
        File t = root;
        List < String > files = new ArrayList < > ();
        if (!path.equals("/")) {
            String[] d = path.split("/");
            for (int i = 1; i < d.length; i++) {
                t = t.files.get(d[i]);
            }
            if (t.isfile) {
                files.add(d[d.length - 1]);
                return files;
            }
        }
        List < String > res_files = new ArrayList < > (t.files.keySet());
        Collections.sort(res_files);
        return res_files;
    }

    public void mkdir(String path) {
        File t = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i++) {
            if (!t.files.containsKey(d[i]))
                t.files.put(d[i], new File());
            t = t.files.get(d[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        File t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.files.get(d[i]);
        }
        if (!t.files.containsKey(d[d.length - 1]))
            t.files.put(d[d.length - 1], new File());
        t = t.files.get(d[d.length - 1]);
        t.isfile = true;
        t.content = t.content + content;
    }

    public String readContentFromFile(String filePath) {
        File t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.files.get(d[i]);
        }
        return t.files.get(d[d.length - 1]).content;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
```
