package model.entity;

public class TakenBooks extends Book {
    String takenDate;
    String wilReturnDate;
    User user;

    public TakenBooks() {
        super();
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

    @Override
    public String toString() {
        return "TakenBooks{" +
                "takenDate='" + takenDate + '\'' +
                ", wilReturnDate='" + wilReturnDate + '\'' +
                ", user=" + user +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", authors='" + authors + '\'' +
                '}';
    }
}
