package model.entity;

import java.util.ArrayList;
import java.util.List;

public class TakenBook {
    Book book;
    String authors;
    String takenDate;
    String wilReturnDate;
    User user;

    public TakenBook() {
    }

    public TakenBook(Book book, String authors, String takenDate, String wilReturnDate, User user) {
        this.book = book;
        this.authors = authors;
        this.takenDate = takenDate;
        this.wilReturnDate = wilReturnDate;
        this.user = user;
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

    public String getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(String takenDate) {
        this.takenDate = takenDate;
    }

    public String getWilReturnDate() {
        return wilReturnDate;
    }

    public void setWilReturnDate(String wilReturnDate) {
        this.wilReturnDate = wilReturnDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthors(List<Author> authors) {
        StringBuffer authorsString=null;
        if(authors.size()!=0){
            authorsString.append(authors.get(0).toString());
        }
        for (int i = 0; i < authors.size(); i++) {
            authorsString.append(", ");
            authorsString.append(authors.get(i).toString());
        }
        this.authors=authorsString.toString();
    }
}
