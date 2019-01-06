package model.entity;

public class Author {
    private int id;
    private String firstName;
    private String secondName;
    private String patronymicName;

    public Author() {
    }

    public Author(int id, String firstName, String secondName, String patronymicName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymicName = patronymicName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecond_name(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        if(patronymicName==null){
            this.patronymicName = "";
        }else {
            this.patronymicName = patronymicName;
        }
    }

    @Override
    public String toString() {
        return firstName +  patronymicName +
                " " +secondName ;
    }
}
