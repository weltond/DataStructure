package com.weltond.entity;

/**
 * @author weltond
 * @project WebBrowserHistory
 * @date 5/18/2019
 */
public class Book {
    private String name;
    private int id;
    private int price;
    private String author;

    public Book(String name, int id, int price, String author) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
