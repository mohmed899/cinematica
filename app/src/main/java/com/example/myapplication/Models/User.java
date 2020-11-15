package com.example.myapplication.Models;

public class User {
    String id,firstName, lastName ,email, password;

    public static final String PREFERENCE_NAME = "com.example.myapplication";
    public static final String REMEMBER_ME = "remember_me";
    public static final String EMAIL = "email";
    public static final String ID = "id";
    public static final String Fname = "fname";
    public static final String Lname = "lname";

    public User(String id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
