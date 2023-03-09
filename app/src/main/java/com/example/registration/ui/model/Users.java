package com.example.registration.ui.model;

public class Users {
    private  String fullName;
    private  String phoneNumber1;
    private  String username1;
    private  String password1;

    public Users(String fullName, String phoneNumber1, String username1, String password1) {
        this.fullName = fullName;
        this.phoneNumber1 = phoneNumber1;
        this.username1 = username1;
        this.password1 = password1;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }
}
