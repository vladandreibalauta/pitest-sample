package com.example.domain;


public class Book {

    private String id;
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public Book() {
    }

    public Book(String id, String title, String author) {

        this.title = title;
        this.id = id;
        this.author = author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
