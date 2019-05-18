package com.weltond.util;

import com.weltond.entity.Book;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weltond
 * @project WebBrowserHistory
 * @date 5/18/2019
 */
public class DBUtil {
    private static Map<String, Book> books = new HashMap<>();

    static {
        books.put("1", new Book("java book", 1, 20, "weltond"));
        books.put("2", new Book("c# book", 2, 30, "yyf"));
        books.put("3", new Book("python book", 3, 40, "burning"));
        books.put("4", new Book("design book", 4, 50, "google"));
    }

    public static  Map<String, Book> findAllBooks() {
        return books;
    }

    public static Book findBookById(String id) {
        return books.get(id);
    }
}
