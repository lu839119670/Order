package com.example.order.Bean;

public class Manager {
    private String user;
    private String password;
    private String name;
    private String sex;
    private String position;
    private String phone;

    public Manager(String user, String password, String name, String sex, String position, String phone) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.position = position;
        this.phone = phone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
