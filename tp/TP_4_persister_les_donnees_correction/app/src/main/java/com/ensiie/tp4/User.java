package com.ensiie.tp4;

import java.io.Serializable;

public class User implements Serializable {
    String login;
    String password;
    String country;
    String address;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String country, String address) {
        this.login = login;
        this.country = country;
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
