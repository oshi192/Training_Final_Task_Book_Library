package model.entity;

public class Book {
    private int id;
    private int userId;
    private int adminId;
    private String name;
    private String section;
    private String authors;
    private String takeBeginDate;
    private String takeEndDate;
    private int status;
    private User user; //todo replace to dto

    public enum Status {
        FREE(0), REQUEST(1), TAKEN(2);
        public final int id;

        private Status(int id) {
            this.id = id;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTakeBeginDate() {
        return takeBeginDate;
    }

    public void setTakeBeginDate(String takeBeginDate) {
        this.takeBeginDate = takeBeginDate;
    }

    public String getTakeEndDate() {
        return takeEndDate;
    }

    public void setTakeEndDate(String takeEndDate) {
        this.takeEndDate = takeEndDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", userId=" + userId +
                ", adminId=" + adminId +
                ", name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", authors='" + authors + '\'' +
                ", takeBeginDate='" + takeBeginDate + '\'' +
                ", takeEndDate='" + takeEndDate + '\'' +
                ", status=" + status +
                '}';
    }
}
