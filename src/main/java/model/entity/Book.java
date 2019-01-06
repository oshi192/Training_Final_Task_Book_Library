package model.entity;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String name;
    private String section;
    private String authors="-";
    public Book() {
    }

    public Book(int id, String name, String section) {
        this.id = id;
        this.name = name;
        this.section = section;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", authors='" + authors + '\'' +
                '}';
    }
}
