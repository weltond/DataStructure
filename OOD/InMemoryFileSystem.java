/**
A file System, in its most simplistic version, consists of Files and Directories. 
Each Directory contains a set of Files and Directories. Since Files and Directories share so many characteristics,
we can implement them such that they inherit from the same class, Entry.
*/

// Entry is superclass for both File and Directory
public abstract class Entry {
  protected Directory parent;
  protected long created;
  protected long lastUpdated;
  protected long lastAccessed;
  protected String name;
  
  public Entry(String n, Directory p) {
    name = n;
    parent = p;
    created = System.currentTimeMillis();
    lastUpdated = System.currentTimeMillis(); 
    lastAccessed = System.currentTimeMillis();
  }
  
  public boolean delete() {
    if (parent == null) return false;
    return parent.deleteEntry(this);
  }
  
  public abstract int size();
  
  /* Getters and setters. */
  public long getcreationTime() 
  { 
    return created; 
  } 
  public long getLastUpdatedTime() 
  { 
    return lastUpdated; 
  } 
  public long getLastAccessedTime() 
  { 
    return lastAccessed; 
  } 
  public void changeName(String n) 
  { 
    name = n; 
  } 
  public String getName() 
  { 
    return name; 
  } 
}
// A class to represent a File (Inherits from Entry) 
public class File extends Entry {
  private String content;
  private int size;
  
  public File(String n, Directory p, int sz) {
    super(n, p);
    size = sz;
  }
  
  public int size() 
  { 
    return size; 
  } 
  public String getContents() 
  { 
    return content; 
  } 
  public void setContents(String c) 
  { 
    content = c; 
  }
}
// A class to represent a Directory (Inherits from Entry)
public class Directory extends Entry {
  protected ArrayList<Entry> contents;
  public Directory(String n, Directory p) {
    super(n, p);
    contents = new ArrayList();
  }
  public int size() {
    int size = 0;
    for (Entry e: contents) {
      size += e.size();
    }
    return size;
  }
  
  public int numberOfFiles() {
    int cnt = 0;
    for (Entry e: contents) {
      if (e istanceof Directory) {
        cnt++;  // Directory counts as a file
        Directory d = (Directory) e;
        cnt += d.numberOfFiles();
      } else if (e istanceof File) {
        cnt++;
      }
    }
    return cnt;
  }
  
  public boolean deleteEntry(Entry entry) {
    return contents.remove(entry);
  }
  
  public void addEntry(Entry entry) 
  { 
    contents.add(entry); 
  } 

  protected ArrayList<Entry> getContents() 
  { 
    return contents; 
  }
}
