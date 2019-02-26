## Description
Design Kindle, which can support 3 type of book format: `PDF`, `MOBI` and `EPUB`.

- Consider using ENUM for book format.
- Consider using simple factory to create reader for each format.

## Example
Input:
```
readBook("EPUB")
readBook("PDF")
readBook("MOBI")
```
Output:
```
Using EPUB reader, book content is: epub
Using PDF reader, book content is: pdf
Using MOBI reader, book content is: mobi
```

## TO DO
```java
import java.util.ArrayList;
import java.util.List;

public class Kindle {

	public Kindle() {
		//write your code here
	}

	public String readBook(Book book) throws Exception {
		//write your code here
	}

	public void downloadBook(Book b) {
		//write your code here
	}

	public void uploadBook(Book b) {
		//write your code here
	}

	public void deleteBook(Book b) {
		//write your code here
	}
}

enum Format {
	EPUB("epub"), PDF("pdf"), MOBI("mobi");

	private String content;

	Format(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}

class Book {
	private Format format;

	public Book(Format format) {
		this.format = format;
	}

	public Format getFormat() {
		return format;
	}
}

abstract class EBookReader {
	
	protected Book book;
	
	public EBookReader(Book b){
		this.book = b;
	}
	
	public abstract String readBook();
	public abstract String displayReaderType();
}

class EBookReaderFactory {

	public EBookReader createReader(Book b) {
		//write your code here
	}
}

class EpubReader extends EBookReader{

	public EpubReader(Book b) {
		super(b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String readBook() {
		//write your code here
	}

	@Override
	public String displayReaderType() {
		// TODO Auto-generated method stub
		return "Using EPUB reader";
	}
}

class MobiReader extends EBookReader {

	public MobiReader(Book b) {
		super(b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String readBook() {
		//write your code here
	}

	@Override
	public String displayReaderType() {
		// TODO Auto-generated method stub
		return "Using MOBI reader";
	}

}
class PdfReader extends EBookReader{

	public PdfReader(Book b) {
		super(b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String readBook() {
		//write your code here
	}

	@Override
	public String displayReaderType() {
		// TODO Auto-generated method stub
		return "Using PDF reader";
	}
}
```

## Tag
**OO Design**