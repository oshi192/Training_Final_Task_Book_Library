package model.entity;

public class User {
    private int id;
    private String email;
    private String phone_number;
    private String password;
    private String name;
    private String surname;
    private String role;

    public enum Role {
        USER, ADMIN, UNKNOWN
    }

    public User() {
    }

    public User(int id, String email, String phone_number, String password, String name, String surname, String role) {
        this.id = id;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
