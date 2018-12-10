package model.entity;

import java.util.ArrayList;
import java.util.List;

public class BookAndAuthors {
    Book book;
    List<Author>authors=new ArrayList<>();

    public BookAndAuthors() {
    }

    public BookAndAuthors(Book book, List<Author> authors) {
        this.book = book;
        this.authors = authors;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
