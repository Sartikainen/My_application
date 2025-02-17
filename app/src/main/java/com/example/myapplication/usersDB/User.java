package com.example.myapplication.usersDB;

public class User {
    private String name;
    private String lastname;
    private String sex;
    private int age;

    public User() {};

    public User(String name, String lastname, String sex, int age) {
        this.name = name;
        this.lastname = lastname;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}


