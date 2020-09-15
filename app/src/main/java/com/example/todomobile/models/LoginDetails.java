package com.example.todomobile.models;

public class LoginDetails {

    private String username;
    private String password;
    private int employeeId;

    public LoginDetails(String username, String password, int employeeId) {
        this.username = username;
        this.password = password;
        this.employeeId = employeeId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getEmployeeId() {
        return employeeId;
    }
}
