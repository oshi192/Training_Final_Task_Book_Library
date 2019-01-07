package model.entity;

public class BooksRequest extends Book {
    private User user;

    public BooksRequest() {
    }

    public BooksRequest(Book book) {
        super();
        this.authors=book.authors;
        this.id=book.id;
        this.name=book.name;
        this.section=book.section;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BooksRequest{" +
                "user=" + user +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", authors='" + authors + '\'' +
                '}';
    }
}
