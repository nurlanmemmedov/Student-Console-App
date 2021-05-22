package models;

/**
 * represents the student throughout the application
 */
public class Student {
    private int id;
    private String name;
    private String surName;
    private String fatherName;
    private String email;
    private String phone;

    public Student(){

    }

    public Student(int id, String name, String surName, String fatherName, String email, String phone) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.fatherName = fatherName;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + " "+ surName+ " "+fatherName;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
