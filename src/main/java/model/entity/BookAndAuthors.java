package model.entity;

import java.util.ArrayList;
import java.util.List;

public class BookAndAuthors {
    Book book;
    String authors;

    public BookAndAuthors() {
    }

    public BookAndAuthors(Book book, List<Author> authors) {
        this.book = book;
        this.authors = listAuthorsToString(authors);
    }

    public String listAuthorsToString(List<Author> authors) {
        String res="";
        for (Author a:authors){
            res+=a.toString();
        }
        return res;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
